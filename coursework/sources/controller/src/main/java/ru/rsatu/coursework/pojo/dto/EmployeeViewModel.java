package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Модель сотрудника для отображения
 */
@Getter
@Setter
public class EmployeeViewModel {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * ФИО сотрудника
     */
    private String name;
}
