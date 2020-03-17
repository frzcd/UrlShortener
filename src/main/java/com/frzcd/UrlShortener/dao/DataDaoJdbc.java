package com.frzcd.UrlShortener.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DataDaoJdbc implements DataDao {
    private final NamedParameterJdbcOperations jdbc;

    public DataDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public String getData() {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", 1);
        return jdbc.queryForObject("select data from shortener where id = 1", params, String.class);
    }
}
