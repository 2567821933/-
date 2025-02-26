-- auto-generated definition
create table user
(
    id           bigint auto_increment
        primary key,
    username     varchar(256)                       null comment '用户昵称',
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态 0-正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间默认当前',
    updataTime   datetime default CURRENT_TIMESTAMP null comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '时候删除',
    userRole     int      default 0                 not null comment '用户角色 0普通用户 1管理员',
    planetCode   varchar(512)                       null comment '星球编号',
    tags         varchar(1024)                      null comment '标签列表'
)
    comment '用户';



-- auto-generated definition
create table tag
(
    id         bigint auto_increment
        primary key,
    tagName    varchar(256)                       null comment '标签名称',
    userId     bigint                             null comment '用户id',
    parentId   bigint                             null comment '父标签id',
    isParent   tinyint                            null comment '0-不是，1-是',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间默认当前',
    updateTime datetime default CURRENT_TIMESTAMP null comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '时候删除',
    constraint unique_tagName
        unique (tagName)
)
    comment 'tag';

-- 创建队伍表
create table team
(
    id           bigint auto_increment
        primary key,
    name     varchar(256)                     not null comment '队伍昵称',
    description  varchar(1024)                       null comment '队伍描述',
    maxNum    int     default 1            not null comment '最大人数',
    expireTime       datetime  null comment '过期时间',
    userId       bigint 	null comment '用户id',
    password 	varchar(512)                       not null comment '密码',
    status   int      default 0                 not null comment '状态 0-公开 1-私有 2-加密v',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间默认当前',
    updataTime   datetime default CURRENT_TIMESTAMP null comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '时候删除'

    )
    comment '队伍';
-- 用户队伍关系表
create table user_team
(
    id           bigint auto_increment
        primary key,
    userId bigint not null comment '用户id',
    teamId bigint not null comment '队伍id',
    joinTime datetime null comment '加入时间',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间默认当前',
    updataTime   datetime default CURRENT_TIMESTAMP null comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '时候删除'

)
    comment '用户队伍关系表';
create index idx_userId
    on tag (userId);

