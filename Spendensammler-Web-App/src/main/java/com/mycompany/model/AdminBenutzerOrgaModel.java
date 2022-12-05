
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import com.mycompany.model.SessionData;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedProperty;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ColumnResizeEvent;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Charity;

@Named(value="adminBenutzerOrgaModel")
@ViewScoped
public class AdminBenutzerOrgaModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private UserController userController;
    
    @Inject
    private EingabeUpdateModel eingabeUpdateView;
    
    @Inject
    private BenutzerBearbeitenAdminModel benutzerBearbeitenAdminModel;
    
    @Inject
    private SelektionModel selektionView;
    
    @Inject
    private SessionData sessiondata;
    
    private List<Benutzer> benutzerList;
    private Benutzer selectedBenutzer;
    private List<Benutzer> selectedBenutzerList;
    
    private List<Charity> charityList;
    private Charity selectedCharity;
    private List<Charity> selectedCharitys;
    
    @PostConstruct
    public void init() {
        benutzerList = userController.findAllUserOrga();
    }
    
    /**
     * Funktion zum Löschen von Ausgewählten Objekten:
     * Es wird eine Map mit allen Charity Objekten gefüllt, in dieser wird 
     * fetgehalten, ob die Checkbox zum Lsöchen angehakt war oder nicht.
     * 
     * Wenn nicht, werden die Objekte im nächsten Schritt wieder entfernt aus
     * der Liste, sodass nur die benötigten Obejkte übrig bleiben.
     * Diese werden dann anhand der ID identifiziert und aus der Datenbank
     * enfernt.
     * @return 
     */
    public String markierteElementeLoeschen() {
        for(int i = 0; i < selectedBenutzerList.size(); i++) {
            userController.elementeLoeschenBenutzer(selectedBenutzerList.get(i));
        }
        benutzerList = userController.findAll(); 
        selectedBenutzerList.clear();
        return "/Admin/AdminBenutzerOrga.xhtml?faces-redirect=true";    
    }
    
    public String benutzerStatusSetzten() {
        for(int i = 0; i < selectedBenutzerList.size(); i++) {
            userController.benutzerStatusSetzten(selectedBenutzerList.get(i));
        }
        benutzerList = userController.findAll(); 
        selectedBenutzerList.clear();        
        return "/Admin/AdminBenutzerOrga.xhtml?faces-redirect=true";
    }
    
    public String aendern() {
        if(selectedBenutzerList.size() > 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", " Sie haben zu viele Objekte für diese Aktion ausgewählt!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else {
            benutzerBearbeitenAdminModel.setSelectedBenutzer(selectedBenutzerList.get(0));
            return "/Admin/BenutzerBearbeitenAdmin.xhtml?faces-redirect=true";
        }
    }
    
    public String updateSelectedBenutzer(Benutzer benutzer) {
        benutzerBearbeitenAdminModel.setSelectedBenutzer(benutzer);
        return "/Admin/BenutzerBearbeitenAdmin.xhtml?faces-redirect=true";
    }

    
    
    /** 
     * Beginn Getter und Setter:  
     */
    public List<Benutzer> getBenutzerList() {
        return benutzerList;
    }

    public void setBenutzerList(List<Benutzer> benutzerList) {
        this.benutzerList = benutzerList;
    }

    public Benutzer getSelectedBenutzer() {
        return selectedBenutzer;
    }

    public void setSelectedBenutzer(Benutzer selectedBenutzer) {
        this.selectedBenutzer = selectedBenutzer;
    }

    public List<Benutzer> getSelectedBenutzerList() {
        return selectedBenutzerList;
    }

    public void setSelectedBenutzerList(List<Benutzer> selectedBenutzerList) {
        this.selectedBenutzerList = selectedBenutzerList;
    }

    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public List<Charity> getCharityList() {
        return charityList;
    }

    public void setCharityList(List<Charity> charityList) {
        this.charityList = charityList;
    }

    public Charity getSelectedCharity() {
        return selectedCharity;
    }

    public void setSelectedCharity(Charity selectedCharity) {
        this.selectedCharity = selectedCharity;
    }

    public List<Charity> getSelectedCharitys() {
        return selectedCharitys;
    }

    public void setSelectedCharitys(List<Charity> selectedCharitys) {
        this.selectedCharitys = selectedCharitys;
    }
    
    
}