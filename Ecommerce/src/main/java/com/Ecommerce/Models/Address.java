package com.Ecommerce.Models;

import jakarta.persistence.*;
import lombok.*;



@Entity(name = "Address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    @ManyToOne
    private User user;


}
