package ua.training.restaurant.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "requestItems")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"requestItems"})
@Builder
@Table(name = "request")
public final class Request implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    @GeneratedValue
    private Long totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approved_by", nullable = false)
    private User approvedBy;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
    private List<RequestItem> requestItems = new ArrayList<>();

}
