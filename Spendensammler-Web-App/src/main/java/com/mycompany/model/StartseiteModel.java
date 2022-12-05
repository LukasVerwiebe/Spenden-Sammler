
package com.mycompany.model;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import org.primefaces.PrimeFaces;
import spendensammler.jpa.entities.Benutzer;

/**
 *
 * @author Lukas
 */
@Named(value="startseiteModel")
@SessionScoped
public class StartseiteModel implements Serializable {
    
    @Inject
    private SessionData sessionData;
    
    @PostConstruct
    public void intit() {
    }
    
    public String einstellungen() {
        Benutzer benutzer;
        
        if(sessionData.getBenutzer() != null) {
            benutzer = sessionData.getBenutzer();                 
        
            if(benutzer.getRolleBenutzer().equals("Benutzer")) {
                return "/User/BenutzerVerwaltung.xhtml?faces-redirect=true";
            } else if(benutzer.getRolleBenutzer().equals("Admin")) {
                return "/Admin/AdminVerwaltung.xhtml?faces-redirect=true";
            } else if(benutzer.getRolleBenutzer().equals("Organisation")) {
                return "/Orga/OrgaVerwaltung.xhtml?faces-redirect=true";
            } 
        
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", " Sie sind nicht Eingeloggt!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        }
        return null;
    }
    
}