package ua.training.restaurant.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "dishes")
@NoArgsConstructor
@EqualsAndHashCode(exclude = "dishes")
@Table(name = "category")
public final class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name_ukr", nullable = false)
    private String nameUkr;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Dish> dishes = new ArrayList<>();

}
