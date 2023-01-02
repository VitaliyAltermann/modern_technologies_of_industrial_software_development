package ru.rsatu.lr5.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Книга
 */
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id_gen")
    @SequenceGenerator(name = "books_id_gen", sequenceName = "books_id_gen_seq", initialValue = 10, allocationSize = 10)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

}
