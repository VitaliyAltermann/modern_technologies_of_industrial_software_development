package ru.rsatu.pojo;

import java.util.Date;

/**
 * Базовая реализация интерфейса работы с документами
 */
public abstract class Document implements IDocument {
    public Document(String name, String namber, Date dateOfIssue) {
        this.name = name;
        this.namber = namber;
        this.dateOfIssue = dateOfIssue;
    }

    @Override
    public String getType() {
        return name;
    }

    @Override
    public String getNumber() {
        return namber;
    }

    @Override
    public Date getDateOfIssue() {
        return dateOfIssue;
    }


    /**
     * Наименование документа
     */
    private String name;

    /**
     * Номер документа
     */
    private String namber;

    /**
     * Дата выдачи документа
     */
    private Date dateOfIssue;
}
