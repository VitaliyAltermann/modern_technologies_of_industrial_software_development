package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Модель накладной для сохранения и обновления
 */
@Getter
@Setter
public class WaybillSaveModel {
    /**
     * Идентификатор накладной
     */
    private Long id;
    /**
     * Идентификатор договора
     */
    private Long contractId;
    /**
     * Номер накладной
     */
    private String number;
    /**
     * Дата поступления имущества
     */
    private Date dateOfReceipt;
    /**
     * Место размещения принятого имущества
     */
    private String location;
    /**
     * ФИО человека, сдавшего груз
     */
    private String deliverer;
    /**
     * Идентификатор сотрудника, принявшего груз
     */
    private Long receiverId;
    /**
     * Детализация накладной
     */
    private List<DetailWaybillSaveModel> detailsWaybill;
}
