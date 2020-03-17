package com.frzcd.UrlShortener.dao;

public interface Dao {

    public String getShortByLong(String long_url);

    public String getLongByShort(String short_url);

    public String addNewShort(String long_url);

    public int checkIsLongExists(String long_url);

    public int checkIsShortExists(String short_url);

    public int getUrlsCount();
}
