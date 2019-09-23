package it.polito.ApplicazioniInternet.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Document(collection = "Users")
public class User {
    @Id
    private String username;
    private String password;
    private Collection<SimpleGrantedAuthority> authorities;
    private String permissions;
    private Integer wallet;
    private int[] color;

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public User(String username, String password, String permissions, Integer wallet, int[] color){
        this.username = username;
        this.password = password;
        this.permissions = permissions;
        this.wallet = wallet;
        authorities = new ArrayList<>();
        ((ArrayList<SimpleGrantedAuthority>) authorities).add(new SimpleGrantedAuthority(permissions));
        this.color = color;
    }

    public User(String username, String password, String permissions) {
        this.username = username;
        this.password = password;
        this.permissions = permissions;
        authorities= new ArrayList<>();
        ((ArrayList<SimpleGrantedAuthority>) authorities).add(new SimpleGrantedAuthority(permissions));
    }
    public User(){
    }
    public String getPermissions() {
        return permissions;
    }
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
     public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
    }
}
