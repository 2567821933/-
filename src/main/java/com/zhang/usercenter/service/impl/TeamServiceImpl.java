package com.zhang.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.usercenter.model.domain.Team;
import com.zhang.usercenter.service.TeamService;
import com.zhang.usercenter.mapper.TeamMapper;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【team(队伍)】的数据库操作Service实现
* @createDate 2024-12-20 16:27:02
*/
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService{

}




