package com.study.mobileback.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final Long userID;
    private final String authToken;

    public JwtResponse(String authToken, Long userID) {
        this.authToken = authToken;
        this.userID = userID;
    }

}