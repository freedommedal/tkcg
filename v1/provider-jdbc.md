## 提供者id
`jdbc`

## 功能描述
抽取数据库表结构,目前只支持mysql数据库

## 提供者属性
- `url` jdbc的连接url
- `username` 用户名
- `password` 密码

## 提供者返回结构
```
表名1:
  tableName 表名
  className 驼峰转换表名,首字母大写 比如表名user_role,返回UserRole
  primaryColumn 主键字段名(只支持一个主键,不要问我为什么)
  primaryName 驼峰转换主键字段名,比如字段名user_id,返回userId
  primaryAuto 主键是否自增,常见于分库分表使用外部生成主键
  imports java类型Set集合,用于生成java文件时,包导入明细
  columns 字段集合
    columnName 字段名
    property 驼峰转换字段名 比如字段名login_id,返回loginId
    comment 字段注释
    size 字段长度
    jdbcType jdbc类型
    javaType java类型
表名2:
  如上结构
```
