create table t_area
(
  id      bigint auto_increment
    primary key,
  name    varchar(45) null,
  city_id bigint      null
)
  comment '区表';

create table t_city
(
  id          bigint auto_increment
    primary key,
  name        varchar(45) null,
  province_id bigint      null
)
  comment '市表';

create table t_province
(
  id   bigint auto_increment
    primary key,
  name varchar(45) null
)
  comment '省表';

create table t_sys_authority
(
  id         bigint auto_increment
    primary key,
  archive    tinyint(1) default '0' null,
  version    int default '1'        null,
  created_at datetime               null,
  updated_at datetime               null,
  user_id    bigint                 null,
  menu_name  varchar(127)           null
  comment '权限名称'
)
  comment '后台用户权限表';

create table t_sys_user
(
  id         bigint auto_increment
    primary key,
  archive    tinyint(1) default '0' null,
  version    int default '1'        null,
  created_at datetime               null,
  updated_at datetime               null,
  name       varchar(127)           null
  comment '姓名',
  username   varchar(127)           null,
  password   varchar(127)           null
)
  comment '后台用户表';

create table t_we_chat_config
(
  id           bigint auto_increment
    primary key,
  archive      tinyint(1) default '0' null,
  version      int default '1'        null,
  created_at   datetime               null,
  updated_at   datetime               null,
  app_id       varchar(127)           null,
  app_secret   varchar(127)           null,
  access_token varchar(127)           null,
  expired_at   datetime               null
)
  comment '微信配置表';

create table t_we_chat_user
(
  id          bigint auto_increment
    primary key,
  archive     tinyint(1) default '0' null,
  version     int default '1'        null,
  created_at  datetime               null,
  updated_at  datetime               null,
  open_id     varchar(255)           null,
  union_id    varchar(255)           null,
  session_key varchar(255)           null
  comment '登录态',
  phone       varchar(45)            null
  comment '手机号',
  gender      tinyint(1) default '0' null
  comment '性别0未知 1男 2女',
  nickname    varchar(127)           null
  comment '昵称',
  avatar      varchar(255)           null
  comment '头像',
  username    varchar(127)           null
  comment '用户名',
  password    varchar(255)           null
  comment '密码'
)
  comment '微信用户表';

