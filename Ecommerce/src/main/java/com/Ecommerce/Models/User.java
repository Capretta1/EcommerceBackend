package com.Ecommerce.Models;



import com.Ecommerce.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@SuppressWarnings("JpaAttributeTypeInspection")
@Entity(name = "User")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long ID;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String password;
    private String phoneNumber;
    @OneToMany(mappedBy = "user")
    private List<Address> address;
    @Enumerated
    private Role role;


}
