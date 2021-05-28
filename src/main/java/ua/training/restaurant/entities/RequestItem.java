package ua.training.restaurant.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "request_item")
public final class RequestItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @Positive
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", insertable = false)
    private int price;

}
