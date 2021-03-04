package org.example.DVDDatabase.models;


public class DVD {

    private int id;
    private String dvd;
    private String note;
    private boolean finished;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDVD() {
        return dvd;
    }

    public void setDVD(String todo) {
        this.dvd = dvd;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}

