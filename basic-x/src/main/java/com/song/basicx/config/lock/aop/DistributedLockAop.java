package com.song.basicx.config.lock.aop;

import com.song.basicx.Constants.SymbolConstant;
import com.song.basicx.config.exception.BizException;
import com.song.basicx.config.lock.annotation.DistributedLock;
import com.song.basicx.config.lock.annotation.LockKeyFields;
import com.song.basicx.enums.ResultCodeEnum;
import com.song.basicx.utils.ApiResultUtil;
import com.song.basicx.utils.SpelUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * 分布式锁aop
 */
@Slf4j
@Aspect
@Component
public class DistributedLockAop {

    private static final String CONSTANT_ARG_NAME = "argName";
    @Resource
    private RedissonClient redissonClient;

    @Pointcut("@annotation(com.song.basicx.config.lock.annotation.DistributedLock)")
    public void distributedLockAspect() {
    }

    @Around("distributedLockAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        /// 生成锁的key，采用@DistributedLock注解中的key值
        // 切点所在的类
        Class<?> targetClass = pjp.getTarget().getClass();
        // 切点所在的方法
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 方法名
        String methodName = signature.getName();
        // 方法参数类型
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        // 方法参数名
        String[] argsName = signature.getParameterNames();
        // 方法参数值
        Object[] args = pjp.getArgs();
        // 根据方法名及参数类型获取方法
        Method method = targetClass.getMethod(methodName, parameterTypes);
        // 获取方法上分布式锁注解
        DistributedLock lockAnnotation = method.getAnnotation(DistributedLock.class);
        // 获取方法的参数数组, 遍历每个参数上的注解, 根据LockKeyFields注解组装锁的key
        Parameter[] parameters = method.getParameters();
        List<Map<String, Object>> argsLockFields = new ArrayList<>();
        if (parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i].isAnnotationPresent(LockKeyFields.class)) {
                    Map<String, Object> argLockFieldMap = HashMap.newHashMap(3);
                    String[] fieldsValue = parameters[i].getAnnotation(LockKeyFields.class).value();
                    argLockFieldMap.put("value", args[i]);
                    argLockFieldMap.put("lockFields", fieldsValue);
                    argLockFieldMap.put(CONSTANT_ARG_NAME, argsName[i]);
                    argsLockFields.add(argLockFieldMap);
                }
            }
        }
        StringBuilder lockKeyBuilder = new StringBuilder();
        lockKeyBuilder.append(lockAnnotation.key());
        lockKeyBuilder.append(SymbolConstant.COLON);
        if (!argsLockFields.isEmpty()) {
            String pattern;
            for (Map<String, Object> m : argsLockFields) {
                String[] lockFields = (String[]) m.get("lockFields");
                for (String lockField : lockFields) {
                    pattern = SymbolConstant.HASH_KEY + m.get(CONSTANT_ARG_NAME) + SymbolConstant.DOT + lockField;
                    lockKeyBuilder.append(SpelUtil.build(pattern, (String) m.get(CONSTANT_ARG_NAME), m.get("value"), String.class));
                    lockKeyBuilder.append(SymbolConstant.COLON);
                }
            }
        }
        String lockKey = lockKeyBuilder.toString();
        RLock lock = redissonClient.getLock(lockKey);
        boolean locked = false;
        try {
            locked = lock(lockAnnotation, lock);
        } catch (Exception e){
            log.error("lock error, lockKey is: {}, config is: {}", lockKey, lockAnnotation, e);
            throw new BizException(ResultCodeEnum.FAILED);
        } finally {
            if (lock != null) {
                lock.forceUnlock();
            }
        }
        if (!locked) {
            log.warn("can't get lock, lockKey is: {}, config is: {}", lockKey, lockAnnotation);
            throw new BizException(ResultCodeEnum.BUSINESS_BUSY);
        } else {
            return pjp.proceed();
        }
    }

    private String getLockNameBySpel(Method method) {
        // todo extract method
        return method.getName();
    }

    private String getLockName(Method method, Object[] args) {
        return method.getName() + "_" + args;
    }

    /**
     * 分布式锁-加锁, leaseTime自动释放
     *
     * @param distributedLock 锁配置信息
     * @return 锁对象
     * @throws InterruptedException 异常
     */
    private boolean lock(DistributedLock distributedLock, RLock lock) throws InterruptedException {
        // 是否获取到锁
        if (distributedLock.waiting()) {
             return lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());
        } else {
            // 获取不到锁直接返回false，不等待，获取到，不执行unlock不会释放锁
            return lock.tryLock();
        }
    }

}
