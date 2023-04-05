package com.delivery.Customer;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDtoIn {
    Long id;

    @NotNull
    @NotBlank(message = "Ошибка: name пустое или содержит только пробелы")
    String name;

    @NotNull
    @NotBlank(message = "Ошибка: email пустое или содержит только пробелы")
    @Email(message = "Неверные данные: ошибка в записи email")
    String email;

    @NotNull
    @NotBlank(message = "Ошибка: phone пустое или содержит только пробелы")
    String phone;
}