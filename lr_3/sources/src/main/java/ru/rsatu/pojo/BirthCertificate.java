package ru.rsatu.pojo;

import java.util.Date;

public class BirthCertificate extends Document{
    public BirthCertificate(String name, String namber, Date dateOfIssue) {
        this(name, namber, dateOfIssue, "-", "-");
    }
    public BirthCertificate(String name, String namber, Date dateOfIssue, String mother, String father) {
        super(name, namber, dateOfIssue);
        this.mother = mother;
        this.father = father;
    }

    /**
     * Получить имя матери
     * @return имя матери
     */
    public String getMother() {
        return mother;
    }

    /**
     * Получить имя матери
     * @return имя матери
     */
    public String getFather() {
        return father;
    }

    /**
     * Мать
     */
    private String mother = "-";

    /**
     * Отец
     */
    private String father = "-";
}
