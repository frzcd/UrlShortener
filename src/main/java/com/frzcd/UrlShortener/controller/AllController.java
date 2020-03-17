package com.frzcd.UrlShortener.controller;

import com.frzcd.UrlShortener.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("all")
public class AllController {

    Dao dao;

//    @GetMapping
//    public List<Map<String, String>> getAll() {
//        return
//    }

    @Autowired
    public AllController(Dao dao) {
        this.dao = dao;
    }
}
