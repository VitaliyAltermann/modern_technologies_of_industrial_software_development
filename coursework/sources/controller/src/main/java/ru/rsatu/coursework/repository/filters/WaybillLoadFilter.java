package ru.rsatu.coursework.repository.filters;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Фильтр для набора накладных
 */
@Getter
@Setter
public class WaybillLoadFilter {
    /**
     * Направление набора данных
     */
    public enum Direction {
        /**
         * Набираем прямо
         */
        FORWARD,
        /**
         * Набираем в обратную сторону
         */
        REVERSE,
    }

    /**
     * Обязательные к заполнению поля
     * @param limit Ограничение на количество набираемых записей
     */
    public WaybillLoadFilter(Long limit) {
        this.limit = limit;
        this.direction = Direction.FORWARD;
    }

    /**
     * Якорь, относительно которого производиться набор накладных.
     * Сам по себе является первичным ключом из таблицы "waybill"
     */
    private Long anchor;
    /**
     * Направление набора накладных
     */
    private Direction direction;
    /**
     * Ограничение на количество набираемых записей
     */
    private Long limit;

    /**
     * Фильтр по номеру накладной
     */
    private String filterByWaybillNumber;
    /**
     * Фильтр по дате поступления накладной.
     * Набирать все накладные поступившие после этой даты.
     */
    private Date filterByBeginDateOfReceipt;
    /**
     * Фильтр по дате поступления накладной.
     * Набирать все накладные поступившие до этой даты.
     */
    private Date filterByEndDateOfReceipt;
    /**
     * Фильтр по общей стоимость товаров по накладной.
     * Набирать все накладные, общая стоимость товаров по которой больше указанной.
     */
    private Double filterByMinCost;
    /**
     * Фильтр по общей стоимость товаров по накладной.
     * Набирать все накладные, общая стоимость товаров по которой меньше указанной.
     */
    private Double filterByMaxCost;
    /**
     * Фильтр по сотруднику, принявшему груз
     */
    private Long filterByReceiverId;
}
