package ru.rsatu.lr5.service;

import ru.rsatu.lr5.mapper.AuthorMapper;
import ru.rsatu.lr5.pojo.dto.AuthorDto;
import ru.rsatu.lr5.pojo.entity.Author;
import ru.rsatu.lr5.repository.AuthorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с авторами
 */
@ApplicationScoped
public class AuthorsService {
    @Inject
    AuthorMapper authorMapper;

    @Inject
    AuthorRepository authorRepository;

    /**
     * Загрузить всех авторов
     */
    public List<Long> loadAuthorIdsList() {
        List<Author> authors = authorRepository.loadAuthors();
        List<Long> result = new ArrayList<>();
        for ( Author author : authors ) {
            result.add(author.getId());
        }
        return result;
    }

    /**
     * Сохранение автора
     */
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        return authorMapper.toAuthorDto(authorRepository.saveAuthor(authorDto));
    }

    /**
     * Удаление автора
     */
    public void deleteAuthorById(Long author_id) {
        authorRepository.deleteAuthorById(author_id);
    }

}
