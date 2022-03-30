package de.anybytes.springbootschulung.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty(message = "username is required")
    private String username;

    @NotEmpty(message = "password is required")
    private String password;

    public User(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER) // Beim erstellen eines Users werden die Rollen mit erstellt
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void addRoles(List<Role> roles) {
        this.roles.addAll(roles);
    }
}
