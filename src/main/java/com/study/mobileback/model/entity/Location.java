package com.study.mobileback.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue
    private Long id;
    private Double lat;
    private Double lng;
    @OneToOne
    @MapsId
    @JoinColumn(name = "event_id")
    private Event event;

}
