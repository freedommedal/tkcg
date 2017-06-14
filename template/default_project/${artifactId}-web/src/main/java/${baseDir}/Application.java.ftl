package ${basePackage};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Application
 *
 * @author ${author}
 */
@SpringBootApplication
@ImportResource("classpath:/spring.xml")
public class Application {

    /**
     * main
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
