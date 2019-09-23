package it.polito.ApplicazioniInternet.Controllers;

import it.polito.ApplicazioniInternet.Model.Account;
import it.polito.ApplicazioniInternet.Model.ResponseRole;
import it.polito.ApplicazioniInternet.Model.User;
import it.polito.ApplicazioniInternet.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class Registration {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin(origins = "localhost:4200")
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody() Account account){
        if(account.getUsername().equals("") || account.getPassword().equals("") || account.getWallet()<0 || account.getRole().equals("ADMIN")){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(userDetailsService.getUserDAO().isPresent(account.getUsername())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(account.getRole().equals("USER") || account.getRole().equals("CUSTOMER") || account.getRole().equals("ADMIN")){
            int[] color = new int[3];
            color[0] = randInt(0, 255);
            color[1] = randInt(0, 255);
            color[2] = randInt(0, 255);
            User newUser = new User(account.getUsername(), passwordEncoder.encode(account.getPassword()), account.getRole(), account.getWallet(), color );
            userDetailsService.getUserDAO().create(newUser);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @CrossOrigin(origins = "localhost:4200")
    @PreAuthorize("hasAnyAuthority('USER', 'CUSTOMER', 'ADMIN')")
    @RequestMapping(value = "/getRole", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity ruolo(){
        String user =(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<GrantedAuthority> myRoles = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        ResponseRole myResponseRole = new ResponseRole();
        try{
            if(myRoles.size() == 1){
                myResponseRole.setId(user);
                myResponseRole.setRole(myRoles.get(0).toString());
                return new ResponseEntity<ResponseRole>(myResponseRole, HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        }

    }

    public static int randInt(int min, int max) {

        Random rand = new Random();
        return  rand.nextInt((max - min) + 1) + min;
    }





}
