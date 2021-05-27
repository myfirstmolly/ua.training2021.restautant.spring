package ua.training.restaurant.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ua.training.restaurant.entities.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class DishDto implements Serializable {

    @NotBlank(message = "{dish.name.blank}")
    private String name;

    @NotBlank(message = "{dish.name.blank}")
    private String nameUkr;

    @Pattern(regexp = "([0-9]+\\.[0-9]{2})|([0-9]+)", message = "{dish.price.incorrect}")
    private String price;

    @Size(max = 2048, message = "{dish.description.maxsize}")
    private String description;

    @Size(max = 2048, message = "{dish.description.maxsize}")
    private String descriptionUkr;

    @NotBlank(message = "{dish.image.blank}")
    private String imagePath;

    @NotBlank(message = "{dish.category.blank}")
    private Category category;

}
