package it.polito.ApplicazioniInternet.Model;

import it.polito.ApplicazioniInternet.DAO.UserDAO;
import it.polito.ApplicazioniInternet.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "Positions")
public class Position {

    @Id
    private String Id;
    private String user;
    private Long timeStamp;
    private GeoJsonPoint geoPoint;
    private String archivio;

    public Position(String owner, GeoJsonPoint geoJsonPoint, long timeStamp) {
        this.user = owner;
        this.geoPoint = geoJsonPoint;
        this.timeStamp = timeStamp;
        Date tmp = new Date();
        SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yy");
        String s = myFormat.format(tmp);
        String seqNumber = s.substring(3);
        archivio = owner + '_' + seqNumber;
    }
    public Position(){
    }

    public Double getLatitudine(){
        if(geoPoint != null){
            return geoPoint.getY();
        }
       return  null;
    }
    public Double getLongitudine(){
        if(geoPoint!= null){
            return geoPoint.getX();
        }
            return null;
    }
    public GeoJsonPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoJsonPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public String getOwner() {
        return user;
    }

    public void setOwner(String owner) {
        this.user = owner;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getArchivio() {
        return archivio;
    }

}
