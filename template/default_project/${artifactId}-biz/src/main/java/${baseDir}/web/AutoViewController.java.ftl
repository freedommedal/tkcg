package ${basePackage}.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

/**
 * 自动渲染html后缀模板
 *
 * @author ${author}
 */
@Controller
public class AutoViewController {

    @RequestMapping(value = "/**/*.html")
    public String autoView(HttpServletRequest request, HttpServletResponse response) {
        String path = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        path = path.replace(".html", "");
        return path;
    }
}
