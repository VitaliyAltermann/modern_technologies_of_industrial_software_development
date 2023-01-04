package ru.rsatu.coursework.service;

import ru.rsatu.coursework.mapper.WaybillMapper;
import ru.rsatu.coursework.pojo.dto.WaybillSaveModel;
import ru.rsatu.coursework.pojo.dto.WaybillViewFullModel;
import ru.rsatu.coursework.pojo.dto.WaybillViewShortModel;
import ru.rsatu.coursework.repository.WaybillRep;
import ru.rsatu.coursework.repository.filters.WaybillLoadFilter;
import ru.rsatu.coursework.repository.listModels.WaybillList;
import ru.rsatu.coursework.service.listModels.WaybillViewFullModelList;
import ru.rsatu.coursework.service.listModels.WaybillViewShortModelList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с накладными
 */
@ApplicationScoped
public class WaybillServ {
    @Inject
    WaybillMapper mapper;
    @Inject
    WaybillRep rep;

    /**
     * Получить полный список накладных в виде полных поделей
     * @return полный список накладных
     */
    public WaybillViewFullModelList loadFullModels() {
        var result = new WaybillViewFullModelList();
        result.setWaybills(rep.load().getWaybills().stream()
                .map(mapper::toWaybillViewFullModel)
                .collect(Collectors.toList()));
        return result;
    }

    /**
     * Получить фильтрованный список накладных в виде сокращённых моделей
     * @return фильтрованный список накладных
     */
    public WaybillViewShortModelList loadShortModels(WaybillLoadFilter filter) {
        var waybills = rep.load(filter);
        var result = new WaybillViewShortModelList();
        result.setWaybills(waybills.getWaybills().stream()
                .map(mapper::toWaybillViewShortModel)
                .collect(Collectors.toList()));
        // Скрываем кнопку набора предыдущейстраницы если:
        // 1) Сейчас набираем начало реестра
        // 2) При наборе в обратную сторону больше нет записей
        result.setBtnLeftDis(
                (filter.getAnchor() == null && filter.getDirection() == WaybillLoadFilter.Direction.FORWARD) ||
                (!waybills.isHaveMore() && filter.getDirection() == WaybillLoadFilter.Direction.REVERSE));
        // Скрываем кнопку набора следующей если при наборе прямо больше нет записей
        result.setBtnRightDis(filter.getDirection() == WaybillLoadFilter.Direction.FORWARD && !waybills.isHaveMore());
        return result;
    }

    /**
     * Сохранить накладную
     * @param model модель накладной
     */
    public void save(WaybillSaveModel model) {
        rep.save(mapper.toWaybill(model));
    }
}
