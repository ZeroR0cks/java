package edu.phystech.hw5.service;

import edu.phystech.hw5.annotation.Cacheable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheableInvocationHandler implements InvocationHandler {

    private final Object target;
    private final Map<Method, Map<Object, Object>> cache = new HashMap<>();

    public CacheableInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cacheable.class) && args != null && args.length == 1) {
            Object arg = args[0];
            cache.putIfAbsent(method, new HashMap<>());
            Map<Object, Object> methodCache = cache.get(method);

            if (methodCache.containsKey(arg)) {
                return methodCache.get(arg);
            } else {
                Object result = method.invoke(target, args);
                methodCache.put(arg, result);
                return result;
            }
        }

        return method.invoke(target, args);
    }
}
