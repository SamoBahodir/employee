package com.example.employee.domen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Setter
@Getter
@ToString
@Table(name = "hodim_lar")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 2,message = "Kamida 2ta belgi bolishi kerak")
    private String name;

    @NotEmpty
    @Size(min = 2,message = "Kamida 2ta belgi bolishi kerak")
    private String lastName;

    @NotEmpty
    @Size(min = 8,message = "Kamida 8ta belgi bolishi kerak")
    private String email;

    @NotEmpty
    @Size(min = 13,max = 13,message = " 13ta belgi bolishi shart")
    private String phone;


    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
