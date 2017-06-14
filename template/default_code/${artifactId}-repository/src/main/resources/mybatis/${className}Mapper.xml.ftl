<#macro hashEL value>${r"#{"}${value}${r"}"}</#macro>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.repository.dao.${className}Dao">

    <resultMap id="BaseResultMap" type="${basePackage}.repository.entity.${className}">
<#list columns as column>
    <#if column.columnName == primaryColumn>
        <id column="${primaryColumn}" jdbcType="BIGINT" property="${primaryName}"/>
    <#else> 
        <result column="${column.columnName}" jdbcType="${column.jdbcType}" property="${column.property}"/>
    </#if>
</#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list columns as column>${column.columnName}<#sep>, </#sep></#list>
    </sql>

    <insert id="create" parameterType="${basePackage}.repository.entity.${className}"<#if primaryAuto!true> useGeneratedKeys="true" keyProperty="${primaryName}"</#if>>
        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
<#if !(primaryAuto!true)>
              ${primaryColumn},
</#if>
<#list columns as column>
    <#if column.columnName != primaryColumn>
            <if test="${column.property} != null">
                ${column.columnName},
            </if>
    </#if>
</#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
<#if !(primaryAuto!true)>
              <@hashEL "${primaryName}"/>,
</#if>
<#list columns as column>
    <#if column.columnName != primaryColumn>
            <if test="${column.property} != null">
                <@hashEL column.property/>,
            </if>
    </#if>
</#list>
        </trim>
    </insert>

    <delete id="delete">
        DELETE FROM ${tableName} WHERE ${primaryColumn} = <@hashEL "${primaryName}"/>
    </delete>

    <update id="update" parameterType="${basePackage}.repository.entity.${className}">
        UPDATE ${tableName}
        <set>
<#list columns as column>
    <#if column.columnName != primaryColumn>
            <if test="${column.property} != null">
                ${column.columnName} = <@hashEL column.property/>,
            </if>
    </#if>
</#list>
        </set>
        WHERE ${primaryColumn} = <@hashEL "${primaryName}"/>
    </update>

    <select id="query" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/> 
        FROM ${tableName}
        WHERE ${primaryColumn} = <@hashEL "${primaryName}"/>
    </select>
</mapper>