package ru.rsatu.pojo;

import java.util.Date;

public class Passport extends Document{

    public Passport(String name, String namber, Date dateOfIssue) {
        super(name, namber, dateOfIssue);
    }

    /**
     * Получить текущий адрес прописки
     * @return текущий адрес
     */
    public String getResidenceAddress() {
        return residenceAddress;
    }

    /**
     * Установить новый адрес прописки
     * @param residenceAddress - новый адрес
     */
    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    /**
     * Адрес прописки
     */
    private String residenceAddress = "БОМЖ";

}
