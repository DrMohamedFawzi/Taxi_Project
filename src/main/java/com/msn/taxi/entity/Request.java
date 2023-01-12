package com.msn.taxi.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "requests")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_gen")
    @SequenceGenerator(name = "request_gen", sequenceName = "request_seq", allocationSize = 1)
    @Column(name = "request_id", nullable = false)
    private Long id;


    private Timestamp createdTime = Timestamp.from(Instant.from(LocalDateTime.now()));

    @ManyToOne
    private User user;

    @OneToOne
    private Address from;
//    private Location to;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Request request = (Request) o;
        return id != null && Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
