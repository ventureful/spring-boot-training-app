package com.example.employee.model.dto.param;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityIdParam {
    @NotNull(message = "id cannot be empty, please check the id parameter")
    private Long id;
}
