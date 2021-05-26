package com.restaurant.api.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "dishes")
@Table(name = "category")
public final class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_ukr")
    private String nameUkr;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Dish> dishes;

}
