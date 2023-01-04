package ru.rsatu.coursework.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

/**
 * Таблица "Детализация накладной"
 */
@Getter
@Setter
@Entity
@Table(name = "detail_waybill")
public class DetailWaybill extends BaseMetaInfo {
    /**
     * Уникальный идентификатор (первичный ключ) детализация накладной
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "detail_waybill_id_gen")
    @SequenceGenerator(name = "detail_waybill_id_gen",
            sequenceName = "detail_waybill_id_gen_seq",
            initialValue = 10,
            allocationSize = 10)
    @Column(name = "id")
    @Comment(value = "Уникальный идентификатор (первичный ключ) детализация накладной")
    private Long id;

    /**
     * Имущество
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id")
    @Comment(value = "Идентификатор имущества из таблицы \"cargo\"")
    private Cargo cargo;

    /**
     * Количество единиц имущества
     */
    @Column(name = "quantity")
    @Comment(value = "Количество единиц имущества")
    private Long quantity;

    /**
     * Цена за единицу имущества
     */
    @Column(name = "price")
    @Comment(value = "Цена за единицу имущества")
    private Double price;
}
