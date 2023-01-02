package ru.rsatu.lr5.repository;

import ru.rsatu.lr5.mapper.BookMapper;
import ru.rsatu.lr5.pojo.dto.BookDto;
import ru.rsatu.lr5.pojo.entity.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с книгами
 */
@ApplicationScoped
public class BooksRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    BookMapper bookMapper;

    /**
     * Загрузить все книги
     */
    public List<Book> loadBooks() {
        return entityManager.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    /**
     * Загрузить книги конкретного автора
     */
    public List<Book> loadBooksByAuthorId(Long author_id) {
        return entityManager.createQuery("select b from Book b where author_id = :author_id", Book.class)
                .setParameter("author_id", author_id)
                .getResultList();
    }

    /**
     * Сохранение книги
     */
    @Transactional
    public Book saveBook(BookDto bookDto) {
        Book book = bookMapper.toBook(bookDto);
        if (book.getId() != null) {
            entityManager.merge(book);
        } else {
            entityManager.persist(book);
        }
        entityManager.flush();
        return book;
    }

    /**
     * Удаление книги
     */
    @Transactional
    public void deleteBookById(Long book_id) {
        entityManager.createQuery("delete from Book where id = :book_id")
                .setParameter("book_id", book_id)
                .executeUpdate();
    }
}
