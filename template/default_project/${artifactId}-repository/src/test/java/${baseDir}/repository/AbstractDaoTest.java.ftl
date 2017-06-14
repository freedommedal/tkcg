package ${basePackage}.repository;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * test
 *
 * @author ${author}
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DruidDataSourceAutoConfigure.class, DataSourceTransactionManagerAutoConfiguration.class,
    MybatisAutoConfiguration.class}, webEnvironment = WebEnvironment.NONE)
@MapperScan("${basePackage}.repository.dao")
@Transactional
public abstract class AbstractDaoTest {
}
