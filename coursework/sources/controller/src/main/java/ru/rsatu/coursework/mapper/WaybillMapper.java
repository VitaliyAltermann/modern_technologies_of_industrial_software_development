package ru.rsatu.coursework.mapper;

import io.quarkus.security.identity.SecurityIdentity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.coursework.pojo.dto.*;
import ru.rsatu.coursework.pojo.entity.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Маппер модели Waybill в WaybillSaveModel, WaybillViewShortModel и WaybillViewFullModel
 */
@Mapper(componentModel = "cdi", imports = {SimpleDateFormat.class}, uses = DetailWaybillMapper.class)
public abstract class WaybillMapper {
    @Inject
    SecurityIdentity securityIdentity;
    @Inject
    EntityManager entityManager;
    @Inject
    DetailWaybillMapper detailWaybillMapper;

    /**
     * Преобразовать в модель представления
     * @param from модель БД
     * @return модель представления
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "contractId", source = "contract.id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "dateOfReceipt", expression = "java(" +
            "new SimpleDateFormat(\"yyyy-MM-dd\").format(from.getDateOfReceipt())" +
            ")")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "deliverer", source = "deliverer")
    @Mapping(target = "receiverId", source = "receiver.id")
    public abstract WaybillViewFullModel toWaybillViewFullModel(Waybill from);

    /**
     * Преобразовать в модель представления
     * @param from модель БД
     * @return модель представления
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "contract", expression = "java(" +
            "\"№ \" + from.getContract().getNumber() + \" от \" + " +
            "new SimpleDateFormat(\"dd.MM.yyyy\").format(from.getContract().getDateOfCreate())" +
            ")")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "dateOfReceipt", expression = "java(" +
            "new SimpleDateFormat(\"dd.MM.yyyy\").format(from.getDateOfReceipt())" +
            ")")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "cost", source = "totalCost")
    @Mapping(target = "deliverer", source = "deliverer")
    @Mapping(target = "receiver", source = "receiver.name")
    public abstract WaybillViewShortModel toWaybillViewShortModel(Waybill from);

    /**
     * Преобразовать в модель представления
     * @param from модель БД
     * @return модель представления
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "contractId", source = "contract.id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "dateOfReceipt", source = "dateOfReceipt")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "deliverer", source = "deliverer")
    @Mapping(target = "receiverId", source = "receiver.id")
    public abstract WaybillSaveModel toWaybillSaveModel( Waybill from);

    /**
     * Преобразовать в модель БД
     * @param from модель представления
     * @return модель БД
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "dateOfReceipt", source = "dateOfReceipt")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "deliverer", source = "deliverer")
    public abstract Waybill toWaybill( WaybillSaveModel from);

    /**
     * Догрузка данных в модель представления
     * @param view_model модель представления
     * @param from модель БД
     */
    @AfterMapping
    protected void updateWaybillAfterMapping(@MappingTarget WaybillViewShortModel view_model, Waybill from) {
        List<DetailWaybillViewShortModel> details = from.getDetails().stream()
                .map(detailWaybillMapper::toDetailWaybillViewShortModel)
                .collect(Collectors.toList());
        view_model.setDetailsWaybill(details);
    }

    /**
     * Догрузка данных в модель представления
     * @param view_model модель представления
     * @param from модель БД
     */
    @AfterMapping
    protected void updateWaybillAfterMapping(@MappingTarget WaybillViewFullModel view_model, Waybill from) {
        var details = from.getDetails().stream()
                .map(detailWaybillMapper::toDetailWaybillViewFullModel)
                .collect(Collectors.toList());
        view_model.setDetailsWaybill(details);
    }

    /**
     * Догрузка данных в модель БД
     * @param db_model модель БД
     * @param from модель представления
     */
    @AfterMapping
    protected void updateWaybillAfterMapping(@MappingTarget Waybill db_model, WaybillSaveModel from) {
        db_model.setContract(entityManager.getReference(Contract.class, from.getContractId()));
        db_model.setReceiver(entityManager.getReference(Employee.class, from.getReceiverId()));

        // Добавляем детализацию
        var details = from.getDetailsWaybill().stream()
                .map(detailWaybillMapper::toDetailWaybill)
                .collect(Collectors.toList());
        db_model.setDetails(details);

        // Обновляем общую стоимость
        double total_cost = 0.0;
        for (DetailWaybill detail : details) {
            total_cost += detail.getPrice() * detail.getQuantity();
        }
        db_model.setTotalCost(total_cost);
    }

    /**
     * Постобработка модели БД
     * @param db_model модель БД
     */
    @AfterMapping
    protected void updateWaybillAfterMapping(@MappingTarget Waybill db_model) {
        db_model.setRecordChangeAuthor(securityIdentity.getPrincipal().getName());
        db_model.setRecordChangeTS(new Timestamp(System.currentTimeMillis()));
    }
}
