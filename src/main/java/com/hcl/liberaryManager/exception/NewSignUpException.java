package com.hcl.liberaryManager.exception;

import jakarta.validation.constraints.NotBlank;

public class NewSignUpException extends Exception {
    public NewSignUpException(@NotBlank String string) {
        super(string);
    }
}
