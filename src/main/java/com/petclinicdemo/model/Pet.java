package com.petclinicdemo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet extends BaseEntity{
    private String name;
    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "pet")
    private List<Visit> visits = new ArrayList<>();

    @Override
    public String toString() {
        return name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return name.equals(pet.name) &&
                petType.equals(pet.petType) &&
                birthDate.equals(pet.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, petType, birthDate);
    }
}
