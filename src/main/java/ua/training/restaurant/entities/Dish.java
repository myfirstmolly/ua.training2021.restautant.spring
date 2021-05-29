package ua.training.restaurant.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 32)
    private String name;

    @Column(name = "name_ukr", unique = true, nullable = false, length = 32)
    private String nameUkr;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "description", length = 2048)
    private String description;

    @Column(name = "description_ukr", length = 2048)
    private String descriptionUkr;

    @Column(name = "image_path", nullable = false, length = 300)
    private String imagePath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY)
    private List<RequestItem> requestItems = new ArrayList<>();

}
