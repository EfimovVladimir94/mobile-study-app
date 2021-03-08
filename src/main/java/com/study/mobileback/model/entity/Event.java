package com.study.mobileback.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String eventType;
    private String description;
    private String phone;
    private byte[] image;
    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Location location;
}
