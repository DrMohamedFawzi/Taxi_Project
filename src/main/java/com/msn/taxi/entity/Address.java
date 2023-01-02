package com.msn.taxi.entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_gen")
    @SequenceGenerator(name = "address_gen", sequenceName = "address_seq", allocationSize = 1)
    @Column(name = "address_id", nullable = false)
    private Long id;

    private int city;
    private int area;
    private String near;
}
