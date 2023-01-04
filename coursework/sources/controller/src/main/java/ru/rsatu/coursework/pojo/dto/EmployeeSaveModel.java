package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Модель сотрудника для сохранения и обновления
 */
@Getter
@Setter
public class EmployeeSaveModel {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * ФИО сотрудника
     */
    private String name;
}
