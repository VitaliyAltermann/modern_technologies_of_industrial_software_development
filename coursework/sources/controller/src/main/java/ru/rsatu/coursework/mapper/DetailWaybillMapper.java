package ru.rsatu.coursework.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.coursework.pojo.dto.DetailWaybillSaveModel;
import ru.rsatu.coursework.pojo.dto.DetailWaybillViewFullModel;
import ru.rsatu.coursework.pojo.dto.DetailWaybillViewShortModel;
import ru.rsatu.coursework.pojo.entity.Cargo;
import ru.rsatu.coursework.pojo.entity.DetailWaybill;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.Timestamp;

/**
 * Маппер модели DetailWaybill в DetailWaybillSaveModel, DetailWaybillViewShortModel и DetailWaybillViewFullModel
 */
@Mapper(componentModel = "cdi", imports = {Cargo.class, String.class})
public abstract class DetailWaybillMapper {
    @Inject
    EntityManager entityManager;

    /**
     * Преобразовать в модель представления
     * @param from модель БД
     * @return модель представления
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "cargoId", source = "cargo.id")
    @Mapping(target = "cargoName", expression = "java(" +
            "from.getCargo().getName() + \", \" + from.getCargo().getUnitOfMeasure()" +
            ")")
    @Mapping(target = "number", source = "quantity")
    @Mapping(target = "price", source = "price")
    public abstract DetailWaybillViewFullModel toDetailWaybillViewFullModel(DetailWaybill from);

    /**
     * Преобразовать в модель представления
     * @param from модель БД
     * @return модель представления
     */
    @Mapping(target = "cargo", expression = "java(" +
            "from.getCargo().getName() + \", \" + from.getCargo().getUnitOfMeasure()" +
            ")")
    @Mapping(target = "number", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "cost", expression = "java(from.getPrice()*from.getQuantity())")
    public abstract DetailWaybillViewShortModel toDetailWaybillViewShortModel(DetailWaybill from);

    /**
     * Преобразовать в модель представления
     * @param from модель БД
     * @return модель представления
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "cargoId", source = "cargo.id")
    @Mapping(target = "number", source = "quantity")
    @Mapping(target = "price", source = "price")
    public abstract DetailWaybillSaveModel toDetailWaybillSaveModel(DetailWaybill from);

    /**
     * Преобразовать в модель БД
     * @param from модель представления
     * @return модель БД
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "quantity", source = "number")
    @Mapping(target = "price", source = "price")
    public abstract DetailWaybill toDetailWaybill(DetailWaybillSaveModel from);

    /**
     * Догрузка данных в модель БД
     * @param db_model модель БД
     * @param from модель представления
     */
    @AfterMapping
    public void updateDetailWaybillAfterMapping(@MappingTarget DetailWaybill db_model, DetailWaybillSaveModel from) {
        db_model.setCargo(entityManager.getReference(Cargo.class, from.getCargoId()));
    }

    /**
     * Постобработка модели БД
     * @param db_model модель БД
     */
    @AfterMapping
    protected void updateDetailWaybillAfterMapping(@MappingTarget DetailWaybill db_model) {
        db_model.setRecordChangeTS( new Timestamp(System.currentTimeMillis()));
    }
}
