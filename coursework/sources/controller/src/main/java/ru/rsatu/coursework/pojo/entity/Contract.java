package ru.rsatu.coursework.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Date;

/**
 * Таблица "Договор"
 */
@Getter
@Setter
@Entity
@Table(name = "contract")
public class Contract extends BaseMetaInfo {
    /**
     * Уникальный идентификатор (первичный ключ) договора
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "contract_id_gen")
    @SequenceGenerator(name = "contract_id_gen",
            sequenceName = "contract_id_gen_seq",
            initialValue = 10,
            allocationSize = 10)
    @Column(name = "id")
    @Comment(value = "Уникальный идентификатор (первичный ключ) договора")
    private Long id;

    /**
     * Дата создания договора
     */
    @Column(name = "date_of_create")
    @Comment(value = "Дата создания договора")
    private Date dateOfCreate;

    /**
     * Номер договора
     */
    @Column(name = "number")
    @Comment(value = "Номер договора")
    private String number;

    /**
     * Наименование контрагента
     */
    @Column(name = "contractor")
    @Comment(value = "Наименование контрагента")
    private String contractor;
}
