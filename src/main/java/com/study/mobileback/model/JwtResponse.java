package com.study.mobileback.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final Long userId;
    private final String jwttoken;

    public JwtResponse(String jwttoken, Long userId) {
        this.jwttoken = jwttoken;
        this.userId = userId;
    }

}