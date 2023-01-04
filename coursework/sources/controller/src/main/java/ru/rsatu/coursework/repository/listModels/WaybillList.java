package ru.rsatu.coursework.repository.listModels;

import lombok.Getter;
import lombok.Setter;
import ru.rsatu.coursework.pojo.entity.Waybill;

import java.util.List;

/**
 * Модель списка накладных
 */
@Getter
@Setter
public class WaybillList {
    /**
     * Результат набора списка накладных
     */
    private List<Waybill> waybills;
    /**
     * Есть ещё элементы для догрузки
     */
    private boolean haveMore = false;
}
