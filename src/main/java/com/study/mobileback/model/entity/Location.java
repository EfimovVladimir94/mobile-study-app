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
    @Column(name = "location_id")
    private Long id;
    private Double lat;
    private Double lng;

    @OneToOne(mappedBy = "location")
    private Event event;

}
