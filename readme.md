**项目结构说明**
```bash
基于springboot的小程序项目框架
dal-持久层
biz-业务层
api-控制层
docs-说明文档
```
**接口风格**
```bash
restful(请求方式建议)
post-保存
put-修改
get-查询
delete-删除
```
**数据传递规范**
```bash
1.所有接口返回数据一律使用VO对象,业务异常一律返回null
2.所有联表查询数据一律使用DO对象
```

**数据库操作规范**
```bash
1.表设计
  
  基础字段:
  id         bigint(20) auto_increment primary key comment '主键',
  archive    tinyint(1) default '0' null comment '逻辑删除(0正常 1删除)',
  version    int default '1'        null commnet '版本号(用于乐观锁)',
  created_at datetime               null comment '创建时间',
  updated_at datetime               null comment '更新时间',
  
  设计规范:
  (1)表名使用统一前缀,例如qy_user,qy_store
  (2)所有命名不允许出现驼峰式,一律使用下划线分割,字段不要大小写混用
  (3)能用int不要使用varchar,varchar长度视情况而定
  (4)尽量遵循三大范式
  (5)避免使用null字段(null字段很难查询优化,null字段的索引需要额外空间,null字段的复合索引无效)
  (6)少用text类型,尽量使用varchar代替text
  (7)合理使用索引, 谨慎建立复合索引
  (8)sql语句尽可能简单,避免使用触发器,函数
  (9)使用union all代替union(union有去重开销)
  (10)性能分析使用explain
  (11)建表时除了基础字段以外,新加字段建议加上注释,便于他人理解
  
2.表操作
  项目基于mybatisplus框架执行数据操作
  mybatisplus注意事项:mybatisplus一般基于单表操作
  
  mapper层:
  (1)添加-insert
  (2)更新-update(务必更新时间)
  (3)删除-update(务必更新时间,不要使用delete,更新逻辑删除字段archive为true)
  (4)查询-select
  所有自定义方法一律遵循以上前缀
  
  service层
  (1)保存-save
  (2)更新-update/modify
  (3)删除-remove/delete
  (4)查询-get/find/load/page
  所以自定义方法建议遵循以上前缀
```

**事物控制**
```bash
一般在进行多张表的增,删,改操作时
使用注解@Transactional(rollbackFor = Exception.class)
```

**并发问题**
```bash
不要使用传统的synchronized或者ReentrantLock锁,
使用分布式锁
```
