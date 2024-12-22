package com.zhang.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.usercenter.model.domain.UserTeam;
import com.zhang.usercenter.service.UserTeamService;
import com.zhang.usercenter.mapper.UserTeamMapper;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【user_team(用户队伍关系表)】的数据库操作Service实现
* @createDate 2024-12-20 16:31:08
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}




