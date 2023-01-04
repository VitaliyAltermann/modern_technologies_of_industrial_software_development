package ru.rsatu.coursework.service;

import ru.rsatu.coursework.mapper.ContractMapper;
import ru.rsatu.coursework.pojo.dto.ContractSaveModel;
import ru.rsatu.coursework.pojo.dto.ContractViewModel;
import ru.rsatu.coursework.repository.ContractRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с договорами
 */
@ApplicationScoped
public class ContractServ {
    @Inject
    ContractMapper mapper;
    @Inject
    ContractRep rep;

    /**
     * Получить полный список договоров
     * @return полный список договоров
     */
    public List<ContractViewModel> load() {
        return rep.load().stream()
                .map(mapper::toContractViewModel)
                .collect(Collectors.toList());
    }

    /**
     * Сохранить договор
     * @param model модель договора
     */
    public void save(ContractSaveModel model) {
        rep.save(mapper.toContract(model));
    }
}
