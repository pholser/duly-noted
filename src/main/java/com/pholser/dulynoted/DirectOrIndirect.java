package com.pholser.dulynoted;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.List;

/**
 * An object that can find annotations that are "directly present" or
 * "indirectly present" on a program element.
 */
final class DirectOrIndirect implements AllByType {
  @Override public <A extends Annotation> List<A> findAll(
    Class<A> annoType,
    AnnotatedElement target) {

    return List.of(target.getDeclaredAnnotationsByType(annoType));
  }
}
