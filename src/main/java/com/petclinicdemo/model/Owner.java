package com.petclinicdemo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Owner extends Person {

    @Column(name = "address")
    private String address;
    private String phone;
    private String city;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

}
