package com.example.user.usercrud.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "can't")
    private String firstName;
    @NotNull(message = "not null")
    private String lastName;
    @Min(0)
    @Max(100)
    private int age;
}
