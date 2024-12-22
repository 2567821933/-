package com.zhang.usercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.usercenter.common.BaseResponse;
import com.zhang.usercenter.common.ErrorCode;
import com.zhang.usercenter.common.ResultUtils;
import com.zhang.usercenter.exception.BusinessException;
import com.zhang.usercenter.model.domain.Team;
import com.zhang.usercenter.model.domain.request.TeamQuery;
import com.zhang.usercenter.service.TeamService;
import com.zhang.usercenter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
@RequestMapping("/team")
@Api(tags = "Team接口")
public class TeamController {

    @Resource
    private UserService userService;
    @Resource
    private TeamService teamService;

    // 增删改查
    @PostMapping("/add")
    @ApiOperation(value = "添加", notes = "添加")
    public BaseResponse<Long> addTeam(@RequestBody Team team) {
        if(team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍信息不能为空");
        }
        boolean rel = teamService.save(team);
        if(!rel) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "保存失败");
        }
        return ResultUtils.success(team.getId());
    }
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    public BaseResponse<Boolean> deleteTeam(@RequestBody Long id) {
        if(id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id应大于0");
        }
        boolean b = teamService.removeById(id);
        if(!b) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"删除失败");
        }
        return ResultUtils.success(true);
    }
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    public BaseResponse<Boolean> updateTeam(@RequestBody Team team) {
        if(team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"传入队伍为空");
        }
        boolean b = teamService.updateById(team);
        if(!b) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"修改失败");
        }
        return ResultUtils.success(true);
    }

    @GetMapping("/get")
    @ApiOperation(value = "单个查询", notes = "单个查询")
    public BaseResponse<Team> getTeamById(@RequestParam Long id) {
        if(id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "传入参数错误");
        }
        Team team = teamService.getById(id);
        if(team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "查找失败");
        }

        return ResultUtils.success(team);
    }
    @GetMapping("/listTeam")
    @ApiOperation(value = "多个查询", notes = "多个查询")
    public BaseResponse<List<Team>> listTeams(@RequestParam TeamQuery teamQuery) {
        Team team = new Team();
        team = copyPropertiesUtils(team,teamQuery);
        QueryWrapper<Team> teamQueryWrapper = new QueryWrapper<Team>(team);
        List<Team> list = teamService.list(teamQueryWrapper);
        return ResultUtils.success(list);
    }

    // 分页操作
    @GetMapping("/list/page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public BaseResponse<Page<Team>> listTamesByPage(TeamQuery teamQuery){
        Team team = new Team();
        team = copyPropertiesUtils(team,teamQuery);

        Page<Team> teamPage = new Page<>(teamQuery.getPageNum(), teamQuery.getPageSize());
        QueryWrapper<Team> teamQueryWrapper = new QueryWrapper<>(team);
        Page<Team> page = teamService.page(teamPage, teamQueryWrapper);
        return ResultUtils.success(page);
    }

    public Team copyPropertiesUtils(Team team, TeamQuery teamQuery){
        team.setId(teamQuery.getId());
        team.setDescription(teamQuery.getDescription());
        team.setName(teamQuery.getName());
        team.setMaxNum(teamQuery.getMaxNum());
        team.setExpireTime(teamQuery.getExpireTime());
        team.setStatus(teamQuery.getStatus());
        return team;
    }


}
