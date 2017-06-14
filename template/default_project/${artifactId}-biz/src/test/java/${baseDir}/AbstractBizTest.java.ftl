package ${basePackage};

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * test
 *
 * @author ${author}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { BizTestApplication.class })
public abstract class AbstractBizTest {
}
