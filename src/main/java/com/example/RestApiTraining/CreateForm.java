package com.example.RestApiTraining;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateForm {

    @NotBlank
    @Size(min = 1, max = 20)
    private String name;

    public CreateForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
