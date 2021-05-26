package com.restaurant.api.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "createdAt")
@Builder
@Table(name = "request_item")
public final class RequestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @Positive
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

}
