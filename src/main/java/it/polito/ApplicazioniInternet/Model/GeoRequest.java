package it.polito.ApplicazioniInternet.Model;


import java.util.List;

public class GeoRequest {
    private CustomGeoJsonPolygon poligono;
    private TimeInterval intervalloTemporale;
    private List<String> utenti;

    public List<String> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<String> utenti) {
        this.utenti = utenti;
    }

    public GeoRequest(CustomGeoJsonPolygon poligono, TimeInterval timeInterval) {
        this.poligono = poligono;
        this.intervalloTemporale = timeInterval;
    }
    public GeoRequest(){
    }

    public CustomGeoJsonPolygon getPoligono() {
        return poligono;
    }

    public void setPoligono(CustomGeoJsonPolygon poligono) {
        this.poligono = poligono;
    }

    public TimeInterval getIntervalloTemporale() {
        return intervalloTemporale;
    }

    public void setIntervalloTemporale(TimeInterval intervalloTemporale) {
        this.intervalloTemporale = intervalloTemporale;
    }
}
