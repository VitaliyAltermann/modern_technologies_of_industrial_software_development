package ru.rsatu.coursework.repository;

import ru.rsatu.coursework.pojo.entity.Waybill;
import ru.rsatu.coursework.repository.filters.WaybillLoadFilter;
import ru.rsatu.coursework.repository.listModels.WaybillList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий для работы с накладными
 */
@ApplicationScoped
public class WaybillRep {
    @Inject
    EntityManager entityManager;

    /**
     * Загрузить полный список накладных
     * @return список накладных
     */
    public WaybillList load() {
        var result = new WaybillList();
        result.setWaybills(entityManager.createQuery(
                "from Waybill tbl order by tbl.number ASC",
                Waybill.class
        ).getResultList());
        return result;
    }

    /**
     * Загрузить фильтрованный список накладных
     * @param filter фильтр для набора накладных
     * @return список накладных
     */
    public WaybillList load(WaybillLoadFilter filter) {
        // Подготавливаем части запроса
        String query_part_select = "SELECT * FROM waybill tbl ";
        String query_part_where = "WHERE tbl.id > 0 ";
        String query_part_order = "ORDER BY (CAST(tbl.date_of_receipt AS TEXT) || CAST(tbl.id AS TEXT)) " + (
                filter.getDirection() == WaybillLoadFilter.Direction.FORWARD ? "DESC" : "ASC") + " LIMIT :limitValue";

        // Формируем фильтры для запроса
        if (filter.getAnchor() != null) {
            query_part_where += "and (" +
                    "SELECT CAST(sub_tbl.date_of_receipt AS TEXT) || CAST(sub_tbl.id AS TEXT) " +
                    "FROM waybill sub_tbl " +
                    "WHERE sub_tbl.id = :anchor) " +
                    (filter.getDirection() == WaybillLoadFilter.Direction.FORWARD ? ">" : "<") +
                    " (CAST(tbl.date_of_receipt AS TEXT) || CAST(tbl.id AS TEXT)) ";
        }
        if (filter.getFilterByWaybillNumber() != null) {
            query_part_where += "AND tbl.number LIKE '%' || :waybillNumber || '%' ";
        }
        if (filter.getFilterByBeginDateOfReceipt() != null) {
            query_part_where += "AND tbl.date_of_receipt >= :beginDateOfReceipt ";
        }
        if (filter.getFilterByEndDateOfReceipt() != null) {
            query_part_where += "AND tbl.date_of_receipt <= :endDateOfReceipt ";
        }
        if (filter.getFilterByMinCost() != null) {
            query_part_where += "AND tbl.total_cost >= :minCost ";
        }
        if (filter.getFilterByMaxCost() != null) {
            query_part_where += "AND tbl.total_cost <= :maxCost ";
        }
        if (filter.getFilterByReceiverId() != null) {
            query_part_where += "AND tbl.receiver_id = :receiverId ";
        }

        String result_query_str = query_part_select + query_part_where + query_part_order;
        if (filter.getDirection() == WaybillLoadFilter.Direction.REVERSE) {
            result_query_str = "SELECT * FROM ( " + result_query_str + " ) tbl " +
                    "ORDER BY (CAST(tbl.date_of_receipt AS TEXT) || CAST(tbl.id AS TEXT)) DESC";
        }

        // Собираем сам запрос
        var query = entityManager.createNativeQuery(result_query_str, Waybill.class);
        if (filter.getAnchor() != null) {
            query.setParameter("anchor", filter.getAnchor());
        }
        if (filter.getFilterByWaybillNumber() != null) {
            query.setParameter("waybillNumber", filter.getFilterByWaybillNumber());
        }
        if (filter.getFilterByBeginDateOfReceipt() != null) {
            query.setParameter("beginDateOfReceipt", filter.getFilterByBeginDateOfReceipt());
        }
        if (filter.getFilterByEndDateOfReceipt() != null) {
            query.setParameter("endDateOfReceipt", filter.getFilterByEndDateOfReceipt());
        }
        if (filter.getFilterByMinCost() != null) {
            query.setParameter("minCost", filter.getFilterByMinCost());
        }
        if (filter.getFilterByMaxCost() != null) {
            query.setParameter("maxCost", filter.getFilterByMaxCost());
        }
        if (filter.getFilterByReceiverId() != null) {
            query.setParameter("receiverId", filter.getFilterByReceiverId());
        }
        query.setParameter("limitValue",  filter.getLimit() + 1);

        // Получаем модели и доп информацию
        List<Waybill> waybills = query.getResultList();
        boolean have_more = waybills.size() > filter.getLimit();
        if (have_more) {
            if (filter.getDirection() == WaybillLoadFilter.Direction.FORWARD) {
                waybills.remove(waybills.size() - 1);
            } else {
                waybills.remove(0);
            }
        }

        // Формируем результат
        WaybillList result = new WaybillList();
        result.setWaybills(waybills);
        result.setHaveMore(have_more);
        return result;
    }

    /**
     * Сохранить накладную в БД
     * @param db_model модель накладной для сохранения
     */
    @Transactional
    public void save(Waybill db_model) {
        entityManager.merge(db_model);
        entityManager.flush();
    }
}
