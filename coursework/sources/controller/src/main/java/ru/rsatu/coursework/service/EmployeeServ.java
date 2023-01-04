package ru.rsatu.coursework.service;

import ru.rsatu.coursework.mapper.EmployeeMapper;
import ru.rsatu.coursework.pojo.dto.EmployeeSaveModel;
import ru.rsatu.coursework.pojo.dto.EmployeeViewModel;
import ru.rsatu.coursework.repository.EmployeeRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с сотрудниками
 */
@ApplicationScoped
public class EmployeeServ {
    @Inject
    EmployeeMapper mapper;
    @Inject
    EmployeeRep rep;

    /**
     * Получить полный список сотрудников
     * @return полный список сотрудников
     */
    public List<EmployeeViewModel> load() {
        return rep.load().stream()
                .map(mapper::toEmployeeViewModel)
                .collect(Collectors.toList());
    }

    /**
     * Сохранить сотрудника
     * @param model модель сотрудника
     */
    public void save(EmployeeSaveModel model) {
        rep.save(mapper.toEmployee(model));
    }
}
