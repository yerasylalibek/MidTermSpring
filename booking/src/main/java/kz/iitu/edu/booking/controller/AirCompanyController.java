package kz.iitu.edu.booking.controller;

import kz.iitu.edu.booking.model.AirCompany;
import kz.iitu.edu.booking.service.IAirCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AirCompanyController {

    @Autowired
    IAirCompanyService iAirCompanyService;

    public void createAirCompany(AirCompany airCompany){
        iAirCompanyService.createAirCompany(airCompany);
    }

    public void deleteAirCompany(Integer id){
        iAirCompanyService.deleteAirCompany(id);
    }

    public List<AirCompany> getAllAirCompanies(){
        return iAirCompanyService.getAllAirCompanies();
    }

    public AirCompany getAirCompanyByName(String name){
        return iAirCompanyService.getAirCompanyByName(name);
    }

}
