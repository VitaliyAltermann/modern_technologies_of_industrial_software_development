package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Модель накладной для отображения при создании и редактировании накладных
 */
@Getter
@Setter
public class WaybillViewFullModel {
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
     * Дата поступления имущества в формате "2022-09-21"
     */
    private String dateOfReceipt;
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
    private List<DetailWaybillViewFullModel> detailsWaybill;
}
