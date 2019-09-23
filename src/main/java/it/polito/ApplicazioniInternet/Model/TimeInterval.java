package it.polito.ApplicazioniInternet.Model;

import java.sql.Time;

public class TimeInterval {
    private long timeStampPartenza;
    private long timeStampArrivo;

    public TimeInterval(long timeStampPartenza, long timeStampArrivo) {
        this.timeStampPartenza = timeStampPartenza;
        this.timeStampArrivo = timeStampArrivo;
    }
    public TimeInterval(){

    }

    public long getTimeStampPartenza() {
        return timeStampPartenza;
    }

    public void setTimeStampPartenza(long timeStampPartenza) {
        this.timeStampPartenza = timeStampPartenza;
    }

    public long getTimeStampArrivo() {
        return timeStampArrivo;
    }

    public void setTimeStampArrivo(long timeStampArrivo) {
        this.timeStampArrivo = timeStampArrivo;
    }
}
