package ru.rsatu.coursework.service.listModels;

import lombok.Getter;
import lombok.Setter;
import ru.rsatu.coursework.pojo.dto.WaybillViewShortModel;

import java.util.List;

/**
 * Модель списка накладных для отображения в списке просмотра
 */
@Getter
@Setter
public class WaybillViewShortModelList {
    /**
     * Результат набора списка накладных
     */
    private List<WaybillViewShortModel> waybills;
    /**
     * Флаг отсутствия данных слева от текущей страницы
     */
    private boolean btnLeftDis = true;
    /**
     * Флаг отсутствия данных справа от текущей страницы
     */
    private boolean btnRightDis = true;
}
