package ru.rsatu.lr5.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuthorDto {
    private Long id;
    private String name;
    private Date birthDate;
}
