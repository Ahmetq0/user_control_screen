package com.turkcell.cm.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUserIU {

    private Integer id;

    @NotEmpty(message = "Ad boş bırakılamaz.")
    @Size(min = 3, max = 7, message = "Ad 3 ile 7 karakter arasında olmalıdır.")
    private String firstName;

    @NotEmpty(message = "Soyad boş bırakılamaz.")
    @Size(min = 3, max = 7, message = "Soyad 3 ile 7 karakter arasında olmalıdır.")
    private String lastName;

    @NotEmpty(message = "Şifre boş bırakılamaz.")
    @Size(min = 6, max = 20, message = "Şifre 6 ile 20 karakter arasında olmalıdır.")
    private String password;
}
