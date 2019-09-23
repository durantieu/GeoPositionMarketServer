package it.polito.ApplicazioniInternet.Service;


import it.polito.ApplicazioniInternet.DAO.ArchivioDAO;
import it.polito.ApplicazioniInternet.Model.Archivio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ArchiveService")
public class ArchiveService {
    private ArchivioDAO archivioDAO;

    public ArchiveService(ArchivioDAO archivioDAO){
        this.archivioDAO = archivioDAO;
    }
    @Autowired
    public ArchivioDAO getArchivioDAO(){
        return archivioDAO;
    }

    public List<Archivio> findAll(){
        return archivioDAO.findAll();
    }

    public  List<Archivio> findByOwner(String own){
        return archivioDAO.findByOwner(own);
    }

    //Inserire i metodi wrapper di ArchivioDAO
}
