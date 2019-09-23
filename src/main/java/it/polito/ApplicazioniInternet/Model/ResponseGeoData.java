package it.polito.ApplicazioniInternet.Model;

import java.util.List;

public class ResponseGeoData {
    private List<ResponsePosition> posizioni;
    private int nArchivi;
    private List<Long> timeStamps;
    private int nUtenti;

    public int getnArchivi() {
        return nArchivi;
    }

    public void setnArchivi(int nArchivi) {
        this.nArchivi = nArchivi;
    }

    public int getnUtenti() {
        return nUtenti;
    }

    public void setnUtenti(int nUtenti) {
        this.nUtenti = nUtenti;
    }

    public ResponseGeoData() {
    }

    public List<ResponsePosition> getPosizioni() {
        return posizioni;
    }

    public void setPosizioni(List<ResponsePosition> posizioni) {
        this.posizioni = posizioni;
    }

    public List<Long> getTimeStamps() {
        return timeStamps;
    }

    public void setTimeStamps(List<Long> timeStamps) {
        this.timeStamps = timeStamps;
    }

}


