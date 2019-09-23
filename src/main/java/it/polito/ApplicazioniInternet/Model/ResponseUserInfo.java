package it.polito.ApplicazioniInternet.Model;

import java.util.List;

public class ResponseUserInfo {
    private String username;
    private int wallet;
    private List<ResponseArchivio> archiviAcquistati;

    public ResponseUserInfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public List<ResponseArchivio> getArchiviAcquistati() {
        return archiviAcquistati;
    }

    public void setArchiviAcquistati(List<ResponseArchivio> archiviAcquistati) {
        this.archiviAcquistati = archiviAcquistati;
    }
}
