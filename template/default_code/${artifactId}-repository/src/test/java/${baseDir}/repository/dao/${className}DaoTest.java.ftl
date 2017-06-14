package ${basePackage}.repository.dao;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ${basePackage}.repository.AbstractDaoTest;
import ${basePackage}.repository.entity.${className};

/**
 * test
 * 
 * @author ${author}
 */
public class ${className}DaoTest extends AbstractDaoTest {

    @Autowired
    private ${className}Dao dao;

    @Test
    public void queryTest() {
        ${className} po = dao.query(1L);
        Assertions.assertThat(po).isNotNull();
    }
}
