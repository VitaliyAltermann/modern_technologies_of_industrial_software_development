package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Модель детализации накладной для сохранения и обновления
 */
@Getter
@Setter
public class DetailWaybillSaveModel {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Идентификатор имущества
     */
    private Long cargoId;
    /**
     * Количество единиц имущества
     */
    private Long number;
    /**
     * Цена за единицу имущества
     */
    private Double price;
}
