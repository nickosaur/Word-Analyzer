package com.wordanalyze.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="File uploaded not valid")
public class FileUploadErrorException extends RuntimeException {
}
