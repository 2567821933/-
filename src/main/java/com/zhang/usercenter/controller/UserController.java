package com.zhang.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.usercenter.common.BaseResponse;
import com.zhang.usercenter.common.ErrorCode;
import com.zhang.usercenter.common.ResultUtils;
import com.zhang.usercenter.contant.UserContstant;
import com.zhang.usercenter.exception.BusinessException;
import com.zhang.usercenter.model.domain.User;
import com.zhang.usercenter.model.domain.request.UserLoginRequest;
import com.zhang.usercenter.model.domain.request.UserRegisterRequest;
import com.zhang.usercenter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.zhang.usercenter.contant.UserContstant.USER_LOGIN_STATE;

/**
 *  用户接口
 *
 * @author zhang
 */


/**
 *
 */
@RestController //适用与编写RESTful风格的api,表示返回值都是JSON
@Slf4j//使用log
@RequestMapping("/user")
@Api(tags = "user接口")
public class UserController {
    @Resource //注入
    @ApiModelProperty(value = "userService")
    private UserService userService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public BaseResponse userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        //1. 校验，不能为空
        if(userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"请求参数为空 ");
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)) throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"请求参数为空 ");
        log.info(userAccount + userPassword + checkPassword);
        long l = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(l);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if(userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"请求的参数是空的");
        }
        log.info("前端校验，成功进入登录后端");
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword)) return null;//验证参数
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    //按照用户名搜索
    @GetMapping("/search")
    @ApiOperation(value =  "用户搜索", notes = "用户搜索")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        if(!isAdmin(request))  throw new BusinessException(ErrorCode.NO_AUTH,"不是管理员 ");


        //下面正式查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> collect = userService.list(queryWrapper).stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());

        return ResultUtils.success(collect);
    }
        //用以用户的脱敏


    //删除
    @PostMapping("/delete")
    @ApiOperation(value = "用户删除", notes = "用户删除")
    public BaseResponse<Boolean> deleteUser(@RequestBody Map<String,Long> map, HttpServletRequest request) {
        long id = map.get("id");
        log.info("进入");
        if(!isAdmin(request)) throw new BusinessException(ErrorCode.NO_AUTH,"只有管理员才可以访问当前页面");
        log.info("id判断前");
        if(id <= 0) throw new BusinessException(ErrorCode.PARAMS_ERROR,"id的格式不对");
        log.info("返回值前");
        //如果定义了逻辑删除的话，官方在删除的时候会转变为更新。
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }

    //获取用户登录态,应当获取的实时的信息，可以查一次数据库来查看信息（一般不变的话是直接获取）
    @GetMapping("/current")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {


        Object object = request.getSession().getAttribute(USER_LOGIN_STATE);
        User userCurrent = (User) object;
        if(userCurrent == null) throw new BusinessException(ErrorCode.NOT_LOGIN,"用户未登录");
        //todo 校验用户是否合法
        userCurrent = userService.getById(userCurrent.getId());
        User safetyUser = userService.getSafetyUser(userCurrent);//用户脱敏
        return ResultUtils.success(safetyUser);
    }

    //注销用户
    @PostMapping ("/logout")
    @ApiOperation(value = "注销用户", notes = "注销用户")
    public BaseResponse<Integer> closeUser(HttpServletRequest request) {

        if(request == null) throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"请求参数为空");
        userService.userLoginOut(request);
        return ResultUtils.success(1);
    }


    @GetMapping("/search/tags")
    @ApiOperation(value = "获取用户标签", notes = "获取用户标签")
    public BaseResponse<List<User>> searchUsersByTags(@RequestParam(required = false) List<String>  tagNameList) {
        if(CollectionUtils.isEmpty(tagNameList)) {//校验空值
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"标签不能为空");
        }
        List<User> userList = userService.searchUserByTags(tagNameList);
        return ResultUtils.success(userList);//返回过滤后的结果值
    }

    @ApiOperation(value= "用户更新", notes = "用户更新")
    @PostMapping("/update")
    public BaseResponse<Integer>  updateUser(@RequestBody User user, HttpServletRequest request) {
        // 1. 校验null
        if(user == null) {
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"用户信息为空");
        }


        // 2.校验权限
        Integer rel = userService.updateUser(user,request);

        //
        return ResultUtils.success(rel);
    }

    @GetMapping("/recommend")
    @ApiOperation(value = "用户查询所有")
    public BaseResponse<Page<User>> recommendUsers(long pageSize, long pageNum, HttpServletRequest request) {
        if(pageSize == 0) pageSize = 5;
        if(pageNum == 0)  pageNum = 1;

        User loginUser = (User)request.getSession().getAttribute(USER_LOGIN_STATE);
        if(loginUser == null) {
            throw new BusinessException(ErrorCode.NO_AUTH,"没有登录");
        }
        // 去缓存查找
        String format = String.format("zhang:user:recommend:%s:page_%d", loginUser.getId(),pageNum);
        ValueOperations<String, Object> rOp = redisTemplate.opsForValue();
        Page<User> page = (Page<User>)rOp.get(format);

        // 有缓存，直接返回。
        if(page != null) {
            log.info("有缓存，成功返回");
            return ResultUtils.success(page);
        }

        // 没有缓存
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> list = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        try{
            log.info("没有缓存，尝试创建缓存");
            //ToDo 可能出现缓存雪崩
            rOp.set(format,list,30000, TimeUnit.MILLISECONDS);//设置过期时间
        } catch (Exception e) {
            log.error("redis error", e);
        }

        return ResultUtils.success(list);
    }

    /**
     * 判断是否为管理员
     * @param request 请求
     * @return 是否为管理员
     */
    public boolean isAdmin(HttpServletRequest request) {
        return userService.isAdmin(request);
    }
}
