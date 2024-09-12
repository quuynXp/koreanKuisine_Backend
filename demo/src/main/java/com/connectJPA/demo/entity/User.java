package com.connectJPA.demo.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    String id;

    @Column(unique = true, nullable = false)
    String username;

    String password;

    String mail;

    String phone;

    String firstName;
    String lastName;
    LocalDate dayOfBirth;
    Set<String> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Orders> orders = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Cart cart;

    public void addOrder(Orders order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Orders order) {
        orders.remove(order);
        order.setUser(null);
    }

    // Chuyển danh sách roles thành GrantedAuthority
    public List<GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public void addRole(String role) {
        roles.add(role);
    }

    public void removeRole(String role) {
        roles.remove(role);
    }


}
