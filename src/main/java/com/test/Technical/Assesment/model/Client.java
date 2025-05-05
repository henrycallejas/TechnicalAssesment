package com.test.Technical.Assesment.model;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CLIENT")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private Long clientId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    @Email
    @NotBlank
    private String email;

    @Column(name = "USERNAME")
    @NotBlank
    private String username;

    @Column(name = "PASSWORD")
    @NotBlank
    private String password;

    @Column(name = "rol")
    private String role;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orders;

}
