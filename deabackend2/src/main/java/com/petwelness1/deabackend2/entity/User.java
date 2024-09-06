package com.petwelness1.deabackend2.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String petType;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns =
                    @JoinColumn(name = "USER_ID",referencedColumnName = "username"),

            inverseJoinColumns =
                    @JoinColumn(name = "ROLE_ID",referencedColumnName = "roleName"))

    private Set<Role> role;


    // Set default role to 'User' if not already present
    public void addDefaultRoleIfNotPresent(Role userRole) {
        if (this.role == null || this.role.isEmpty()) {
            this.role = new HashSet<>();
        }
        this.role.add(userRole);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}



