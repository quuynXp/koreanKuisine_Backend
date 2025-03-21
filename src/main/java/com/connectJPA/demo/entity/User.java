package com.connectJPA.demo.entity;
import com.connectJPA.demo.entity.Role;
import com.connectJPA.demo.exception.ErrorCode;
import com.connectJPA.demo.validator.DobContraints;
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

    @Column(nullable = false)
    String password;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false, unique = true)
    String phone;

    String firstName;
    String lastName;

    @DobContraints(min = 18, message = "INVALID_DOB")
    LocalDate dayOfBirth;

    @ManyToMany
    Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Orders> orders = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<TableBooking> tableBookings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<RoomBooking> roomBookings;

    public void addOrder(Orders order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Orders order) {
        orders.remove(order);
        order.setUser(null);
    }

}
