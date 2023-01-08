package ru.rsatu.coursework;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import ru.rsatu.coursework.mapper.WaybillMapper;

import javax.inject.Inject;

@QuarkusTest
public class WaybillMapperTest {
    @Inject
    WaybillMapper mapper;

    @Test
    public void testWaybillViewModel() {
        //@todo Добавить тест маппера
    }

}