package ${basePackage}.interceptor;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sal层拦截器
 *
 * @author ${author}
 */
public class SalInterceptor implements MethodInterceptor {

    private static final Logger SAL_DIGEST_LOGGER = LoggerFactory.getLogger("sal-digest");
    private static final Logger SAL_LOGGER = LoggerFactory.getLogger("sal");

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        String packageName = method.getDeclaringClass().getPackage().getName();
        String appName = StringUtils.substringAfterLast(packageName, ".");
        Object[] arguments = invocation.getArguments();
        // 是否成功
        String hasError = "F";
        Object result = null;
        SAL_LOGGER.info("{},{}.{} >>>> args:{}", appName, className, methodName, JSON.toJSONString(arguments));
        long start = System.currentTimeMillis();
        try {
            result = invocation.proceed();
            hasError = "T";
        } finally {
            long diffTime = System.currentTimeMillis() - start;
            SAL_LOGGER.info("{},{}.{} <<<< result:{}", appName, className, methodName, JSON.toJSONString(result));
            SAL_DIGEST_LOGGER.info("({},{}.{},{},{}ms)", appName, className, methodName, hasError, diffTime);
        }
        return result;
    }
}
