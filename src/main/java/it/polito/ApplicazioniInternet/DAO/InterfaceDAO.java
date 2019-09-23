package it.polito.ApplicazioniInternet.DAO;

import it.polito.ApplicazioniInternet.Model.Position;
import it.polito.ApplicazioniInternet.Model.User;

public interface InterfaceDAO {

    public void create(Object position);
    public Object readById(String id);
    public void update(Object position);
    public int deleteById(String id);
}
