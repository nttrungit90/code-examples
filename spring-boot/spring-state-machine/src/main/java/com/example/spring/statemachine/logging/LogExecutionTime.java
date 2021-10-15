package com.example.spring.statemachine.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation used for logging execution time of any method
 *
 * @author trung.nguyenthanh
 * @See com.example.springaop.logging.LoggingAspect
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogExecutionTime {

}
