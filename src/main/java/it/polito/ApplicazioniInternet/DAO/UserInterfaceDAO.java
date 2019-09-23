package it.polito.ApplicazioniInternet.DAO;

import it.polito.ApplicazioniInternet.Model.User;

public interface UserInterfaceDAO {


    public void create(User user);
    public User readById(String id);
    public void update(User user);
    public int deleteById(String id);
}
