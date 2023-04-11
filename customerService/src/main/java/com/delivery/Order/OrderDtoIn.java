package com.delivery.Order;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDtoIn {
    Long customerId;

    @Range(min = 1, max = 20)
    Long personsNumber;

    @NotNull
    @NotBlank(message = "Ошибка: comment пустое или содержит только пробелы")
    String comment;

    @NotNull
    @NotBlank(message = "Ошибка: address пустое или содержит только пробелы")
    String address;

    @NotNull
    @NotBlank(message = "Ошибка: apartmentOrOffice пустое или содержит только пробелы")
    String apartmentOrOffice;

    @NotNull
    @NotBlank(message = "Ошибка: paymentType пустое или содержит только пробелы")
    String paymentType;

    @ColumnDefault("true")
    Boolean operatorCall;

    Boolean conditionAgreement;
    List<Long> foodIds;
}