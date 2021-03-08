package com.study.mobileback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventMapMarkDto {

    private String name;
    private String description;
    private LocationDto location;
    private String eventType;
}