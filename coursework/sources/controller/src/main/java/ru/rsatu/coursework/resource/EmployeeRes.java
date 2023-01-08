package ru.rsatu.coursework.resource;

import ru.rsatu.coursework.pojo.dto.EmployeeSaveModel;
import ru.rsatu.coursework.pojo.dto.EmployeeViewModel;
import ru.rsatu.coursework.service.EmployeeServ;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * API для работы с сервисами сотрудников
 */
@Path("/coursework/api/v1/employee")
public class EmployeeRes {
    @Inject
    EmployeeServ serv;

    /**
     * Получить список view-моделей сотрудников
     * @return список view-моделей сотрудников
     */
    @GET
    @Path("/loadEmployee")
    @RolesAllowed("readEmployee")
    public List<EmployeeViewModel> load() {
        return serv.load();
    }

    /**
     * Добавить нового сотрудника
     * @param model модель сотрудника
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createEmployee")
    @RolesAllowed("editEmployee")
    public void create(EmployeeSaveModel model) {
        serv.save(model);
    }

    /**
     * Обновить данные по сотруднику
     * @param model модель сотрудника
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateEmployee")
    @RolesAllowed("editEmployee")
    public void update(EmployeeSaveModel model) {
        serv.save(model);
    }
}
