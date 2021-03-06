package com.pholser.dulynoted;

import com.pholser.dulynoted.annotated.AnnotationsGalore;
import com.pholser.dulynoted.annotations.Atom;
import com.pholser.dulynoted.annotations.Iota;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Parameter;

import static com.pholser.dulynoted.Presences.DIRECT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AnnotatedPathFromParameterToDeclaringMethodTest {
  private AnnotatedPath path;

  @BeforeEach void setUp() throws Exception {
    Parameter p =
      AnnotationsGalore.class
        .getMethod("foo", int.class)
        .getParameters()[0];
    path =
      AnnotatedPath.fromParameter(p)
        .toDeclaringMethod()
        .build();
  }

  @Test void findFirstOnParameter() {
    Atom a =
      path.findFirst(Atom.class, DIRECT)
        .orElseGet(() -> fail("Missing annotation"));

    assertEquals(4, a.value());
  }

  @Test void findFirstOnMethodOfParameter() {
    Iota i =
      path.findFirst(Iota.class, DIRECT)
        .orElseGet(() -> fail("Missing annotation"));

    assertEquals(5, i.value());
  }
}
