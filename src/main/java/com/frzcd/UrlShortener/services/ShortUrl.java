package com.frzcd.UrlShortener.services;

import com.frzcd.UrlShortener.dao.DataDao;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ShortUrl {

    public static final int SHORT_URL_LEN = 6;
    DataDao dataDao;
    char[] data;
    int dataLength;

    public ShortUrl(DataDao dataDao) {
        this.dataDao = dataDao;
        data = dataDao.getData().toCharArray();
        dataLength = data.length;
    }

    public String makeRandomShortUrl() {
        char letter1 = getRandomChar();
        char letter2 = getRandomChar();
        char letter3 = getRandomChar();
        char letter4 = getRandomChar();
        char letter5 = getRandomChar();
        char letter6 = getRandomChar();

        return "" + letter1 + letter2 + letter3 + letter4 + letter5 + letter6;
    }

    private char getRandomChar() {
        return data[ThreadLocalRandom.current().nextInt(dataLength)];
    }
}
