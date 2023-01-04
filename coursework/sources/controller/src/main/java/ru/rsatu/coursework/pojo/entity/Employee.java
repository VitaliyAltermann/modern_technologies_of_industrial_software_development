package ru.rsatu.coursework.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

/**
 * Таблица "Сотрудник"
 */
@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee extends BaseMetaInfo {
    /**
     * Уникальный идентификатор (первичный ключ) сотрудника
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "employee_id_gen")
    @SequenceGenerator(name = "employee_id_gen",
            sequenceName = "employee_id_gen_seq",
            initialValue = 10,
            allocationSize = 10)
    @Column(name = "id")
    @Comment(value = "Уникальный идентификатор (первичный ключ) сотрудника")
    private Long id;

    /**
     * ФИО сотрудника
     */
    @Column(name = "name")
    @Comment(value = "ФИО сотрудника")
    private String name;
}
