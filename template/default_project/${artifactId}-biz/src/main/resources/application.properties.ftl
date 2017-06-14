# app
server.port=7001
# web
spring.profiles.active=project
spring.application.name=${appName}
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=GMT+8
spring.mvc.date-format=yyyy-MM-dd HH:mm:ss
# mybatis
mybatis.config-location=classpath:/mybatis-config.xml
mybatis.mapperLocations=classpath:mybatis/**/*.xml