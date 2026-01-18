package org.example.library.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateBookRequest {

    @NotBlank
    @Size(min = 2)
    private String title;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
