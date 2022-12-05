
package com.mycompany.model;

import com.mycompany.controller.CharityController;
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
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Kategorie;
import spendensammler.jpa.entities.PlaceOfAction;

@Named(value="indexModel")
@ViewScoped
public class IndexModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private EingabeUpdateModel eingabeUpdateView;
    
    @Inject
    private SelektionModel selektionView;
    
    @Inject
    private SessionData sessiondata;
    
    @Inject
    private SpendenModel spendenModel;
    
    private List<Charity> charityList;
    private Charity selectedCharity;
    private List<Charity> selectedCharitys;
    private boolean globalFilterOnly;
    private String txt1;
    
    //Für den Filter
    private String worldview = "";
    private String land = "";
    private String category = "";
    
    @PostConstruct
    public void init() {
        charityList = charityController.findAll();
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
        for(int i = 0; i < selectedCharitys.size(); i++) {
            charityController.elementeLoeschen(selectedCharitys.get(i));
        }
        charityList = charityController.findAll(); 
        selectedCharitys.clear();
        return "/index.xhtml?faces-redirect=true";    
    }
    
    public String aendern() {
        if(selectedCharitys.size() > 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", " Sie haben zu viele Objekte für diese Aktion ausgewählt!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else {
            eingabeUpdateView.setSelectedCharity(selectedCharitys.get(0));
            return "/EingabeUpdate.xhtml?faces-redirect=true";
        }
    }
    
    public String selektionAuswahl() {
        if(selectedCharitys.size() >= 1) {
            sessiondata.setSelectedCharitys(selectedCharitys);
            sessiondata.setSelektionCheck(true);
            return "/Selektion.xhtml?faces-redirect=true";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sie haben keine Elemente Ausgewählt!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        }
    }
    
    public List<String> completeText(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> countryList = new ArrayList<>();
        List<Charity> charitys = charityController.findAll();
        for (Charity charity : charitys) {
            countryList.add(charity.getName());
        }
        return countryList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }
    
    

    public List<String> completeTextPlaces(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> placesList = new ArrayList<>();
        List<String> places = charityController.findAllPlaces();
        for (String place : places) {
            placesList.add(place);
        }
        return placesList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }
    
    public List<String> completeTextCategories(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> categoryList = new ArrayList<>();
        List<String> categories = charityController.findAllCategories();
        for (String category : categories) {
            categoryList.add(category);
        }
        return categoryList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }
    
    public String filtern() {
        selectedCharitys = charityController.gefilterteCharities(this.worldview, this.land, this.category);
        sessiondata.setSelectedCharitys(selectedCharitys);
        sessiondata.setSelektionCheck(true);
        return "/Selektion.xhtml?faces-redirect=true";
    }  
    
    public String orgaOeffnen() {
        sessiondata.setSelectedCharity(getSelectedCharity());
        return "/presentationOrga.xhtml?faces-redirect=true";
        
    }
        
    public String test() {
        return "/test.xhtml?faces-redirect=true";
    }
    
    
    /**
     * 
     * Beginn Getter und Setter:
     *  
     */
    
    public List<Charity> getCharitys() {
        charityList = charityController.findAll();        
        return charityList;
    }   

    public CharityController getCharityController() {
        return charityController;
    }

    public List<Charity> getCharityList() {
        return charityList;
    }

    public List<Charity> getSelectedCharitys() {
        return selectedCharitys;
    }

    public Charity getSelectedCharity() {        
        return selectedCharity;
    }

    public void setCharityController(CharityController charityController) {
        this.charityController = charityController;
    }

    public void setCharityList(List<Charity> charityList) {
        this.charityList = charityList;
    }

    public void setSelectedCharity(Charity selectedCharity) {
        this.selectedCharity = selectedCharity;
    }

    public void setSelectedCharitys(List<Charity> selectedCharitys) {
        this.selectedCharitys = selectedCharitys;
    }

    public boolean isGlobalFilterOnly() {
        return globalFilterOnly;
    }

    public void setGlobalFilterOnly(boolean globalFilterOnly) {
        this.globalFilterOnly = globalFilterOnly;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public String getWorldview() {
        return worldview;
    }

    public void setWorldview(String worldview) {
        this.worldview = worldview;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public List<String> getWorldviews() {
        return charityController.getWorldviews();
    }
    
}