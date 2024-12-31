package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CUSTOMERS_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer customer;

    @NotNull
    @Size(max = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    private String lastName;

    @NotNull
    @Email
    private String email;

    public Record(Integer customer, String firstName, String lastName, String email) {
        this.customer = customer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
