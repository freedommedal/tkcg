<#macro hashEL value>${r"#{"}${value}${r"}"}</#macro>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.repository.dao.${className}Dao">

    <select id="listCount" resultType="java.lang.Integer">
        SELECT COUNT(1) FROM ${tableName}
    </select>

    <sql id="page">
        <if test="page != null">
            LIMIT <@hashEL "page.offset"/>, <@hashEL "page.pageSize"/>
        </if>
    </sql>

    <select id="list" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableName}
        <include refid="page"/>
    </select>
</mapper>