package ru.rsatu.coursework.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

/**
 * Таблица "Имущество"
 */
@Getter
@Setter
@Entity
@Table(name = "cargo")
public class Cargo extends BaseMetaInfo {

    /**
     * Уникальный идентификатор (первичный ключ) имущества
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "cargo_id_gen")
    @SequenceGenerator(name = "cargo_id_gen",
                       sequenceName = "cargo_id_gen_seq",
                       initialValue = 10,
                       allocationSize = 10)
    @Column(name = "id")
    @Comment(value = "Уникальный идентификатор (первичный ключ) имущества")
    private Long id;

    /**
     * Наименование имущества
     */
    @Column(name = "name")
    @Comment(value = "Наименование имущества")
    private String name;

    /**
     * Единицы измерения
     */
    @Column(name = "unit_of_measure")
    @Comment(value = "Единицы измерения")
    private String unitOfMeasure;
}
