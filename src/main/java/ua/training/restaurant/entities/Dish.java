package ua.training.restaurant.entities;

import lombok.*;
import ua.training.restaurant.dto.DishDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "requestItems")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "requestItems")
@Builder
@Table(name = "dish")
public final class Dish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name_ukr", nullable = false)
    private String nameUkr;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "description_ukr")
    private String descriptionUkr;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY)
    private List<RequestItem> requestItems;

}
