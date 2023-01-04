package ru.rsatu.coursework.repository;

import ru.rsatu.coursework.pojo.entity.Cargo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с имуществом
 */
@ApplicationScoped
public class CargoRep {
    @Inject
    EntityManager entityManager;

    /**
     * Загрузить список имущества
     * @return список имущества
     */
    public List<Cargo> load() {
        return entityManager.createQuery("select tbl from Cargo tbl", Cargo.class)
                .getResultList();
    }

    /**
     * Сохранить имущество в БД
     * @param db_model модель имущества для сохранения
     */
    @Transactional
    public void save(Cargo db_model) {
        if (db_model.getId() != null) {
            entityManager.merge(db_model);
        } else {
            entityManager.persist(db_model);
        }
        entityManager.flush();
    }
}
