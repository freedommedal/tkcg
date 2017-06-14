<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">


    <bean id="apiInterceptor" class="${basePackage}.interceptor.ApiInterceptor"/>
    <bean id="restInterceptor" class="${basePackage}.interceptor.RestInterceptor"/>
    <bean id="sqlInterceptor" class="${basePackage}.interceptor.SqlInterceptor"/>
    <bean id="salInterceptor" class="${basePackage}.interceptor.SalInterceptor"/>

    <aop:config>
        <aop:pointcut id="apiPointcut" expression="execution(* ${basePackage}.api..*.*(..))"/>
        <aop:pointcut id="restPointcut" expression="execution(* ${basePackage}.rest..*.*(..))"/>
        <aop:pointcut id="sqlPointcut" expression="execution(* ${basePackage}.repository.dao..*.*(..))"/>
        <aop:pointcut id="salPointcut" expression="execution(* ${basePackage}.sal..*.*(..))"/>

        <aop:advisor pointcut-ref="apiPointcut" advice-ref="apiInterceptor" order="1"/>
        <aop:advisor pointcut-ref="restPointcut" advice-ref="restInterceptor" order="1"/>
        <aop:advisor pointcut-ref="sqlPointcut" advice-ref="sqlInterceptor" order="1"/>
        <aop:advisor pointcut-ref="salPointcut" advice-ref="salInterceptor" order="1"/>
    </aop:config>
</beans>
