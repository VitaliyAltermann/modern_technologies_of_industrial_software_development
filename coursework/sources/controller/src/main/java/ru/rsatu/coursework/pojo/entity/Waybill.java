package ru.rsatu.coursework.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Таблица "Накладная"
 */
@Getter
@Setter
@Entity
@Table(name = "waybill", indexes = {
    @Index(columnList = "number"),
    @Index(columnList = "date_of_receipt"),
    @Index(columnList = "total_cost"),
    @Index(columnList = "receiver_id")
})
public class Waybill extends BaseMetaInfo {
    /**
     * Уникальный идентификатор (первичный ключ) накладной
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "waybill_id_gen")
    @SequenceGenerator(name = "waybill_id_gen",
            sequenceName = "waybill_id_gen_seq",
            initialValue = 10,
            allocationSize = 10)
    @Column(name = "id")
    @Comment(value = "Уникальный идентификатор (первичный ключ) накладной")
    private Long id;

    /**
     * Договор
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id")
    @Comment(value = "Идентификатор договора из таблицы \"contract\"")
    private Contract contract;

    /**
     * Номер накладной
     */
    @Column(name = "number")
    @Comment(value = "Номер накладной")
    private String number;

    /**
     * Дата поступления имущества
     */
    @Column(name = "date_of_receipt")
    @Comment(value = "Дата поступления имущества")
    private Date dateOfReceipt;

    /**
     * Место размещения принятого имущества
     */
    @Column(name = "location")
    @Comment(value = "Место размещения принятого имущества")
    private String location;

    /**
     * Общая стоимость поступившего имущества
     */
    @Column(name = "total_cost")
    @Comment(value = "Общая стоимость поступившего имущества")
    private Double totalCost;

    /**
     * ФИО человека, сдавшего груз
     */
    @Column(name = "deliverer")
    @Comment(value = "ФИО человека, сдавшего груз")
    private String deliverer;

    /**
     * Сотрудник, принявший груз
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_id")
    @Comment(value = "Идентификатор сотрудника из таблицы \"employee\"")
    private Employee receiver;

    /**
     * Детализация накладной
     */
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<DetailWaybill> details;
}
