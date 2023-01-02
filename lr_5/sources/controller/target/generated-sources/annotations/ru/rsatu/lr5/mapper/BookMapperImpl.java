package ru.rsatu.lr5.mapper;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import ru.rsatu.lr5.pojo.dto.BookDto;
import ru.rsatu.lr5.pojo.entity.Author;
import ru.rsatu.lr5.pojo.entity.Book;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-04T02:17:53+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@ApplicationScoped
public class BookMapperImpl extends BookMapper {

    @Override
    public BookDto toBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( book.getId() );
        bookDto.setName( book.getName() );
        bookDto.setAuthorId( bookAuthorId( book ) );

        return bookDto;
    }

    @Override
    public Book toBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDto.getId() );
        book.setName( bookDto.getName() );

        afterBookMapping( book, bookDto );

        return book;
    }

    private Long bookAuthorId(Book book) {
        if ( book == null ) {
            return null;
        }
        Author author = book.getAuthor();
        if ( author == null ) {
            return null;
        }
        Long id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
