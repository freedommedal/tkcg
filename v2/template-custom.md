## 模板自定义

* tkcg插件在idea启动时,会检测是否存在默认模板
* 如果没有,则会创建插件自带的模板.
* 使用者,可以根据实际情况,调整模板结构
* 默认模板在`idea工作区目录\config\tkcg\template`
![](./image/template-custom.png)
* `default_project` 对应创建项目骨架,`default_code`对应创建业务代码,最好使用git管理模板