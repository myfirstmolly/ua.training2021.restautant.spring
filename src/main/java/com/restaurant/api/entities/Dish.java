package com.restaurant.api.entities;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "requestItems")
@Table(name = "dish")
public final class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "{dish.name.blank}")
    @Getter @Setter
    private String name;

    @Column(name = "name_ukr", nullable = false)
    @NotBlank(message = "{dish.name.blank}")
    @Getter @Setter
    private String nameUkr;

    @Column(name = "price", nullable = false)
    @Positive(message = "{dish.price.positive}")
    @Getter
    private int price;

    @Column(name = "description")
    @Size(max = 2048, message = "{dish.description.maxsize}")
    @Getter @Setter
    private String description;

    @Column(name = "description_ukr")
    @Size(max = 2048, message = "{dish.description.maxsize}")
    @Getter @Setter
    private String descriptionUkr;

    @Column(name = "image_path", nullable = false)
    @NotBlank(message = "{dish.image.blank}")
    @Getter @Setter
    private String imagePath;

    @NotBlank(message = "{dish.category.blank}")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @Getter @Setter
    private Category category;

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY)
    @Getter @Setter
    private List<RequestItem> requestItems;

    public void setPrice(@NotNull @Pattern(regexp = "([0-9]+\\.[0-9]{2})|([0-9]+)",
            message = "{dish.price.incorrect}") String price) {
        if (price.contains(".")) {
            this.price = Integer.parseInt(price.replace(".", ""));
        } else {
            this.price = Integer.parseInt(price) * 100;
        }
    }
}
