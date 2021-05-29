package ua.training.restaurant.dto;

import lombok.Getter;
import lombok.Setter;
import ua.training.restaurant.entities.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class DishDto implements Serializable {

    @NotNull(message = "{dish.name.blank}")
    @NotBlank(message = "{dish.name.blank}")
    @Size(max = 32, min = 4, message = "{dish.name.length}")
    private String name;

    @NotNull(message = "{dish.name.blank}")
    @NotBlank(message = "{dish.name.blank}")
    @Size(max = 32, min = 4, message = "{dish.name.length}")
    private String nameUkr;

    @Pattern(regexp = "([0-9]{2,5}\\.[0-9]{2})", message = "{dish.price.incorrect}")
    @NotNull(message = "{dish.price.incorrect}")
    private String price;

    @Size(max = 2048, message = "{dish.description.maxsize}")
    private String description;

    @Size(max = 2048, message = "{dish.description.maxsize}")
    private String descriptionUkr;

    @NotNull(message = "{dish.image.blank}")
    @NotBlank(message = "{dish.image.blank}")
    private String imagePath;

    @NotNull(message = "{dish.category.blank}")
    private Category category;

}
