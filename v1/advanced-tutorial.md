## 配置文件
使用yaml格式，示例内容
```
####文件和目录路径可以是绝对地址,也可以是相对地址
#模板目录
templateDirectory: ../template/default_project
#输出目录
outDirectory: ../dest/project
#数据提供者配置,
providerConfig:
  #提供者id
  id: yaml
  #数据路径
  dataPath: project-data.yml
#全局数据
global:
  key1: value1
  key2: value2
#要生成的分组数据列表,使用","分隔,可以减少不必要的生成
keys:user,task
```

## 模板文件
freemarker模板,扩展名ftl,可以多目录多文件进行渲染,比如Hello.java.ftl会转换Hello.java

## 文件名表达式
文件名和目录名也是一种模板,支持表达式
常见使用示例  
目录名 `${artifactId}-api`  
文件名 `${className}Dao.java.ftl`

## 普通文件
扩展名不是ftl的所有文件,使用复制方式进行拷贝

## 全局数据
作用于多组数据生成过程中,可以放置一些公共数据

## 数据提供者
根据配置,调用不同提供者,加载数据  
程序自带的提供者,如下

* [提供者yaml](提供者yaml.md)
* [提供者jdbc](提供者jdbc.md)
