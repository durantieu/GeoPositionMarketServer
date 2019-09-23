package it.polito.ApplicazioniInternet.Service;


import it.polito.ApplicazioniInternet.DAO.PositionDAO;
import it.polito.ApplicazioniInternet.Model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PositionService")
public class PositionService {

    private PositionDAO positionDAO;

    public PositionService(PositionDAO positionDAO){
        this.positionDAO=positionDAO;
    }

    @Autowired
    public PositionDAO getPositionDAO() {
        return positionDAO;
    }
    public List<Position> getPositions (String username){
        return positionDAO.getPositionsByOwner(username);
    }
    public List<Position> findAllPositions(){
        return positionDAO.findAll();
    }
    public List<Position> getPointsIntoPoly(GeoJsonPolygon poly){
        return positionDAO.getPositionInsidePoly(poly);
    }

}
