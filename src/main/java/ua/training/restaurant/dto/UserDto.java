package ua.training.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {

    @NotNull(message = "{username.notnull}")
    @Size(min = 4, max = 16, message = "{username.length}")
    @Pattern(regexp = "[A-Za-z_]+", message = "{username.characters}")
    private String username;

    @NotNull(message = "{password.notnull}")
    @Size(min = 6, message = "{password.length}")
    private String password;

    @NotNull(message = "{user.name.notnull}")
    @Size(min = 3, max = 32, message = "{user.name.length}")
    @Pattern(regexp = "\\p{Lu}\\p{L}+", message = "{user.name.characters}")
    private String name;

    @NotNull(message = "{phone.notnull}")
    @Pattern(regexp = "\\([0-9]{3}\\)[0-9]{3}-[0-9]{2}-[0-9]{2}",
            message = "{phone.characters}")
    private String phoneNumber;

    @Pattern(regexp = "[A-Za-z]+@[A-Za-z]+\\.[a-z]+", message = "{email.message}")
    @Email(message = "{email.message}")
    private String email;

}
