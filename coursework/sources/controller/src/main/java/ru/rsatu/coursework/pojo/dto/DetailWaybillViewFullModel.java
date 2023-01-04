package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Модель детализации накладной для отображения при создании и редактировании накладных
 */
@Getter
@Setter
public class DetailWaybillViewFullModel {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Идентификатор имущества
     */
    private Long cargoId;
    /**
     * Наименование имущества.
     * Имеет следующий формат: "[cargo.name], [cargo.unitOfMeasure]"
     */
    private String cargoName;
    /**
     * Количество единиц имущества
     */
    private Long number;
    /**
     * Цена за единицу имущества
     */
    private Double price;
}
