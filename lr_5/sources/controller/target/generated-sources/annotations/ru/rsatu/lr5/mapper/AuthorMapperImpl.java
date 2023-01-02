package ru.rsatu.lr5.mapper;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import ru.rsatu.lr5.pojo.dto.AuthorDto;
import ru.rsatu.lr5.pojo.entity.Author;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-04T02:17:52+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@ApplicationScoped
public class AuthorMapperImpl extends AuthorMapper {

    @Override
    public AuthorDto toAuthorDto(Author book) {
        if ( book == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        authorDto.setId( book.getId() );
        authorDto.setName( book.getName() );
        authorDto.setBirthDate( book.getBirthDate() );

        return authorDto;
    }

    @Override
    public Author toAuthor(AuthorDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( bookDto.getId() );
        author.setName( bookDto.getName() );
        author.setNickName( bookDto.getName() );
        author.setBirthDate( bookDto.getBirthDate() );

        return author;
    }
}
