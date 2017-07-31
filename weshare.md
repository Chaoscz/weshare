[TOC]

## weShare 

> 微晒 轻分享 分享点滴快乐



```json
Mysql 连接信息 ::
host：47.52.61.41
port：3306
user：user001
pwd：14715912300.mm
```

### h_account 用户表

| 字段名      | 类型       | 长度   | 是否为空 | 备注                  |
| -------- | -------- | :--- | ---- | ------------------- |
| id       | int      | 11   | 否    | 用户id                |
| gid      | int      | 11   | 否    | 用户组（1为普通用户）         |
| nick     | varchar  | 100  | 否    | 昵称                  |
| headimg  | varchar  | 200  | 是    | 头像                  |
| tel      | int      | 11   | 是    | 电话                  |
| ctime    | datetime |      |      | 创建日期哦               |
| state    | int      | 1    | 否    | 账号状态（默认1，0为冻结，2为删除） |
| score    | int      |      | 否    | 积分                  |
| grade    | int      |      | 否    | 用户等级，默认1            |
| birthday | datetime |      | 是    | 生日，默认空              |
| pwd      | varchar  | 50   | 否    | 密码                  |

```sql
--插入用户信息
INSERT INTO h_account(nick,headimg,tel,ctime)
VALUES(
'Glacier',
'http://wx.qlogo.cn/mmopen/YChLVIdvaSXAAUCnj022kcVSNiaeeRo9SqhxTibrPia2pB8HFmlDq9wjRvPeOjUL6mxeyVTE0S9DIV00JwFSrntHHVwtJcO414H/0',
'13776390465',
NOW())

--写查询语句的时候不要用 select * from 切记切记
```

### h_admin 管理员表

| 字段名      | 类型       | 长度   | 是否为空 | 备注    |
| -------- | -------- | ---- | ---- | ----- |
| id       | int      | 11   | 否    | 管理员id |
| username | varchar  | 50   | 否    | 用户名   |
| nick     | varchar  | 50   | 是    | 昵称    |
| ctime    | datetime |      | 否    | 创建时间  |
| describe | varchar  | 200  |      | 描述    |
| state    | int      | 1    | 否    | 是否有效  |
| pwd      | varchar  | 50   | 否    | 密码    |

```sql
--插入语句
INSERT INTO h_admin(username,nick,ctime,`describe`)
VALUES('glacier','傅百万',NOW(),'愿有岁月可回首.')
```

### h_comment 评论表

| 字段名       | 类型       | 长度   | 是否为空 | 备注           |
| --------- | -------- | ---- | ---- | ------------ |
| id        | int      | 11   | 否    | 评论id         |
| describe  | varchar  | 200  | 否    | 评语           |
| ctime     | datetime |      | 否    | 创建时间         |
| parentcom | int      |      | 否    | 父级评论id（默认为0） |
| artid     | int      | 11   | 否    | 父级文章id（默认为0） |
| aid       | int      | 11   | 否    | 用户id         |
| state     | int      | 2    | 否    | 状态（0删除，1显示）  |

```sql
--插入语句 aid 是用户id 用做关联用户表
INSERT INTO h_comment(`describe`,ctime,aid)
VALUES ('写点什么吧',NOW(),'1')
```

### h_notice 系统公告表

| 字段名     | 类型       | 长度   | 是否为空 | 备注   |
| ------- | -------- | ---- | ---- | ---- |
| id      | int      | 11   | 否    | 自增id |
| title   | varchar  | 50   | 否    | 标题   |
| info    | varchar  | 255  | 否    | 内容   |
| ctime   | datetime |      | 是    | 创建时间 |
| cauthor | int      | 11   | 否    | 创建者  |

```sql
INSERT INTO h_notice(title,info,ctime,cauthor)
VALUES('这是标题','这是详情',NOW(),'1')
```

### h_article 文章表

| 字段名      | 类型       | 长度   | 是否为空 | 备注          |
| -------- | -------- | ---- | ---- | ----------- |
| id       | int      | 11   | 否    | 自增id        |
| aid      | int      | 11   | 否    | 用户id        |
| ctime    | datetime |      | 否    | 创建时间        |
| lctime   | datetime |      |      | 最后修改时间      |
| comsum   | int      | 11   | 否    | 评论数         |
| title    | varchar  | 50   | 否    | 标题          |
| artlabel | varchar  | 200  | 是    | 标签          |
| watchsum | int      | 11   | 否    | 浏览数         |
| isexc    | int      | 2    | 否    | 是否为精华帖（默认0） |

### h_article_detail 文章内容表

| 字段名     | 类型   | 长度   | 是否为空 | 备注   |
| ------- | ---- | ---- | ---- | ---- |
| id      | int  | 11   | 否    | 自增id |
| artid   | int  | 11   | 否    | 文章id |
| content | text |      | 否    | 内容   |



角色：超级管理员，版主，普通用户

描述：超级管理员有删除用户的权限（修改用户的状态）