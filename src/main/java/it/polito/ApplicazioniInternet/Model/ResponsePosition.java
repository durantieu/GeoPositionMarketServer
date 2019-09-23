package it.polito.ApplicazioniInternet.Model;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public class ResponsePosition {

    private String user;
    private int[] color;
    private ResponseArchivio archivio;
    private boolean acquistato;
    GeoJsonPoint geoJsonPoint;

    public ResponsePosition(String user, int[] color, ResponseArchivio archivio, boolean acquistato, GeoJsonPoint geoJsonPoint) {
        this.user = user;
        this.color = color;
        this.archivio = archivio;
        this.acquistato = acquistato;
        this.geoJsonPoint = geoJsonPoint;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public ResponseArchivio getArchivio() {
        return archivio;
    }

    public void setArchivio(ResponseArchivio archivio) {
        this.archivio = archivio;
    }

    public boolean isAcquistato() {
        return acquistato;
    }

    public void setAcquistato(boolean acquistato) {
        this.acquistato = acquistato;
    }

    public GeoJsonPoint getGeoJsonPoint() {
        return geoJsonPoint;
    }

    public void setGeoJsonPoint(GeoJsonPoint geoJsonPoint) {
        this.geoJsonPoint = geoJsonPoint;
    }
    public Double getLatitudine(){
        if(geoJsonPoint != null){
            return geoJsonPoint.getX();
        }
        return  null;
    }
    public Double getLongitudine(){
        if(geoJsonPoint!= null){
            return geoJsonPoint.getY();
        }
        return null;
    }

}
