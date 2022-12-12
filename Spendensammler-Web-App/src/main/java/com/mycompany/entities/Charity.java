
package spendensammler.jpa.entities;

import jakarta.persistence.CascadeType;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author lu80238
 */
@Entity
public class Charity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCharity;
    
    private String name;    
    private String logoPath;
    private int foundingYear;
    private String homepage;
    private String worldview;
    private boolean deleted;
    private String fieldsOfAction;
    private String advertingFinancialInspektion;
    private Date erstelldatum;
    
    //Location:
    private String strasse;
    private String stadt;
    private String postleitzahl;
    
    // Contact:
    private String telefon;
    private String telefax;
    private String email;
    
    // Paypal-ID:
    private String paypalid;
    
    @OneToOne(mappedBy = "charity", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private Charity_Organisation ch_orga;
    
//    @OneToMany(mappedBy = "charity", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
//    private List<Location> location = new ArrayList<>() ;
    
    @OneToMany(mappedBy = "charity", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<Kategorie> kategorie = new ArrayList<>();
    
    @OneToMany(mappedBy = "charity", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<PlaceOfAction> placeofaction = new ArrayList<>();
    
    @OneToMany(mappedBy = "charity", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<Einkommen> einkommen = new ArrayList<>();
    
    @OneToMany(mappedBy = "charity", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<Bankkonto> bankkonto = new ArrayList<>();
    
    @OneToMany(mappedBy = "charity", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<Quittung> quittung = new ArrayList<>();
    
    @OneToOne(mappedBy = "charity", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private Benutzer benutzer;
    
    
    public Charity(String name, String logoPath, int foundingYear, String homepage, String worldview, String fieldsOfAction, String advertingFinancialInspektion) {        
        this.name = name;
        this.logoPath = logoPath;
        this.foundingYear = foundingYear;
        this.homepage = homepage;
        this.worldview = worldview;
        this.fieldsOfAction = fieldsOfAction;
        this.advertingFinancialInspektion = advertingFinancialInspektion;
    }

    public Charity() {
    }  
    

    public Long getIdCharity() {
        return idCharity;
    }

    public void setIdCharity(Long idCharity) {
        this.idCharity = idCharity;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getWorldview() {
        return worldview;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getFieldsOfAction() {
        return fieldsOfAction;
    }

    public String getAdvertingFinancialInspektion() {
        return advertingFinancialInspektion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public void setFoundingYear(int foundingYear) {
        this.foundingYear = foundingYear;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setWorldview(String worldview) {
        this.worldview = worldview;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setFieldsOfAction(String fieldsOfAction) {
        this.fieldsOfAction = fieldsOfAction;
    }

    public void setAdvertingFinancialInspektion(String advertingFinancialInspektion) {
        this.advertingFinancialInspektion = advertingFinancialInspektion;
    }    

    public Charity_Organisation getCh_orga() {
        return ch_orga;
    }

    public void setCh_orga(Charity_Organisation ch_orga) {
        this.ch_orga = ch_orga;
    }

    public List<Kategorie> getKategorie() {
        return kategorie;
    }

    public void setKategorie(List<Kategorie> kategorie) {
        this.kategorie = kategorie;
    }
    
//    public void addKategorie(Kategorie kategorie) {
//        this.kategorie.add(kategorie);
//    }

    public List<PlaceOfAction> getPlaceofaction() {
        return placeofaction;
    }

    public void setPlaceofaction(List<PlaceOfAction> placeofaction) {
        this.placeofaction = placeofaction;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public List<Einkommen> getEinkommen() {
        return einkommen;
    }

    public void setEinkommen(List<Einkommen> einkommen) {
        this.einkommen = einkommen;
    }

    public List<Bankkonto> getBankkonto() {
        return bankkonto;
    }

    public void setBankkonto(List<Bankkonto> bankkonto) {
        this.bankkonto = bankkonto;
    }

    public List<Quittung> getQuittung() {
        return quittung;
    }

    public void setQuittung(List<Quittung> quittung) {
        this.quittung = quittung;
    }

    public Date getErstelldatum() {
        return erstelldatum;
    }

    public void setErstelldatum(Date erstelldatum) {
        this.erstelldatum = erstelldatum;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelefax() {
        return telefax;
    }

    public void setTelefax(String telefax) {
        this.telefax = telefax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaypalid() {
        return paypalid;
    }

    public void setPaypalid(String paypalid) {
        this.paypalid = paypalid;
    }
    

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCharity != null ? idCharity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Charity)) {
            return false;
        }
        Charity other = (Charity) object;
        if ((this.idCharity == null && other.idCharity != null) || (this.idCharity != null && !this.idCharity.equals(other.idCharity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "spendensammler.jpa.entities.Charity[ id=" + idCharity + " ]";
    }
    
}