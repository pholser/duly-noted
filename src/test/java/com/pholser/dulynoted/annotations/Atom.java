package com.pholser.dulynoted.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Inherited
public @interface Atom {
  int value();

  int otherValue() default -1;
}
