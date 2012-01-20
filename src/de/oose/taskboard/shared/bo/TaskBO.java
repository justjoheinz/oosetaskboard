package de.oose.taskboard.shared.bo;

import java.io.Serializable;

public class TaskBO implements Serializable {

    private int id;
    private String titel;
    private String beschreibung;
    private String status;

    public TaskBO() {
    }

    public TaskBO(int id, String titel, String beschreibung, String status) {
   	 super();
   	 this.id = id;
   	 this.titel = titel;
   	 this.beschreibung = beschreibung;
   	 this.status = status;
    }

    public int getId() {
   	 return id;
    }

    public void setId(int id) {
   	 this.id = id;
    }

    public String getTitel() {
   	 return titel;
    }

    public void setTitel(String titel) {
   	 this.titel = titel;
    }

    public String getBeschreibung() {
   	 return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
   	 this.beschreibung = beschreibung;
    }

    public String getStatus() {
   	 return status;
    }

    public void setStatus(String status) {
   	 this.status = status;
    }

}