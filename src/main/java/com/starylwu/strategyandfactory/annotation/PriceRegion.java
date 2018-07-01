package com.starylwu.strategyandfactory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/1 09:23
 * @Description: 标识有效的价格区间
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceRegion {

    int max() default Integer.MAX_VALUE;
    int min() default Integer.MIN_VALUE;
}
