package com.restaurant.api.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"createdAt","updatedAt","requestItems"})
@Builder
@Table(name = "request")
public final class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private User user;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_id")
    private Status status;

    @Column(name = "delivery_address")
    @Size(min = 6, max = 128, message = "{request.delivery.size}")
    private String deliveryAddress;

    @Column(name = "total_price")
    private Long totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approved_by", nullable = false)
    private User approvedBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
    private List<RequestItem> requestItems;

}
