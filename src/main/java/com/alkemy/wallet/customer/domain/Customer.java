package com.alkemy.wallet.customer.domain;

import com.alkemy.wallet.account.domain.model.Account;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "CUSTOMERS")
@SQLDelete(sql = "UPDATE customers SET ACTIVE=false WHERE id=?")
@SQLRestriction("ACTIVE <> false")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, name = "FIRST_NAME")
    private String firstName;

    @Column(nullable = false, name = "LAST_NAME")
    private String lastName;

    @Column(nullable = false, unique = true, name = "EMAIL")
    private String email;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Column(name = "ACTIVE")
    private boolean active;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "REGISTRATION_AT")
    private LocalDateTime registrationDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "LAST_MODIFICATION")
    private LocalDateTime lastProfileModificationDate;

    @OneToMany(fetch = LAZY, cascade = ALL)
    private List<Account> associatedAccounts;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.associatedAccounts = new ArrayList<>();
        this.active = true;
        this.registrationDate = LocalDateTime.now();
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public LocalDateTime getLastProfileModificationDate() {
        return lastProfileModificationDate;
    }

    public List<Account> getAssociatedAccounts() {
        return associatedAccounts;
    }

    public boolean isActive() {
        return active;
    }

    public void updateEmail(String newEmail) {
        if (Objects.nonNull(newEmail))
            this.email = newEmail;
    }

    public void setPassword(String password) {
        if (Objects.nonNull(password))
            this.password = password;
    }

    public void setLastProfileModificationDate(LocalDateTime lastProfileModificationDate) {
        if (Objects.nonNull(lastProfileModificationDate))
            this.lastProfileModificationDate = lastProfileModificationDate;
    }
}
