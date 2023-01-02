package ru.rsatu.lr5.service;

import ru.rsatu.lr5.mapper.BookMapper;
import ru.rsatu.lr5.pojo.dto.BookDto;
import ru.rsatu.lr5.repository.BooksRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с книгами
 */
@ApplicationScoped
public class BooksService {

    @Inject
    BookMapper bookMapper;

    @Inject
    BooksRepository booksRepository;

    /**
     * Загрузить все книги
     */
    public List<BookDto> loadBookList() {
        return booksRepository.loadBooks()
                .stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    /**
     * Загрузить книги указанного автора
     */
    public List<BookDto> loadBookListByAuthorId(Long author_id) {
        return booksRepository.loadBooksByAuthorId(author_id)
                .stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    /**
     * Сохранение книги
     */
    public BookDto saveBook(BookDto bookDto) {
        return bookMapper.toBookDto(booksRepository.saveBook(bookDto));
    }

    /**
     * Удаление книги
     */
    public void deleteBookById(Long book_id) {
        booksRepository.deleteBookById(book_id);
    }
}
