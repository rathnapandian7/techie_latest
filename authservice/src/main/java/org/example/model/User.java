package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="USER")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID")
    private Integer userId;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="USER_NAME")
    private String userName;
}
