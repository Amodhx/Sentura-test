package com.example.Sentura_test.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull
    private String uid;
    private String name;
    private String given_name;
    private String middle_name;
    private String family_name;
    private String nickname;
    @Email
    private String email;
    @Pattern(regexp = "^(?:\\+94|0)([1-9]\\d)-?\\d{7}$\n", message = "Phone Number Is Not Valid")
    private String phone_number;
    private String comment;
    private String picture;
    private String directory;
    private Map<String, Object> metadata;
    private List<String> tags;

}
