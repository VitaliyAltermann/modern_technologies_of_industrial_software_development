package ru.rsatu.coursework.repository;

import ru.rsatu.coursework.pojo.entity.Contract;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с договорами
 */
@ApplicationScoped
public class ContractRep {
    @Inject
    EntityManager entityManager;

    /**
     * Загрузить список договоров
     * @return список договоров
     */
    public List<Contract> load() {
        return entityManager.createQuery("from Contract tbl order by tbl.number ASC", Contract.class)
                .getResultList();
    }

    /**
     * Сохранить договор в БД
     * @param db_model модель договора для сохранения
     */
    @Transactional
    public void save(Contract db_model) {
        if (db_model.getId() != null) {
            entityManager.merge(db_model);
        } else {
            entityManager.persist(db_model);
        }
        entityManager.flush();
    }
}
