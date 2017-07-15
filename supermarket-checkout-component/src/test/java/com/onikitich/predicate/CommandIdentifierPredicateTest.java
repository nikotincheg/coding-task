package com.onikitich.predicate;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommandIdentifierPredicateTest {

    @Test
    public void whenTestCompleteCommandForIncorrectIdentifierThenFalseShouldBeReturned() {
        assertFalse(CommandIdentifierPredicate.COMPLETE.test("incorrect"));
    }

    @Test
    public void whenTestCompleteCommandForCorrectIdentifierThenFalseShouldBeReturned() {
        assertTrue(CommandIdentifierPredicate.COMPLETE.test("complete"));
        assertTrue(CommandIdentifierPredicate.COMPLETE.test(" complete   "));
    }

    @Test
    public void whenTestNewCommandForIncorrectIdentifierThenFalseShouldBeReturned() {
        assertFalse(CommandIdentifierPredicate.NEW.test("incorrect"));
    }

    @Test
    public void whenTestNewCommandForCorrectIdentifierThenFalseShouldBeReturned() {
        assertTrue(CommandIdentifierPredicate.NEW.test("new"));
        assertTrue(CommandIdentifierPredicate.NEW.test(" new   "));
    }

    @Test
    public void whenTestExitCommandForIncorrectIdentifierThenFalseShouldBeReturned() {
        assertFalse(CommandIdentifierPredicate.EXIT.test("incorrect"));
    }

    @Test
    public void whenTestExitCommandForCorrectIdentifierThenFalseShouldBeReturned() {
        assertTrue(CommandIdentifierPredicate.EXIT.test("exit"));
        assertTrue(CommandIdentifierPredicate.EXIT.test(" exit   "));
    }

    @Test
    public void whenTestEmptyCommandForIncorrectIdentifierThenFalseShouldBeReturned() {
        assertFalse(CommandIdentifierPredicate.EMPTY.test("not empty"));
    }

    @Test
    public void whenTestEmptyCommandForCorrectIdentifierThenFalseShouldBeReturned() {
        assertTrue(CommandIdentifierPredicate.EMPTY.test(""));
        assertTrue(CommandIdentifierPredicate.EMPTY.test("    "));
    }
}
