package qw.openapi.sdk.api.base;

import qw.openapi.sdk.api.annotation.Service;
import qw.openapi.sdk.api.exception.ServiceException;
import qw.openapi.sdk.config.Config;
import qw.openapi.sdk.oauth.response.Token;
import qw.openapi.sdk.utils.WebUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseNopService {
    private Token token;
    private Map<String, Method> methodMap = new HashMap();
    private Class service;
    private Config config;

    public BaseNopService(Config config, Token token, Class service) {
        this.token = token;
        this.service = service;
        this.config = config;
        Method[] methods = service.getMethods();
        Method[] var5 = methods;
        int var6 = methods.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Method method = var5[var7];
            this.methodMap.put(method.getName(), method);
        }

    }

    public <T> T call(String action, Map<String, Object> parameters) throws ServiceException {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        Method method = this.getMethod(methodName);
        Service annotation = (Service)this.service.getAnnotation(Service.class);
        if(annotation == null) {
            throw new RuntimeException("服务未找到Service注解");
        } else {
            return WebUtils.call(this.config, action, parameters, this.token, method.getGenericReturnType());
        }
    }

    private Method getMethod(String methodName) {
        return (Method)this.methodMap.get(methodName);
    }
}
