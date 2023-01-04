package ru.rsatu.coursework.service;

import ru.rsatu.coursework.mapper.CargoMapper;
import ru.rsatu.coursework.pojo.dto.CargoSaveModel;
import ru.rsatu.coursework.pojo.dto.CargoViewModel;
import ru.rsatu.coursework.repository.CargoRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с имуществом
 */
@ApplicationScoped
public class CargoServ {
    @Inject
    CargoMapper mapper;
    @Inject
    CargoRep rep;

    /**
     * Получить полный список имущества
     * @return полный список имущества
     */
    public List<CargoViewModel> load() {
        return rep.load().stream()
                .map(mapper::toCargoViewModel)
                .collect(Collectors.toList());
    }

    /**
     * Сохранить имущество
     * @param model модель имущества
     */
    public void save(CargoSaveModel model) {
        rep.save(mapper.toCargo(model));
    }
}
