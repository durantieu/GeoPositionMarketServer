package it.polito.ApplicazioniInternet.Model;

import org.springframework.data.geo.Point;

import java.util.List;


public class CustomGeoJsonPolygon {
    String type;
    List<Coordinates> coordinates;

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CustomGeoJsonPolygon(){

    }

}
