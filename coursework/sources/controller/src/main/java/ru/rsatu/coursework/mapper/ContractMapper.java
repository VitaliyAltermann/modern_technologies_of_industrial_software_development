package ru.rsatu.coursework.mapper;

import io.quarkus.security.identity.SecurityIdentity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.coursework.pojo.dto.ContractSaveModel;
import ru.rsatu.coursework.pojo.dto.ContractViewModel;
import ru.rsatu.coursework.pojo.entity.Contract;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Маппер модели Contract в ContractSaveModel и ContractViewModel
 */
@Mapper(componentModel = "cdi", imports = {SimpleDateFormat.class})
public abstract class ContractMapper {
    @Inject
    SecurityIdentity securityIdentity;

    /**
     * Преобразовать в модель представления
     * @param from модель БД
     * @return модель представления
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "date", expression = "java(new SimpleDateFormat(\"yyyy-MM-dd\").format(from.getDateOfCreate()))")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "contractor", source = "contractor")
    public abstract ContractViewModel toContractViewModel(Contract from);

    /**
     * Преобразовать в модель представления
     * @param from модель БД
     * @return модель представления
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "date", source = "dateOfCreate")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "contractor", source = "contractor")
    public abstract ContractSaveModel toContractSaveModel(Contract from);

    /**
     * Преобразовать в модель БД
     * @param from модель представления
     * @return модель БД
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dateOfCreate", source = "date")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "contractor", source = "contractor")
    public abstract Contract toContract(ContractSaveModel from);

    /**
     * Постобработка модели БД
     * @param db_model модель БД
     */
    @AfterMapping
    protected void updateContractAfterMapping(@MappingTarget Contract db_model) {
        db_model.setRecordChangeAuthor(securityIdentity.getPrincipal().getName());
        db_model.setRecordChangeTS(new Timestamp(System.currentTimeMillis()));
    }
}
