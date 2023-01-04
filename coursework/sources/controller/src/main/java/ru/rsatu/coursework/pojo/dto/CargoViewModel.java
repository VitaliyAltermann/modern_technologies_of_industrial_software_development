package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Модель имущества для отображения
 */
@Getter
@Setter
public class CargoViewModel {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Наименование
     */
    private String name;
    /**
     * Единицы измерения
     */
    private String unit;
}
