package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Модель договора для отображения
 */
@Getter
@Setter
public class ContractViewModel {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Дата создания договора в формате "2022-09-21"
     */
    private String date;
    /**
     * Номер договора
     */
    private String number;
    /**
     * Наименование контрагента
     */
    private String contractor;
}
