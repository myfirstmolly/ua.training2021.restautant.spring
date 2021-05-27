package ua.training.restaurant.dto;

import lombok.Getter;
import lombok.Setter;
import ua.training.restaurant.entities.Request;
import ua.training.restaurant.entities.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class RequestDto implements Serializable {

    @NotNull
    private Request request;

    @NotNull
    private Status status;

}
