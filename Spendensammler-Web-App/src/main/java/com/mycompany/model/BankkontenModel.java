
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import org.primefaces.PrimeFaces;
import spendensammler.jpa.entities.Bankkonto;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Quittung;

/**
 *
 * @author Lukas
 */
@Named(value="bankkontenModel")
@ViewScoped
public class BankkontenModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private UserController userController;
    
    @Inject
    private BankkontoBearbeitenModel bankkontoUpdateModel;
    
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
    
    //Listen:
    private List<Bankkonto> bankkontenListe;
    private List<Bankkonto> bankkontenListeOrga;
    private List<Bankkonto> selectedBankkontenListe;
    
    @PostConstruct
    public void init() {
        if(sessiondata.getBenutzer().getRolleBenutzer().equals("Admin")) {
            bankkontenListe = userController.findAllBankkonten();
        } else {            
            bankkontenListeOrga = userController.findAllBankkontenOrga(sessiondata.getBenutzer().getCharity().getIdCharity());
        }
    }
    
    public String markierteElementeLoeschenBank() {
        if(sessiondata.getBenutzer().getRolleBenutzer().equals("Admin")) {
            for(int i = 0; i < selectedBankkontenListe.size(); i++) {
                userController.elementeLoeschenBankkonto(selectedBankkontenListe.get(i));
                bankkontenListe.remove(selectedBankkontenListe.get(i));
            }
            selectedBankkontenListe.clear();
            return "/Admin/AdminBankkonten.xhtml?faces-redirect=true"; 
        } else {
            for(int i = 0; i < selectedBankkontenListe.size(); i++) {
                userController.elementeLoeschenBankkonto(selectedBankkontenListe.get(i));
                bankkontenListeOrga.remove(selectedBankkontenListe.get(i));
            }
            selectedBankkontenListe.clear();
            return "/Orga/OrgaBankkonto.xhtml?faces-redirect=true";
        }           
    }
    
    public String aendern() {
        if(selectedBankkontenListe.size() > 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", " Sie haben zu viele Objekte für diese Aktion ausgewählt!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else {
            bankkontoUpdateModel.setBankkonto(selectedBankkontenListe.get(0));
            return "/Formulare/BankkontoBearbeiten.xhtml?faces-redirect=true";
        }
    }
    
    public String newBAnkkonto() {
        if(sessiondata.getBenutzer().getRolleBenutzer().equals("Admin")) {
            userController.newBAnkkonto(ibanBankkonto, inhaberBankkonto, bicBankkonto, deletedBankkonto, aktivKonto);
            return "/Admin/AdminBankkonten.xhtml?faces-redirect=true";
        } else {
            Charity charity = sessiondata.getBenutzer().getCharity();
            userController.newBAnkkontoOrga(ibanBankkonto, inhaberBankkonto, bicBankkonto, deletedBankkonto, aktivKonto, charity, paypalid);
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
    
    public String bevorzugtesBankkonto() {
        if(selectedBankkontenListe.size() > 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", " Sie haben zu viele Objekte für diese Aktion ausgewählt!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else {
            bankkontoUpdateModel.setBankkonto(selectedBankkontenListe.get(0));
            userController.benutzerBankkontoSetzten(bankkontoUpdateModel.getBankkonto(), sessiondata.getBenutzer().getCharity(), bankkontenListeOrga);
            
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

    public List<Bankkonto> getBankkontenListeOrga() {
        return bankkontenListeOrga;
    }

    public void setBankkontenListeOrga(List<Bankkonto> bankkontenListeOrga) {
        this.bankkontenListeOrga = bankkontenListeOrga;
    }

    public String getPaypalid() {
        return paypalid;
    }

    public void setPaypalid(String paypalid) {
        this.paypalid = paypalid;
    }
    
    
}
