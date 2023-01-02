package ru.rsatu.lr5.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rsatu.lr5.pojo.entity.Author;
import ru.rsatu.lr5.pojo.dto.AuthorDto;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Mapper(componentModel = "cdi")
public abstract class AuthorMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "birthDate", source = "birthDate")
    public abstract AuthorDto toAuthorDto(Author book);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "nickName", source = "name")
    @Mapping(target = "birthDate", source = "birthDate")
    public abstract Author toAuthor(AuthorDto bookDto);

}
