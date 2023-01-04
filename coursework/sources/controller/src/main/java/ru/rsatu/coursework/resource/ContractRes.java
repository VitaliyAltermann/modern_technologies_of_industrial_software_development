package ru.rsatu.coursework.resource;

import ru.rsatu.coursework.pojo.dto.ContractSaveModel;
import ru.rsatu.coursework.pojo.dto.ContractViewModel;
import ru.rsatu.coursework.service.ContractServ;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * API для работы с сервисами договоров
 */
@Path("/coursework/api/v1/contract")
public class ContractRes {
    @Inject
    ContractServ serv;

    /**
     * Получить список view-моделей договоров
     * @return список view-моделей договоров
     */
    @GET
    @Path("/loadContract")
    @RolesAllowed("readContract")
    public List<ContractViewModel> load() {
        return serv.load();
    }

    /**
     * Добавить новый договор
     * @param model модель договора
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createContract")
    @RolesAllowed("editContract")
    public void create(ContractSaveModel model) {
        serv.save(model);
    }

    /**
     * Обновить данные по договору
     * @param model модель договора
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateContract")
    @RolesAllowed("editContract")
    public void update(ContractSaveModel model) {
        serv.save(model);
    }
}
