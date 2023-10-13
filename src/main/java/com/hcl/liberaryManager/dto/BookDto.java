package com.hcl.liberaryManager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookDto {
    @NotBlank(message = "title can not be blank")
    private String title;
    @NotBlank(message = "author can not be blank")
    private String author;
    @NotBlank(message = "isbn can not be blank")
    private String isbn;

    @NotNull(message = "publishedDate can not be blank")
    private Date publishedDate;
    @NotBlank(message = "publisher can not be blank")
    private String publisher;

}
