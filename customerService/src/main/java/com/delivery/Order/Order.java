package com.delivery.Order;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "customerId", nullable = false)
    Long customerId;

    @Column(name = "personsNumber", nullable = false)
    Long personsNumber;

    @Column(name = "comment", nullable = false)
    String comment;

    @Column(name = "address", nullable = false)
    String address;

    @Column(name = "apartmentOrOffice", nullable = false)
    String apartmentOrOffice;

    @Column(name = "paymentType", nullable = false)
    String paymentType;

    @Column(name = "operatorCall", nullable = false)
    Boolean operatorCall;

    @Column(name = "conditionAgreement", nullable = false)
    Boolean conditionAgreement;
}
