package com.home.aatravel.entity;

import com.home.aatravel.model.UserSignupRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users_tbl")
@Setter
@Getter
public class User {

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(UserSignupRequest userSignupRequest) {
        this.email = userSignupRequest.getEmail().toString();
        this.password = userSignupRequest.getPassword();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TransactionParticipation> transactionParticipationList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
