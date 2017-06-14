package ${basePackage}.common.util;

import java.util.Locale;

import ${basePackage}.common.error.ErrorBase;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * test
 *
 * @author ${author}
 */
public class TestMessagesBundle {

    @Test
    public void messageTest() {
        String message = MessagesBundle.message(ErrorBase.SYSTEM_SUCCESS.code());
        Assertions.assertThat(message).isEqualTo("成功");
    }

    @Test
    public void messageLocalTest() {
        String message = MessagesBundle.message(Locale.CHINA, ErrorBase.SYSTEM_SUCCESS.code());
        Assertions.assertThat(message).isEqualTo("成功");
    }

    @Test
    public void messageArgsTest() {
        String message = MessagesBundle.message(ErrorBase.PARAMS_NOT_NULL.code(), "name");
        Assertions.assertThat(message).isEqualTo("name 不能为NUll");
    }
}
