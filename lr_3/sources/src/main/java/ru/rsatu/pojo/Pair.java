package ru.rsatu.pojo;

/**
 * Шаблонный класс пары
 * @param <First> тип левого значения
 * @param <Second> тип правого значения
 */
public class Pair<First,Second> {
    public Pair() {
    }
    public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }


    public First getFirst() {
        return first;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public Second getSecond() {
        return second;
    }

    public void setSecond(Second second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "{\'" + first.toString() + "\', \'" + second.toString() + "\'}";
    }

    /**
     * Левое значение
     */
    private First first;
    /**
     * Правое занчение
     */
    private Second second;
}
