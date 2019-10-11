package com.carcompany.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "\"car\"")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "company")
    private String company;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "yearOfProduction")
    private int yearOfProduction;

    @Column(name = "horsePower")
    private int horsePower;

    @Column(name = "engine")
    private int engine;

    @Column(name = "isAutomatic")
    private boolean isAutomatic;
}
