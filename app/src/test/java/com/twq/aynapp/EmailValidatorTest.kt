package com.twq.aynapp


import com.twq.aynapp.utility.EmailValidator
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test


/**
 * Unit tests for the EmailValidator logic.
 */
class EmailValidatorTest {
    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertEquals(EmailValidator.isValidEmail("name@email.com"),true)
    }

    @Test
    fun emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertEquals(EmailValidator.isValidEmail("name@email.co.uk"),true)
    }

    @Test
    fun emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertEquals(EmailValidator.isValidEmail("name@email"),false)
    }

    @Test
    fun emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertEquals(EmailValidator.isValidEmail("name@email..com"),false)
    }

    @Test
    fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertEquals(EmailValidator.isValidEmail("@email.com"),false)
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        assertEquals(EmailValidator.isValidEmail(""),false)
    }

    @Test
    fun emailValidator_NullEmail_ReturnsFalse() {
        assertEquals(EmailValidator.isValidEmail(null),false)
    }
}