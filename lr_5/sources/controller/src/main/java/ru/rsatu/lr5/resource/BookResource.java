package ru.rsatu.lr5.resource;


import ru.rsatu.lr5.pojo.dto.BookDto;
import ru.rsatu.lr5.pojo.dto.AuthorDto;
import ru.rsatu.lr5.service.AuthorsService;
import ru.rsatu.lr5.service.BooksService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books/api/v1")
public class BookResource {

    @Inject
    BooksService booksService;

    @Inject
    AuthorsService authorsService;

    @GET
    @Path("/loadBookList")
    public List<BookDto> loadBookList() {
        return booksService.loadBookList();
    }

    @GET
    @Path("/loadBookListByAuthorId")
    public List<BookDto> loadBookListByAuthorId(@QueryParam("authorId") Long authorId) {
        return booksService.loadBookListByAuthorId(authorId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/saveBook")
    public BookDto saveBook(BookDto bookDto) {
        return booksService.saveBook(bookDto);
    }

    @DELETE
    @Path("/deleteBookById")
    public void deleteBookById(@QueryParam("bookId") Long bookId) {
        booksService.deleteBookById(bookId);
    }

    @GET
    @Path("/loadAuthorIdsList")
    public List<Long> loadAuthorIdsList() {
        return authorsService.loadAuthorIdsList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveAuthor")
    public void saveAuthor(AuthorDto authorDto) {
        authorsService.saveAuthor(authorDto);
    }

    @DELETE
    @Path("/deleteAuthorById")
    public void deleteAuthorById(@QueryParam("authorId") Long authorId) {
        authorsService.deleteAuthorById(authorId);
    }
}
