package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Модель детализации накладной для отображения в списке накладных
 */
@Getter
@Setter
public class DetailWaybillViewShortModel {
    /**
     * Наименование имущества.
     * Имеет следующий формат: "[cargoName], [unitOfMeasure]"
     */
    private String cargo;
    /**
     * Количество единиц имущества
     */
    private Long number;
    /**
     * Цена за единицу имущества
     */
    private Double price;
    /**
     * Стоимость имущества
     */
    private Double cost;
}
