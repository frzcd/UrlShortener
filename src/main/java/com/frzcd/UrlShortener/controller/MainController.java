package com.frzcd.UrlShortener.controller;

import com.frzcd.UrlShortener.dao.Dao;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("u")
public class MainController {

    Dao dao;
    UrlValidator urlValidator;

    @Value("${home.link}")
    String host;

    @GetMapping(path = "/{link}")
    public RedirectView redirect(@PathVariable String link) {
        if (link.length() != 6) {
            return new RedirectView("/");
        }
        if (dao.checkIsShortExists(link) != 0) {
            return new RedirectView(dao.getLongByShort(link));
        }

        return new RedirectView("/");
    }

    @PutMapping
    public HashMap<String, String> generateShortUrl(@RequestBody Map<String, String> request) {

        if (!urlValidator.isValid(request.get("text"))) {
            return new HashMap<String, String>() {{ put("message", "Invalid url received"); }};
        }
        if (dao.checkIsLongExists(request.get("text")) != 0) {
            return new HashMap<String, String>() {{ put("message", host + "u/" + dao.getShortByLong(request.get("text"))); }};
        }
        return new HashMap<String, String>() {{ put("message", host + "u/" + dao.addNewShort(request.get("text"))); }};
    }

    @PostMapping
    public Map<String, String> getLongUrlByShort(@RequestBody Map<String, String> request) {
        String url = request.get("text")
                .replaceFirst(host + "u/", "");
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
