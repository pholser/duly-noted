package com.pholser.dulynoted;

import com.pholser.dulynoted.annotated.X;
import com.pholser.dulynoted.annotations.Atom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.AnnotatedElement;

import static com.pholser.dulynoted.Presences.ASSOCIATED;
import static com.pholser.dulynoted.Presences.DIRECT;
import static com.pholser.dulynoted.Presences.DIRECT_OR_INDIRECT;
import static com.pholser.dulynoted.Presences.PRESENT;
import static com.pholser.dulynoted.annotations.Annotations.annoValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SingleNonRepeatableAnnotationDeclaredOnNonClassTest {
  private AnnotatedElement target;

  @BeforeEach void setUp() throws Exception {
    target = X.class.getDeclaredField("i");
  }

  @Test void findDirect() {
    Atom a =
      DIRECT.find(Atom.class, target)
        .orElseGet(() -> fail("Missing annotation"));

    assertEquals(1, a.value());
  }

  @Test void allDirect() {
    assertThat(
      DIRECT.all(target),
      containsInAnyOrder(annoValue(Atom.class, 1)));
  }

  @Test void findAllDirectOrIndirect() {
    assertThat(
      DIRECT_OR_INDIRECT.findAll(Atom.class, target),
      containsInAnyOrder(annoValue(Atom.class, 1)));
  }

  @Test void findPresent() {
    Atom a =
      PRESENT.find(Atom.class, target)
        .orElseGet(() -> fail("Missing annotation"));

    assertEquals(1, a.value());
  }

  @Test void allPresent() {
    assertThat(
      PRESENT.all(target),
      containsInAnyOrder(annoValue(Atom.class, 1)));
  }

  @Test void findAllAssociated() {
    assertThat(
      ASSOCIATED.findAll(Atom.class, target),
      containsInAnyOrder(annoValue(Atom.class, 1)));
  }
}
