package ru.rsatu.coursework;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import ru.rsatu.coursework.mapper.CargoMapper;

import javax.inject.Inject;

@QuarkusTest
public class CargoMapperTest {
    @Inject
    CargoMapper mapper;

    @Test
    public void testCargoViewModel() {
        //@todo Добавить тест маппера
    }

}