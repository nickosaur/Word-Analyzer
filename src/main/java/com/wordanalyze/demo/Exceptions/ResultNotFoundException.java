package com.wordanalyze.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="No file with name")
public class ResultNotFoundException extends RuntimeException {
}
