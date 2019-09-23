package it.polito.ApplicazioniInternet.Model;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.awt.*;
import java.util.Objects;

public class Coordinates implements Comparable<Double>{

    private long timeStamp;
    private double latitudine;
    private double longitudine;


    public Coordinates(double latitudine , double longitudine, long time ) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        timeStamp = time;
    }
    public Coordinates(){


    }

    public double getLatitudine() {
        return latitudine;
    }


    public double getLongitudine() {
        return longitudine;
    }


    public long getTimeStamp() {
        return timeStamp;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return timeStamp == that.timeStamp &&
                Double.compare(that.latitudine, latitudine) == 0 &&
                Double.compare(that.longitudine, longitudine) == 0;
    }


    @Override
    public int hashCode() {
        return Objects.hash(timeStamp, latitudine, longitudine);
    }

    @Override
    public int compareTo(Double o) {
        double lastCmp = timeStamp - o;
        return ((int)lastCmp != 0 ? (int)lastCmp : 0);
    }
}
