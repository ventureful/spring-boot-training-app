package com.example.employee.model.dto.param;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class EntityIdParam {
    @NotNull(message = "id cannot be empty, please check the id parameter")
    private BigInteger id;
}
