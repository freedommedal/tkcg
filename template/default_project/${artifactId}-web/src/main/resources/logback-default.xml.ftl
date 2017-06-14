<#macro dolsEL value>${r"${"}${value}${r"}"}</#macro>
<?xml version="1.0" encoding="UTF-8" ?>
<configuration scan="true">
    <property name="LOG_PATH" value="/home/admin/logs/app"/>
    <property name="LOG_PATTERN" value="%date{yyyy-MM-dd HH:mm:ss.SSS} | %-5level | %thread | %logger |  | %msg%n"/>

    <!-- Appenders -->
    <appender name="root-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <OnMismatch>NEUTRAL</OnMismatch>
            <OnMatch>ACCEPT</OnMatch>
        </filter>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>WARN</level>
            <OnMismatch>DENY</OnMismatch>
            <OnMatch>ACCEPT</OnMatch>
        </filter>
        <file><@dolsEL "LOG_PATH"/>/root.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/root.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="biz-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <OnMismatch>NEUTRAL</OnMismatch>
            <OnMatch>ACCEPT</OnMatch>
        </filter>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>WARN</level>
            <OnMismatch>DENY</OnMismatch>
            <OnMatch>ACCEPT</OnMatch>
        </filter>
        <file><@dolsEL "LOG_PATH"/>/biz.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/biz.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="error-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <OnMismatch>DENY</OnMismatch>
            <OnMatch>ACCEPT</OnMatch>
        </filter>
        <file><@dolsEL "LOG_PATH"/>/error.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/error.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="sql-digest-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><@dolsEL "LOG_PATH"/>/sql-digest.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/sql-digest.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="sql-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><@dolsEL "LOG_PATH"/>/sql.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/sql.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="api-digest-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><@dolsEL "LOG_PATH"/>/api-digest.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/api-digest.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="api-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><@dolsEL "LOG_PATH"/>/api.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/api.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="rest-digest-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><@dolsEL "LOG_PATH"/>/rest-digest.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/rest-digest.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="rest-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><@dolsEL "LOG_PATH"/>/rest.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/rest.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="sal-digest-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><@dolsEL "LOG_PATH"/>/sal-digest.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/sal-digest.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="sal-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><@dolsEL "LOG_PATH"/>/sal.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/sal.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="message-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><@dolsEL "LOG_PATH"/>/metaq-message.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/metaq-message.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <appender name="task-file"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><@dolsEL "LOG_PATH"/>/scheduler-task.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern><@dolsEL "LOG_PATH"/>/scheduler-task.log.%d{yyyy-MM-dd}</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><@dolsEL "LOG_PATTERN"/></pattern>
        </encoder>
    </appender>

    <!-- root -->
    <root level="INFO">
        <appender-ref ref="root-file"/>
        <appender-ref ref="error-file"/>
    </root>

    <!-- biz -->
    <logger name="${basePackage}" level="INFO" additivity="false">
        <appender-ref ref="biz-file"/>
        <appender-ref ref="error-file"/>
    </logger>

    <!-- dao -->
    <logger name="sql-digest" level="INFO" additivity="false">
        <appender-ref ref="sql-digest-file"/>
    </logger>
    <logger name="${basePackage}.repository.dao" level="DEBUG" additivity="false">
        <appender-ref ref="sql-file"/>
    </logger>

    <!-- api -->
    <logger name="api-digest" level="INFO" additivity="false">
        <appender-ref ref="api-digest-file"/>
    </logger>

    <logger name="api" level="INFO" additivity="false">
        <appender-ref ref="api-file"/>
    </logger>

    <!-- rest -->
    <logger name="rest-digest" level="INFO" additivity="false">
        <appender-ref ref="rest-digest-file"/>
    </logger>

    <logger name="rest" level="INFO" additivity="false">
        <appender-ref ref="rest-file"/>
    </logger>

    <!-- sal -->
    <logger name="sal-digest" level="INFO" additivity="false">
        <appender-ref ref="sal-digest-file"/>
    </logger>

    <logger name="sal" level="INFO" additivity="false">
        <appender-ref ref="sal-file"/>
    </logger>

    <!-- message -->
    <logger name="message" level="INFO" additivity="false">
        <appender-ref ref="message-file"/>
    </logger>

    <!-- task -->
    <logger name="task" level="INFO" additivity="false">
        <appender-ref ref="task-file"/>
    </logger>
</configuration>