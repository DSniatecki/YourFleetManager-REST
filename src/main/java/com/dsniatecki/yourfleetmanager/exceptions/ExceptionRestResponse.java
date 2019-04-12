package com.dsniatecki.yourfleetmanager.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ExceptionRestResponse {

    private Date timestamp;
    private String message;
    private String details;

}
