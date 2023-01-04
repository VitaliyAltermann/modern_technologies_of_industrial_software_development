package ru.rsatu.coursework.repository;

import ru.rsatu.coursework.pojo.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с сотрудниками
 */
@ApplicationScoped
public class EmployeeRep {
    @Inject
    EntityManager entityManager;

    /**
     * Загрузить список сотрудников
     * @return список сотрудников
     */
    public List<Employee> load() {
        return entityManager.createQuery("select tbl from Employee tbl", Employee.class)
                .getResultList();
    }

    /**
     * Сохранить сотрудника в БД
     * @param db_model модель сотрудника для сохранения
     */
    @Transactional
    public void save(Employee db_model) {
        if (db_model.getId() != null) {
            entityManager.merge(db_model);
        } else {
            entityManager.persist(db_model);
        }
        entityManager.flush();
    }
}
