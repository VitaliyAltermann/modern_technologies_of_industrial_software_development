package ru.rsatu.pojo;

import java.util.Date;

/**
 * Интерфейс работы с документами
 */
public interface IDocument {
    /**
     * Получить наименование документа
     * @return имя документа в строковом виде
     */
    public String getType();

    /**
     * Получить номер документа
     * @return номер документа в строковом виде
     */
    public String getNumber();

    /**
     * Получить дату выдачи документа
     * @return дата выдачи документа
     */
    public Date getDateOfIssue();
}
