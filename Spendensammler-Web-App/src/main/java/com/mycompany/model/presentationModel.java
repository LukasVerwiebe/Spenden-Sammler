
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.primefaces.PrimeFaces;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Einkommen;
import spendensammler.jpa.entities.Kategorie;

/**
 *
 * @author Timo
 */
@Named(value="presentationModel") // Nur für den Fall das eine weitere Bean mit selben Namen verwendet werden muss
@RequestScoped
public class presentationModel {
    
    @Inject
    private SessionData sessionData;
    
    @Inject
    private CharityController charityController;
    
    private Charity charity;
    private List<String> categoryList;
    private List<String> placeList;
    
    @PostConstruct
    public void init() {
        charity = sessionData.getSelectedCharity();
        
        
    }
    
    public String orgaAnzeigen(Charity charity) {
        sessionData.setSelectedCharity(charity); 
        
        return "/presentationOrga.xhtml?faces-redirect=true";
    }
    
    public List<String> getCategories(){
        categoryList = charityController.findCategories(charity.getIdCharity());
        return categoryList;
    }
    
    public List<String> getPlaces(){
        placeList = charityController.findPlaces(charity.getIdCharity());
        return placeList;
    }
    
    public double getJahrEinkommen() {
        Date dt=new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(dt);
        int year = calendar.get(Calendar.YEAR);
        List<Einkommen> einkommenList = charityController.jahrEinkommen(year, charity.getIdCharity());
        if(einkommenList.size() > 0) {
            return einkommenList.get(0).getGeldEinkommen();
        } else {
            return 0;
        }
    }
    
    public int getAktuellesJahr() {
        Date dt=new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(dt);
        int year = calendar.get(Calendar.YEAR);
        
        return year;
    }
    
    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }
    
    
    
}