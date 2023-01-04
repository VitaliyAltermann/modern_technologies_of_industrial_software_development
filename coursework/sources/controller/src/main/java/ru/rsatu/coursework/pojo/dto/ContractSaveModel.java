package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Модель договора для сохранения и обновления
 */
@Getter
@Setter
public class ContractSaveModel {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Дата создания договора
     */
    private Date date;
    /**
     * Номер договора
     */
    private String number;
    /**
     * Наименование контрагента
     */
    private String contractor;
}
