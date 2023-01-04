package ru.rsatu.coursework.resource;

import ru.rsatu.coursework.pojo.dto.CargoSaveModel;
import ru.rsatu.coursework.pojo.dto.CargoViewModel;
import ru.rsatu.coursework.service.CargoServ;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * API для работы с сервисами имущества
 */
@Path("/coursework/api/v1/cargo")
public class CargoRes {
    @Inject
    CargoServ serv;

    /**
     * Получить список view-моделей имущества
     * @return список view-моделей имущества
     */
    @GET
    @Path("/loadCargo")
    @RolesAllowed("readCargo")
    public List<CargoViewModel> load() {
        return serv.load();
    }

    /**
     * Добавить новое имущество
     * @param model модель имущества
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createCargo")
    @RolesAllowed("editCargo")
    public void create(CargoSaveModel model) {
        serv.save(model);
    }

    /**
     * Обновить данные по имуществу
     * @param model модель имущества
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateCargo")
    @RolesAllowed("editCargo")
    public void update(CargoSaveModel model) {
        serv.save(model);
    }
}
