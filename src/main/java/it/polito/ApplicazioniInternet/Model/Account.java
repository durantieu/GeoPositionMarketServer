package it.polito.ApplicazioniInternet.Model;

public class Account {

    private String username;
    private String password;
    private String role;
    private Integer wallet;

    public Account(String username, String password, String role, Integer wallet) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.wallet = wallet;
    }

    public Account() {
    }

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
