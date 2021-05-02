package com.web.restservice;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String detailes;
    private String httpCodeMessage;
  
    public ExceptionResponse(Date timestamp, String message, String details,String httpCodeMessage) {
      this.timestamp = timestamp;
      this.message = message;
      this.detailes = details;
      this.httpCodeMessage = httpCodeMessage;
    }
  
    public String getHttpCodeMessage() {
      return httpCodeMessage;
    }
  
    public Date getTimestamp() {
      return timestamp;
    }
  
    public String getMessage() {
      return message;
    }
  
    public String getDetailes() {
      return detailes;
    }
  
  }
  