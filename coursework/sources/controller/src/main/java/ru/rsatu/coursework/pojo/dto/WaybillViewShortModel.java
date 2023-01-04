package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Модель накладной для отображения в списке просмотра
 */
@Getter
@Setter
public class WaybillViewShortModel {
    /**
     * Идентификатор накладной
     */
    private Long id;
    /**
     * Номер договора.
     * Имеет следующий формат: "№ [contract.number] от [contract.dateOfCreate]"
     */
    private String contract;
    /**
     * Номер накладной
     */
    private String number;
    /**
     * Дата поступления имущества в формате "21.09.2022"
     */
    private String dateOfReceipt;
    /**
     * Место размещения принятого имущества
     */
    private String location;
    /**
     * Общая стоимость имущества по накладной
     */
    private Double cost;
    /**
     * ФИО человека, сдавшего груз
     */
    private String deliverer;
    /**
     * ФИО сотрудника, принявшего груз
     */
    private String receiver;
    /**
     * Детализация накладной
     */
    private List<DetailWaybillViewShortModel> detailsWaybill;
}
