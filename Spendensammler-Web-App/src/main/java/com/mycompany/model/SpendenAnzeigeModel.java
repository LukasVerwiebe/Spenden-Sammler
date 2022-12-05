
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import spendensammler.jpa.entities.Quittung;

/**
 *
 * @author Lukas
 */
@Named(value="spendenAnzeigeModel") 
@RequestScoped
public class SpendenAnzeigeModel implements Serializable {
    
    
    @Inject
    private SessionData sessiondata;
    
    @Inject
    private SessionData sessionData;
    
    @Inject
    private UserController userController;
    
    @Inject
    private CharityController charityController;
    
    private List<Quittung> quittungen = new ArrayList<Quittung>();
    private Quittung quittung;
    
    @PostConstruct
    public void init() {
        quittungen = spendenanzeige();
    }
    
    public List<Quittung> spendenanzeige() {
        List<Quittung> quittungNormal = charityController.getQuittungen();        
        List<Quittung> quittungGedreht = new ArrayList<Quittung>();
        int zaehler = quittungNormal.size() -1;
        for(int i = 0; i <= 4; i++) {
            quittungGedreht.add(quittungNormal.get(zaehler));
            zaehler = zaehler - 1;
        }        
        return quittungGedreht;
    } 
    
    
    
    // Getter und Setter
    public Quittung getQuittung() {
        return quittung;
    }

    public void setQuittung(Quittung quittung) {
        this.quittung = quittung;
    }

    public List<Quittung> getQuittungen() {
        return quittungen;
    }

    public void setQuittungen(List<Quittung> quittungen) {
        this.quittungen = quittungen;
    }
    
    
    
}
