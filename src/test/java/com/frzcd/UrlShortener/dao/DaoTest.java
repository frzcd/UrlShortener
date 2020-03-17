package com.frzcd.UrlShortener.dao;


import com.frzcd.UrlShortener.services.ShortUrl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

@DisplayName("Jdbc local test")
@JdbcTest
@Import({DaoJdbc.class, ShortUrl.class, DataDaoJdbc.class})
public class DaoTest {

    @Autowired
    Dao dao;
    @Autowired
    ShortUrl shortUrl;

    @DisplayName("Get by long test")
    @Test
    void shortsShouldBeEqual() {
        String shortByLong = dao.getShortByLong("http://htmlbook.ru/html/img");
        Assertions.assertEquals("123bcd", shortByLong);
    }

    @DisplayName("Get by long test 2")
    @Test
    void shortsShouldBeEqual2() {
        String shortByLong = dao.getShortByLong("https://www.google.com/");
        Assertions.assertEquals("111111", shortByLong);
    }

    @DisplayName("Get by short test")
    @Test
    void longShouldBeEqual() {
        String longByShort = dao.getLongByShort("111111");
        Assertions.assertEquals("https://www.google.com/", longByShort);
    }

    @DisplayName("Is Long Exist")
    @Test
    void checkIsLongExist() {
        Assertions.assertEquals(0, dao.checkIsLongExists("asdad"));
        Assertions.assertEquals(1, dao.checkIsLongExists("https://www.google.com/"));
    }

    @DisplayName("Is Short Exist")
    @Test
    void checkIsShortExist() {
        Assertions.assertEquals(0, dao.checkIsShortExists("eeeeee"));
        Assertions.assertEquals(1, dao.checkIsShortExists("111111"));
    }

    @DisplayName("AddNewShort Test")
    @Test
    void addNewShortTest() {
        String shortOne = dao.addNewShort("https://www.youtube.com/");
        String shortTwo = dao.addNewShort("https://www.youtube.com/");
        String shortThree = dao.addNewShort("https://www.ube.com/watch?v=z42U");
        Assertions.assertEquals(shortOne, shortTwo);
        Assertions.assertNotEquals(shortOne, shortThree);
        Assertions.assertNotEquals(shortTwo, shortThree);
    }

//    @DisplayName("Return the same short url if long already exists")
//    @Test
//    void sameShortIfExists() {
//        String short_exists = "1122dd";
//        String shortByLong = dao.getShortByLong("http://htmlbook.ru/html");
//
//    }
}
