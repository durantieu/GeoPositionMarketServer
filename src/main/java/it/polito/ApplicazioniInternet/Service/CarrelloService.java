package it.polito.ApplicazioniInternet.Service;

import it.polito.ApplicazioniInternet.DAO.ArchivioDAO;
import it.polito.ApplicazioniInternet.DAO.CarrelloDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CarrelloService")
public class CarrelloService {
    private CarrelloDAO carrelloDAO;

    public CarrelloService(CarrelloDAO carrelloDAO) {
        this.carrelloDAO = carrelloDAO;
    }
    @Autowired
    public CarrelloDAO getCarrelloDAO(){
        return carrelloDAO;
    }
}
