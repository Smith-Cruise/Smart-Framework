package org.annotation;

import java.lang.annotation.*;

/**
 * Created by Smith on 2017/4/22.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
