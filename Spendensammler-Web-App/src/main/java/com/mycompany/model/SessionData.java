
package com.mycompany.model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Kategorie;

@Named(value="sessionData")
@SessionScoped
public class SessionData implements Serializable{    

    private Charity selectedCharity;    
    private List<Charity> selectedCharitys;
    private Boolean selektionCheck = false;
    private Benutzer benutzer;
    private Benutzer benutzerOrga;
    private Kategorie selectedKategorie;
    private Charity spendenCharity;
    private boolean eingeloggt;
    
    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }   

    public void setSelectedCharitys(List<Charity> selectedCharitys) {
        this.selectedCharitys = selectedCharitys;
    }

    public List<Charity> getSelectedCharitys() {
        return selectedCharitys;
    }

    public Charity getSelectedCharity() {
        return selectedCharity;
    }

    public void setSelectedCharity(Charity selectedCharity) {
        this.selectedCharity = selectedCharity;
    }

    public Boolean getSelektionCheck() {
        return selektionCheck;
    }

    public void setSelektionCheck(Boolean selektionCheck) {
        this.selektionCheck = selektionCheck;
    }

    public Benutzer getBenutzerOrga() {
        return benutzerOrga;
    }

    public void setBenutzerOrga(Benutzer benutzerOrga) {
        this.benutzerOrga = benutzerOrga;
    }

    public Kategorie getSelectedKategorie() {
        return selectedKategorie;
    }

    public void setSelectedKategorie(Kategorie selectedKategorie) {
        this.selectedKategorie = selectedKategorie;
    }

    public Charity getSpendenCharity() {
        return spendenCharity;
    }

    public void setSpendenCharity(Charity spendenCharity) {
        this.spendenCharity = spendenCharity;
    }

    public boolean isEingeloggt() {
        return eingeloggt;
    }

    public void setEingeloggt(boolean eingeloggt) {
        this.eingeloggt = eingeloggt;
    }
    
}