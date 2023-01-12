package com.msn.taxi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@SecondaryTable(name = "pwds", pkJoinColumns =
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "phone"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "user_id", nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(length = 25, nullable = false)
    private String fname;
    @Column(length = 25)
    private String mname;
    @Column(length = 25, nullable = false)
    private String lname;

    private boolean active = false;

    @JsonIgnore
    @Column(table = "pwds", length = 25, nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Request> requests;

    public User(String fname, String lname, String phone, String password) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.password = password;
    }


}
