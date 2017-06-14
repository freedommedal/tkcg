package ${basePackage}.repository.dao;

import java.util.List;

import ${basePackage}.common.util.PageHelper;
import ${basePackage}.repository.entity.${className};
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Dao接口-${className}
 *
 * @author ${author}
 */
@Mapper
public interface ${className}Dao {

    /**
     * 插入一条数据,实体数据不为空的字段
     *
     * @param po po
     * @return int
     */
    int create(${className} po);

    /**
     * 删除一条数据,根据主键
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 更新一条数据,根据主键,实体属性不为空的字段
     *
     * @param po po
     * @return int
     */
    int update(${className} po);

    /**
     * 查询一条数据,根据主键
     *
     * @param id id
     * @return po
     */
    ${className} query(Long id);

    /**
     * 分页查询总数-仅用于演示,切勿用于生产
     *
     * @return int
     */
    int listCount();

    /**
     * 分页查询结果-仅用于演示,切勿用于生产
     *
     * @param page  page
     * @return list
     */
    List<${className}> list(@Param("page") PageHelper page);
}