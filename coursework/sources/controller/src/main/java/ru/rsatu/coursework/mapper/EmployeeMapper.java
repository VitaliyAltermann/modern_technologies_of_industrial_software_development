package ru.rsatu.coursework.mapper;

import io.quarkus.security.identity.SecurityIdentity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.coursework.pojo.dto.EmployeeSaveModel;
import ru.rsatu.coursework.pojo.dto.EmployeeViewModel;
import ru.rsatu.coursework.pojo.entity.Employee;

import javax.inject.Inject;
import java.sql.Timestamp;

/**
 * Маппер модели Employee в EmployeeSaveModel и EmployeeViewModel
 */
@Mapper(componentModel = "cdi")
public abstract class EmployeeMapper {
    @Inject
    SecurityIdentity securityIdentity;

    /**
     * Преобразовать в модель представления
     * @param from модель БД
     * @return модель представления
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    public abstract EmployeeViewModel toEmployeeViewModel(Employee from);

    /**
     * Преобразовать в модель БД
     * @param from модель представления
     * @return модель БД
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    public abstract Employee toEmployee(EmployeeSaveModel from);

    /**
     * Постобработка модели БД
     * @param db_model модель БД
     */
    @AfterMapping
    protected void updateEmployeeAfterMapping(@MappingTarget Employee db_model) {
        db_model.setRecordChangeAuthor(securityIdentity.getPrincipal().getName());
        db_model.setRecordChangeTS(new Timestamp(System.currentTimeMillis()));
    }
}
