package com.soft.AOP;

import com.soft.datasource.DataSourceContextHolder;
import com.soft.util.DataBase;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Aspect
@Component
public class DateBaseAOP {


    /**
     * @param joinPoint
     * 方法上有DataBase注解则获取DataBse中value值，动态注入数据源
     *
     */
    @Around(value="execution(* com.soft.service.*.*(..))")
    public Object aroundService(ProceedingJoinPoint joinPoint){

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Annotation[]  Annotations = methodSignature.getMethod().getDeclaredAnnotations();
        DataBase dataBase = findDataBase(Annotations);

        Object value=null;

        if(dataBase!=null){
            DataSourceContextHolder. setDbType(dataBase.value());
        }
        try {
          value=joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        if(dataBase!=null){
            DataSourceContextHolder.clearDbType();
        }
        return value;
    }

    /**
     *
     * @param  annotations 查询方法上@DataBase注解是否存在
     * @return  存在返回@DataBase注解对象，不存在则返回null
     */
    DataBase findDataBase( Annotation[] annotations ) {
        for ( Annotation annotation : annotations ) {
            if (annotation.annotationType() == DataBase.class) {
                return DataBase.class.cast(annotation);
            }
        }
        return null;
    }

}
