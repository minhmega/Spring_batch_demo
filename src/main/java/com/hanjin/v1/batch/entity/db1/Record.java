package com.hanjin.v1.batch.entity.db1;


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

    private Integer customer;

    private String firstName;

    private String lastName;

    private String email;

    public Record(Integer customer, String firstName, String lastName, String email) {
        this.customer = customer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
