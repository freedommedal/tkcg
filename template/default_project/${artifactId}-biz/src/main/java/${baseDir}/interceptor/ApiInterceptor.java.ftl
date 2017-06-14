package ${basePackage}.interceptor;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${basePackage}.common.error.ErrorBase;
import ${basePackage}.common.exception.BizException;
import ${basePackage}.common.exception.SalException;
import ${basePackage}.common.util.MessagesBundle;
import com.sgota.commons.dto.GeneralResponse;

/**
 * API层拦截器
 *
 * @author ${author}
 */
public class ApiInterceptor implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiInterceptor.class);
    private static final Logger API_DIGEST_LOGGER = LoggerFactory.getLogger("api-digest");
    private static final Logger API_LOGGER = LoggerFactory.getLogger("api");

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        String appName = "${appName}";
        Object[] arguments = invocation.getArguments();
        // 是否成功
        String hasError = "F";
        Object result = null;
        API_LOGGER.info("{},{}.{} >>>> args:{}", appName, className, methodName, JSON.toJSONString(arguments));
        long start = System.currentTimeMillis();
        try {
            result = invocation.proceed();
            hasError = "T";
        } catch (BizException e) {
            GeneralResponse response = getResult(invocation);
            response.setCode(e.getCode());
            response.setMsg(e.getMessage());
            result = response;
        } catch (SalException e) {
            GeneralResponse response = getResult(invocation);
            response.setMsg(e.getMessage());
            response.setCode(e.getCode());
            result = response;
        } catch (Exception e) {
            LOGGER.error("system error", e);
            GeneralResponse response = getResult(invocation);
            response.setCode(ErrorBase.SYSTEM_ERROR.code());
            response.setMsg(MessagesBundle.message(ErrorBase.SYSTEM_ERROR.code()));
            result = response;
        } finally {
            long diffTime = System.currentTimeMillis() - start;
            API_LOGGER.info("{},{}.{} <<<< result:{}", appName, className, methodName, JSON.toJSONString(result));
            API_DIGEST_LOGGER.info("({},{}.{},{},{}ms)", appName, className, methodName, hasError, diffTime);
        }
        return result;
    }

    /**
     * Gets result.
     *
     * @param invocation invocation
     * @return result
     */
    private GeneralResponse getResult(MethodInvocation invocation) throws IllegalAccessException,
        InstantiationException {
        Class<?> returnType = invocation.getMethod().getReturnType();
        return (GeneralResponse)returnType.newInstance();
    }
}
