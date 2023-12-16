package org.sid.orderservice.entities;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.enums.OrderStatus;
import org.sid.orderservice.model.Customer;


import java.util.Date;
import java.util.List;

@Entity
@Builder @Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Long customerId;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productsList;

    public double getTotal() {
        double somme=0;
        for(ProductItem pi:productsList)
            somme+=pi.getAmount();
        return somme;
    }
}
