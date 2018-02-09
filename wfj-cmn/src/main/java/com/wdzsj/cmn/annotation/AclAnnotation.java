package com.wdzsj.cmn.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解相关设置
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AclAnnotation {

    /**
     * 模块
     * @return
     */
    String mdul() default "";

    /**
     * 级别
     * @return
     */
    int level() default 1;
}
