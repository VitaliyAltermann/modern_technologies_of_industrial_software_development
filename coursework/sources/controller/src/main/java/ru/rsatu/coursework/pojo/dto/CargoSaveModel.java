package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Модель имущества для сохранения и обновления
 */
@Getter
@Setter
public class CargoSaveModel {
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
