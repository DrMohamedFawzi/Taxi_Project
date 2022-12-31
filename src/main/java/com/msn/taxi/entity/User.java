package com.msn.taxi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@SecondaryTable(name = "pwds", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "phone"))
public class User {

    @Id
    private String phone;
    @Column(length = 25, nullable = false)
    private String fname;
    @Column(length = 25)
    private String mname;
    @Column(length = 25, nullable = false)
    private String lname;


    @JsonIgnore
    @Column(table = "pwds", length = 25, nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses;


}
