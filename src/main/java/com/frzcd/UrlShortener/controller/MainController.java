package com.frzcd.UrlShortener.controller;

import com.frzcd.UrlShortener.dao.Dao;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("url")
public class MainController {

    Dao dao;
    UrlValidator urlValidator;

    @Value("${home.link}")
    String host;

    @GetMapping
    public Map<String, String> main() {
        return new HashMap<String, String>() {{ put("message", "main page"); }};
    }

    @GetMapping("{link}")
    public Map<String, String> redirect(@PathVariable String link) {
        if (link.length() != 6) {
            return new HashMap<String, String>() {{ put("message", "wrong short url received"); }};
        }
        if (dao.checkIsShortExists(link) != 0) {
            return new HashMap<String, String>() {{ put("message", dao.getLongByShort(link)); }};
        }
        return new HashMap<String, String>() {{ put("message", "such url is not exists"); }};
    }

    @PutMapping
    public HashMap<String, String> generateShortUrl(@RequestBody Map<String, String> request) {

        if (!urlValidator.isValid(request.get("text"))) {
            return new HashMap<String, String>() {{ put("message", "Invalid url received"); }};
        }
        if (dao.checkIsLongExists(request.get("text")) != 0) {
            return new HashMap<String, String>() {{ put("message", host + dao.getShortByLong(request.get("text"))); }};
        }
        return new HashMap<String, String>() {{ put("message", host + dao.addNewShort(request.get("text"))); }};
    }

    @PostMapping
    public Map<String, String> getLongUrlByShort(@RequestBody Map<String, String> request) {
        System.out.println(request.get("text"));
        System.out.println(host);
        String url = request.get("text")
                .replaceFirst(host, "");
        System.out.println(url);
        if (dao.checkIsShortExists(url) != 0) {
            return new HashMap<String, String>() {{ put("message", dao.getLongByShort(url)); }};
        }
        return new HashMap<String, String>() {{ put("message", "Short url is not exists"); }};
    }

    @Autowired
    public MainController(Dao dao, UrlValidator urlValidator) {
        this.dao = dao;
        this.urlValidator = urlValidator;
    }
}
