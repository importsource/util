package com.importsource.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})   //用于字段，方法，参数
@Retention(RetentionPolicy.RUNTIME) //在运行时加载到Annotation到JVM中
public @interface Field_Method_Parameter_Annotation {
    Class type() default void.class;  //定义一个具有默认值的Class型成员
    String describ();    //定义一个没有默认值的String成员
}