package ru.rsatu.coursework;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import ru.rsatu.coursework.mapper.DetailWaybillMapper;

import javax.inject.Inject;

@QuarkusTest
public class DetailWaybillMapperTest {
    @Inject
    DetailWaybillMapper mapper;

    @Test
    public void testDetailWaybillViewModel() {
        //@todo Добавить тест маппера
    }

}