package ru.rsatu.coursework;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import ru.rsatu.coursework.mapper.ContractMapper;

import javax.inject.Inject;

@QuarkusTest
public class ContractMapperTest {
    @Inject
    ContractMapper mapper;

    @Test
    public void testContractViewModel() {
        //@todo Добавить тест маппера
    }

}