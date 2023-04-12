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

    @Column(name = "customer_id", nullable = false)
    Long customerId;

    @Column(name = "persons_number", nullable = false)
    Long personsNumber;

    @Column(name = "comments", nullable = false)
    String comments;

    @Column(name = "address", nullable = false)
    String address;

    @Column(name = "apartment_or_office", nullable = false)
    String apartmentOrOffice;

    @Column(name = "payment_type", nullable = false)
    String paymentType;

    @Column(name = "operator_call", nullable = false)
    Boolean operatorCall;

    @Column(name = "condition_agreement", nullable = false)
    Boolean conditionAgreement;
}
