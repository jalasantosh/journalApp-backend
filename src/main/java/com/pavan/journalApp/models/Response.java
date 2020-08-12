package com.pavan.journalApp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Response<T> {

    private Date timeStamp;
    private String status;
    private String message;
    private T payload;

    public Response() {
        this.timeStamp = new Date();
    }

    public Response(String status, String message, T payload) {
        this.timeStamp = new Date();
        this.status = status;
        this.message = message;
        this.payload = payload;
    }
}
