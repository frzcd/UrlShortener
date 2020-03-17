package com.frzcd.UrlShortener.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@DisplayName("ShortUrl tests")
@SpringBootTest
@Import(ShortUrl.class)
public class ShortUrlTest {
    @Autowired
    ShortUrl shortUrl;

    @DisplayName("is data correct")
    @Test
    void isDataCorrect() {
        Assertions.assertEquals("123456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ", shortUrl.dataDao.getData());
    }

    @DisplayName("check data array length")
    @Test
    void dataArrayLengthTest() {
        System.out.println(shortUrl.dataLength);
        Assertions.assertEquals(50, shortUrl.dataLength);
    }
}
