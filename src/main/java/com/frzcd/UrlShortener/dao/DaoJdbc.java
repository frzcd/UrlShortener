package com.frzcd.UrlShortener.dao;

import com.frzcd.UrlShortener.services.ShortUrl;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DaoJdbc implements Dao {

    private final NamedParameterJdbcOperations jdbc;
    ShortUrl shortUrl;

    public DaoJdbc(NamedParameterJdbcOperations jdbc, ShortUrl shortUrl) {
        this.jdbc = jdbc;
        this.shortUrl = shortUrl;
    }

    @Override
    public String getShortByLong(String long_url) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("long_url", long_url);
        return jdbc.queryForObject("select short_url from urls where long_url = :long_url", params, String.class);
    }

    @Override
    public String getLongByShort(String short_url) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("short_url", short_url);
        return jdbc.queryForObject("select long_url from urls where short_url = :short_url", params, String.class);
    }

    @Override
    public String addNewShort(String long_url) {
        if (checkIsLongExists(long_url) == 1) {
            return getShortByLong(long_url);
        } else {
            String newShort = shortUrl.makeRandomShortUrl();
            while (checkIsShortExists(newShort) != 0) {
                newShort = shortUrl.makeRandomShortUrl();
            }
            final HashMap<String, Object> params = new HashMap<>(1);
            params.put("long_url", long_url);
            params.put("short_url", newShort);
            jdbc.update("insert into urls (`long_url`, `short_url`) values (:long_url, :short_url)", params);
            return newShort;
        }
    }

    @Override
    public int checkIsLongExists(String long_url) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("long_url", long_url);
        return jdbc.queryForObject("select count(long_url) from urls where long_url = :long_url", params, Integer.class);
    }

    @Override
    public int checkIsShortExists(String short_url) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("short_url", short_url);
        return jdbc.queryForObject("select count(short_url) from urls where short_url = :short_url", params, Integer.class);
    }

    @Override
    public int getUrlsCount() {
        final HashMap<String, Object> params = new HashMap<>(1);
        return jdbc.queryForObject("select count(id) from urls", params, Integer.class);
    }
}
