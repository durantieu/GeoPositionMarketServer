package it.polito.ApplicazioniInternet.Service;


import it.polito.ApplicazioniInternet.DAO.UserDAO;
import it.polito.ApplicazioniInternet.Model.CustomUser;
import it.polito.ApplicazioniInternet.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;


//Questa classe acchiappa lo User a partire dall'username
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {



    private UserDAO userDAO;

    public UserDetailsService(UserDAO userDAO){
        this.userDAO = userDAO;
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserDAO().getUserDetails(username);

       return new CustomUser(user);
    }
}
