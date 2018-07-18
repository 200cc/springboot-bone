package me.cc200.base.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 针对函数的日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomLog {

    /**
     * 日志名称
     * @return
     */
    String name();

    /**
     * 日志类型
     * @return
     */
    String type();

    /**
     * 日志描述
     * @return
     */
    String desc() default "";

    /**
     * 是否记录函数的参数
     * @return
     */
    boolean isLogParams() default false;

    /**
     * 是否记录函数的返回
     * @return
     */
    boolean isLogResult() default false;
}
