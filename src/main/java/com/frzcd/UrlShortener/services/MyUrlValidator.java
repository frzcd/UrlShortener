package com.frzcd.UrlShortener.services;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

@Service
public class MyUrlValidator extends UrlValidator { }
