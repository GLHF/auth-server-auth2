package com.craftsmengroup.paymentsystem.authserver.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ps_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique=true, nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(name = "email_accepted")
    private boolean emailAccepted;
    @Column(unique = true)
    private String phone;
    @Column(name = "phone_accepted")
    private boolean phoneAccepted;
    private boolean banned;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, name = "first_name")
    private String firstName;
    @Column(unique = true, name = "last_name")
    private String lastName;
    @Column(unique = true, name = "created_utc")
    private Instant created;
    @Column(unique = true, name = "modified_utc")
    private Instant modified;
    //separator - ','
    private String authorities;
}
