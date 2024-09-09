package transfer;

import java.io.Serializable;

import konstante.StatusServerskogOdgovora;

public class ServerskiOdgovor implements Serializable {

    private Object odgovor;
    private Exception exc;
    private StatusServerskogOdgovora statusOdgovora;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, Exception exc, StatusServerskogOdgovora statusOdgovora) {
        this.odgovor = odgovor;
        this.exc = exc;
        this.statusOdgovora = statusOdgovora;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Exception getExc() {
        return exc;
    }

    public void setExc(Exception exc) {
        this.exc = exc;
    }

    public StatusServerskogOdgovora getStatusOdgovora() {
        return statusOdgovora;
    }

    public void setStatusOdgovora(StatusServerskogOdgovora statusOdgovora) {
        this.statusOdgovora = statusOdgovora;
    }

  

}
