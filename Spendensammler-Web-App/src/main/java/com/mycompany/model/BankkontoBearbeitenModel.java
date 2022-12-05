
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import spendensammler.jpa.entities.Bankkonto;

/**
 *
 * @author Lukas
 */
@Named(value="bankkontoBearbeitenModel")
@SessionScoped
public class BankkontoBearbeitenModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private UserController userController;
    
    @Inject
    private AdminQuittungUpdateModel quittungUpdateModel;
    
    @Inject
    private SessionData sessiondata;
    
    // Variablen:
    private Long idBankkonto;    
    private String ibanBankkonto;
    private String inhaberBankkonto;
    private String bicBankkonto;
    private boolean deletedBankkonto;
    private boolean aktivKonto;
    private String paypalid;
    
    private Bankkonto bankkonto;
    
    //Listen:
    private List<Bankkonto> bankkontenListe;
    private List<Bankkonto> selectedBankkontenListe;
    
    @PostConstruct
    public void init() {
    }
    
    public String updateBankkonto() {
        userController.updateBankkonto(bankkonto.getIdBankkonto(), bankkonto.getIbanBankkonto(), bankkonto.getInhaberBankkonto(), bankkonto.getBicBankkonto(), 
                bankkonto.isDeletedBankkonto(), bankkonto.isAktivKonto(), bankkonto.getPaypalid());
        if(sessiondata.getBenutzer().getRolleBenutzer().equals("Admin")) {
            return "/Admin/AdminBankkonten.xhtml?faces-redirect=true";  
        } else {
            return "/Orga/OrgaBankkonto.xhtml?faces-redirect=true";
        }     
    } 
        
    public String abbrechen() {
        if(sessiondata.getBenutzer().getRolleBenutzer().equals("Admin")) {
            return "/Admin/AdminBankkonten.xhtml?faces-redirect=true"; 
        } else {
            return "/Orga/OrgaBankkonto.xhtml?faces-redirect=true"; 
        }                        
    }
    
    // Getter und Setter:

    public Long getIdBankkonto() {
        return idBankkonto;
    }

    public void setIdBankkonto(Long idBankkonto) {
        this.idBankkonto = idBankkonto;
    }

    public String getIbanBankkonto() {
        return ibanBankkonto;
    }

    public void setIbanBankkonto(String ibanBankkonto) {
        this.ibanBankkonto = ibanBankkonto;
    }

    public String getInhaberBankkonto() {
        return inhaberBankkonto;
    }

    public void setInhaberBankkonto(String inhaberBankkonto) {
        this.inhaberBankkonto = inhaberBankkonto;
    }

    public String getBicBankkonto() {
        return bicBankkonto;
    }

    public void setBicBankkonto(String bicBankkonto) {
        this.bicBankkonto = bicBankkonto;
    }

    public boolean isDeletedBankkonto() {
        return deletedBankkonto;
    }

    public void setDeletedBankkonto(boolean deletedBankkonto) {
        this.deletedBankkonto = deletedBankkonto;
    }

    public boolean isAktivKonto() {
        return aktivKonto;
    }

    public void setAktivKonto(boolean aktivKonto) {
        this.aktivKonto = aktivKonto;
    }

    public Bankkonto getBankkonto() {
        return bankkonto;
    }

    public void setBankkonto(Bankkonto bankkonto) {
        this.bankkonto = bankkonto;
    }

    public List<Bankkonto> getBankkontenListe() {
        return bankkontenListe;
    }

    public void setBankkontenListe(List<Bankkonto> bankkontenListe) {
        this.bankkontenListe = bankkontenListe;
    }

    public List<Bankkonto> getSelectedBankkontenListe() {
        return selectedBankkontenListe;
    }

    public void setSelectedBankkontenListe(List<Bankkonto> selectedBankkontenListe) {
        this.selectedBankkontenListe = selectedBankkontenListe;
    }

    public String getPaypalid() {
        return paypalid;
    }

    public void setPaypalid(String paypalid) {
        this.paypalid = paypalid;
    }
    
    
}
