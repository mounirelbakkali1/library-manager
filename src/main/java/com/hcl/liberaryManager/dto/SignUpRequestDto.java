package com.hcl.liberaryManager.dto;

import jakarta.validation.constraints.NotBlank;

public record SignUpRequestDto(@NotBlank String name,@NotBlank String username ,@NotBlank String password) {
}
