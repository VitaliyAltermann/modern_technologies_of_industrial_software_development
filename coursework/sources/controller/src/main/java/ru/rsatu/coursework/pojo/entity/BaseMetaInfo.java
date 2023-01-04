package ru.rsatu.coursework.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
public class BaseMetaInfo {
    /**
     * Дата и время изменения записи в БД
     */
    @Column(name = "record_change_ts")
    @Comment(value = "Дата и время создания записи в БД")
    private Timestamp recordChangeTS;

    /**
     * Последний пользователь, который внёс изменения в записи в БД
     */
    @Column(name = "record_change_author")
    @Comment(value = "Автор созданной записи в БД")
    private String recordChangeAuthor;
}
