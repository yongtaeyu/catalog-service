package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsAreValid() {
        var book = new Book("12345", "title", "홍길동", 9.990);
        Set<ConstraintViolation<Book>> validate = validator.validate(book);
        assertTrue(!validate.isEmpty());
    }

    @Test
    void 유효하지않은책을생성() {
        var book = new Book("12345", "title", "홍길동", 9.990);

        Set<ConstraintViolation<Book>> validate = validator.validate(book);

        assertTrue(validate.size() > 0);
        assertTrue(validate.iterator().next().getMessage()
                .equals("The ISBN format must be valid."));
    }
}
