package com.frzcd.UrlShortener.services;

import com.frzcd.UrlShortener.services.MyUrlValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@DisplayName("MyUrlValidator tests")
@SpringBootTest
@Import(MyUrlValidator.class)
public class UrlValidatorTest {
    @Autowired
    MyUrlValidator validator;

    @DisplayName("http url validation test")
    @Test
    void httpValidation() {
        String url = "http://test/html/img";
        Assertions.assertFalse(validator.isValid(url));
        String url2 = "http://test.ru/html/img";
        Assertions.assertTrue(validator.isValid(url2));
    }

    @DisplayName("https url validation test")
    @Test
    void httpsValidation() {
        String url = "https://test.ru/html/img";
        Assertions.assertTrue(validator.isValid(url));
        String url2 = "https://test.ru/get-gtm/";
        Assertions.assertTrue(validator.isValid(url2));
    }

    @DisplayName("ftp url validation test")
    @Test
    void ftpValidation() {
        String url = "ftp://test.ru/html/img";
        Assertions.assertTrue(validator.isValid(url));
        String url2 = "ftp:/test.ru/html/img";
        Assertions.assertFalse(validator.isValid(url2));
    }

    @DisplayName("test url with GET parameter")
    @Test
    void urlWithGetTest() {
        String url = "http://mysite.ru/?a=2&b=3";
        Assertions.assertTrue(validator.isValid(url));
    }

    @Test
    void urlTest() {
        String url = "test.com/ru/blog/article/rest-api-18";
        Assertions.assertFalse(validator.isValid(url));
    }
}
