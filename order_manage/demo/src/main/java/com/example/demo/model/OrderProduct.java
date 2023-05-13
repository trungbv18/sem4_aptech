package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_product")public class OrderProduct {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "code")
    private String code;
    @Column(name = "customer_id")
    private long customerId;
    @CreatedDate
    @Column(name = "created_date")
    private Timestamp createDate;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "order_detail",
//            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
//    )
//    private Set<Product> products = new HashSet<>();


    //    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(	name = "order_detail",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private Set<Product> products = new HashSet<>();
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
////    @JsonIgnore
//    private Customer customer;
//    @ManyToOne
//    @JoinColumn(name="customer_id")
//    private Customer customer;

}
