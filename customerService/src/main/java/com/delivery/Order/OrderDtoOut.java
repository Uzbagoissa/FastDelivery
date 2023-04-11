package com.delivery.Order;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDtoOut {
    Long id;
    Long customerId;
    Long personsNumber;
    String comment;
    String address;
    String apartmentOrOffice;
    String paymentType;
    Boolean operatorCall;
    Boolean conditionAgreement;
    List<Long> foodIds;
}
