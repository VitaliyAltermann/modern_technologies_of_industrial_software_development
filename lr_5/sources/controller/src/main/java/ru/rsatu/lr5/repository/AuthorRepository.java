package ru.rsatu.lr5.repository;

import ru.rsatu.lr5.mapper.AuthorMapper;
import ru.rsatu.lr5.pojo.dto.AuthorDto;
import ru.rsatu.lr5.pojo.entity.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с авторами
 */
@ApplicationScoped
public class AuthorRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    AuthorMapper authorMapper;

    public List<Author> loadAuthors() {
        return entityManager.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    /**
     * Сохранение автора
     */
    @Transactional
    public Author saveAuthor(AuthorDto authorDto) {
        Author author = authorMapper.toAuthor(authorDto);
        if (author.getId() != null) {
            entityManager.merge(author);
        } else {
            entityManager.persist(author);
        }
        entityManager.flush();
        return author;
    }

    /**
     * Удаление автора
     */
    @Transactional
    public void deleteAuthorById(Long author_id) {
        entityManager.createQuery("delete from Book where author_id=:id")
                .setParameter("id", author_id)
                .executeUpdate();
        entityManager.createQuery("delete from Author where id=:id")
                .setParameter("id", author_id)
                .executeUpdate();
    }
}
