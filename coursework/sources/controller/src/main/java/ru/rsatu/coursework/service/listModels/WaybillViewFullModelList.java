package ru.rsatu.coursework.service.listModels;

import lombok.Getter;
import lombok.Setter;
import ru.rsatu.coursework.pojo.dto.WaybillViewFullModel;

import java.util.List;

/**
 * Модель списка накладных для отображения при создании и редактировании накладных
 */
@Getter
@Setter
public class WaybillViewFullModelList {
    /**
     * Результат набора списка накладных
     */
    private List<WaybillViewFullModel> waybills;
    /**
     * Флаг отсутствия данных слева от текущей страницы
     */
    private boolean btnLeftDis = true;
    /**
     * Флаг отсутствия данных справа от текущей страницы
     */
    private boolean btnRightDis = true;
}
