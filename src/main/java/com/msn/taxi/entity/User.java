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

    public User(String fname, String lname, String phone, String password) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.password = password;
    }


}
