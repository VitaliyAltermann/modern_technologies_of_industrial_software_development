package ru.rsatu.coursework.resource;

import ru.rsatu.coursework.pojo.dto.WaybillSaveModel;
import ru.rsatu.coursework.pojo.dto.WaybillViewFullModel;
import ru.rsatu.coursework.repository.filters.WaybillLoadFilter;
import ru.rsatu.coursework.service.WaybillServ;
import ru.rsatu.coursework.service.listModels.WaybillViewShortModelList;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * API для работы с сервисами накладных
 */
@Path("/coursework/api/v1/waybill")
public class WaybillRes {
    @Inject
    WaybillServ serv;

    /**
     * Получить полный список view-моделей накладных
     * @return список view-моделей накладных
     */
    @GET
    @Path("/loadWaybill")
    @RolesAllowed("readWaybill")
    public List<WaybillViewFullModel> loadFullModels() {
        return serv.loadFullModels().getWaybills();
    }

    /**
     * Получить фильтрованный список view-моделей накладных
     * @return список view-моделей накладных
     */
    @GET
    @Path("/loadWaybillFilter")
    @RolesAllowed("readWaybill")
    @Produces(MediaType.APPLICATION_JSON)
    public WaybillViewShortModelList loadShortModels(@QueryParam("anchor") Long anchor,
                                                     @QueryParam("direction") String direction,
                                                     @QueryParam("limit") Long limit,
                                                     @QueryParam("number") String number,
                                                     @QueryParam("dateMin") String dateMin,
                                                     @QueryParam("dateMax") String dateMax,
                                                     @QueryParam("costMin") String costMin,
                                                     @QueryParam("costMax") String costMax,
                                                     @QueryParam("employeeId") Long employee_id) throws ParseException {
        var filter = new WaybillLoadFilter(limit);
        if (anchor != null && anchor > 0) {
            filter.setAnchor(anchor);
        }
        if(direction != null) {
            if (direction.equals("left")) {
                filter.setDirection(WaybillLoadFilter.Direction.REVERSE);
            } else if (direction.equals("right")) {
                filter.setDirection(WaybillLoadFilter.Direction.FORWARD);
            }
        }
        if (number != null && !number.isEmpty()) {
            filter.setFilterByWaybillNumber(number);
        }
        if (dateMin != null && !dateMin.isEmpty()) {
            var format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            filter.setFilterByBeginDateOfReceipt(format.parse(dateMin));
        }
        if (dateMax != null && !dateMax.isEmpty()) {
            var format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            filter.setFilterByEndDateOfReceipt(format.parse(dateMax));
        }
        if (costMin != null && !costMin.isEmpty()) {
            filter.setFilterByMinCost(Double.valueOf(costMin));
        }
        if (costMax != null && !costMax.isEmpty()) {
            filter.setFilterByMaxCost(Double.valueOf(costMax));
        }
        if (employee_id != null && employee_id > 0) {
            filter.setFilterByReceiverId(employee_id);
        }
        return serv.loadShortModels(filter);
    }

    /**
     * Добавить новую накладную
     * @param model модель накладной
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createWaybill")
    @RolesAllowed("editWaybill")
    public void create(WaybillSaveModel model) {
        serv.save(model);
    }

    /**
     * Обновить данные по накладным
     * @param model модель накладной
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateWaybill")
    @RolesAllowed("editWaybill")
    public void update(WaybillSaveModel model) {
        serv.save(model);
    }
}
