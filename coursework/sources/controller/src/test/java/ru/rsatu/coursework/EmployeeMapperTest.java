package ru.rsatu.coursework;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import ru.rsatu.coursework.mapper.EmployeeMapper;

import javax.inject.Inject;

@QuarkusTest
public class EmployeeMapperTest {
    @Inject
    EmployeeMapper mapper;

    @Test
    public void testEmployeeViewModel() {
        //@todo Добавить тест маппера
    }

}