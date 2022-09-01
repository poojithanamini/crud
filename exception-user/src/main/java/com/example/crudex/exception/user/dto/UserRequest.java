package com.example.crudex.exception.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "name can't be empty")
    private String firstName;
    @NotNull(message = "name can't be empty")
    private String lastName;
    @Min(18)
    @Max(60)
    private int age;
}
