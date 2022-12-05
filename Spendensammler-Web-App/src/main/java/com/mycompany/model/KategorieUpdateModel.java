
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.model.SessionData;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedProperty;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Kategorie;

@Named(value="kategorieUpdateModel")
@SessionScoped
public class KategorieUpdateModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private IndexModel indexView;
    
    @Inject
    private KategorieformularModel kategorieFormularView;
    
    @Inject
    private SessionData sessionData;
    
    private Long idKategorie;
    private String nameKategorie;
    
    
    private Kategorie selectedKategorie;

    @PostConstruct
    public void intit() {
    }

    public String updateKategorie() {
        charityController.upadateKategorie(selectedKategorie.getIdKategorie(), selectedKategorie.getNameKategorie());
        return "/Orga/OrgaKategorien.xhtml?faces-redirect=true";
    }
    
    public String updateSelectedKategorie(Kategorie kategorie) {
        setSelectedKategorie(kategorie);
        return "/KategorieformularUpdate.xhtml?faces-redirect=true";
    }
    
    /**
     * Abbrechen Funktion für die Eingabeformulare:
     * Redirect auf die Startseite, damit alle Werte verworfen werden.
     * @return 
     */
    public String abbrechen() {
        return "/Orga/OrgaKategorien.xhtml?faces-redirect=true";        
    }
    
    
    /**
     * 
     * Beginn Getter und Setter:
     *  
     */
    public Kategorie getSelectedKategorie() {
        return selectedKategorie;
    }

    public void setSelectedKategorie(Kategorie selectedKategorie) {
        this.selectedKategorie = selectedKategorie;
    }

    public Long getIdKategorie() {
        return idKategorie;
    }

    public void setIdKategorie(Long idKategorie) {
        this.idKategorie = idKategorie;
    }

    public String getNameKategorie() {
        return nameKategorie;
    }

    public void setNameKategorie(String nameKategorie) {
        this.nameKategorie = nameKategorie;
    }
     
}