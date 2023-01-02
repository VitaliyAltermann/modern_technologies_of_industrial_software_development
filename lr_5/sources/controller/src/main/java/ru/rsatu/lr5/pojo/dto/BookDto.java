package ru.rsatu.lr5.pojo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private Long id;
    private String name;
    private Long authorId;

}
