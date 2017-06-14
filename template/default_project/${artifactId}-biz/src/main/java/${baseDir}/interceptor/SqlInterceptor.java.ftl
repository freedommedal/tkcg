package ${basePackage}.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sql层拦截器
 *
 * @author ${author}
 */
public class SqlInterceptor implements MethodInterceptor {

    private static final Logger SQL_DIGEST_LOGGER = LoggerFactory.getLogger("sql-digest");

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        String appName = "${appName}";
        // 是否成功
        String hasError = "F";
        Object result;
        long start = System.currentTimeMillis();
        try {
            result = invocation.proceed();
            hasError = "T";
        } finally {
            long diffTime = System.currentTimeMillis() - start;
            SQL_DIGEST_LOGGER.info("({},{}.{},{},{}ms)", appName, className, methodName, hasError, diffTime);
        }
        return result;
    }
}
