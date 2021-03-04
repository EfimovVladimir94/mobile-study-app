package com.study.mobileback.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String eventType;
    private String description;
    private String location;
    private String phone;
    private byte[] image;
}
