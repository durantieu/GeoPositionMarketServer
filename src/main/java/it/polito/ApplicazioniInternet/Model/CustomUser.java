package it.polito.ApplicazioniInternet.Model;

import org.springframework.security.core.userdetails.User;


public class CustomUser extends User {

    public CustomUser(it.polito.ApplicazioniInternet.Model.User myUser){
        super(myUser.getUsername(), myUser.getPassword(), myUser.getAuthorities());
    }

}
