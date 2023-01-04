package ru.rsatu.coursework.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.coursework.pojo.dto.CargoViewModel;
import ru.rsatu.coursework.pojo.entity.Cargo;
import ru.rsatu.coursework.pojo.dto.CargoSaveModel;

import java.sql.Timestamp;

/**
 * Маппер модели Cargo в CargoSaveModel и CargoViewModel
 */
@Mapper(componentModel = "cdi")
public abstract class CargoMapper {
    /**
     * Преобразовать в модель представления
     * @param from модель БД
     * @return модель представления
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "unit", source = "unitOfMeasure")
    public abstract CargoViewModel toCargoViewModel(Cargo from);

    /**
     * Преобразовать в модель представления
     * @param from модель БД
     * @return модель представления
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "unit", source = "unitOfMeasure")
    public abstract CargoSaveModel toCargoSaveModel(Cargo from);

    /**
     * Преобразовать в модель БД
     * @param from модель представления
     * @return модель БД
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "unitOfMeasure", source = "unit")
    public abstract Cargo toCargo(CargoSaveModel from);

    /**
     * Постобработка модели БД
     * @param db_model модель БД
     */
    @AfterMapping
    protected void updateCargoAfterMapping(@MappingTarget Cargo db_model) {
        db_model.setRecordChangeTS( new Timestamp(System.currentTimeMillis()));
    }
}
