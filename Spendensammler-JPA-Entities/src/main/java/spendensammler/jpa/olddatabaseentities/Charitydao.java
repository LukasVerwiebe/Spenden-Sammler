
package spendensammler.jpa.entities;

import jakarta.persistence.CascadeType;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.sql.Date;

/**
 *
 * @author lu80238
 */
@Entity
public class Charitydao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String advertiesementfinancialinspection;
    private boolean deleted;
    private String fieldsofaction;
    private Date foundingyear;
    private String homepage;
    private String importsource;
    private String logopath;
    private String name;
    private String wasupdated;
    private String worldview;
    private String charityorganization_id;

    
    public Charitydao(){
    }
    public Charitydao(String name, String logoPath, int foundingYear, String homepage, String worldview, String fieldsOfAction, String advertingFinancialInspektion, boolean deleted) {        
        this.name = name;
        this.logopath = logoPath;
        this.foundingyear = Date.valueOf(Long.toString(foundingYear));
        this.homepage = homepage;
        this.worldview = worldview;
        this.fieldsofaction = fieldsOfAction;
        this.advertiesementfinancialinspection = advertingFinancialInspektion;
        this.deleted = deleted;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public String getAdvertiesementfinancialinspection() {
        return advertiesementfinancialinspection;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getFieldsofaction() {
        return fieldsofaction;
    }

    public Date getFoundingyear() {
        return foundingyear;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImportsource() {
        return importsource;
    }

    public String getLogopath() {
        return logopath;
    }

    public String getName() {
        return name;
    }

    public String getWasupdated() {
        return wasupdated;
    }

    public String getWorldview() {
        return worldview;
    }

    public String getCharityorganization_id() {
        return charityorganization_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdvertiesementfinancialinspection(String advertiesementfinancialinspection) {
        this.advertiesementfinancialinspection = advertiesementfinancialinspection;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setFieldsofaction(String fieldsofaction) {
        this.fieldsofaction = fieldsofaction;
    }

    public void setFoundingyear(Date foundingyear) {
        this.foundingyear = foundingyear;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setImportsource(String importsource) {
        this.importsource = importsource;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWasupdated(String wasupdated) {
        this.wasupdated = wasupdated;
    }

    public void setWorldview(String worldview) {
        this.worldview = worldview;
    }

    public void setCharityorganization_id(String charityorganization_id) {
        this.charityorganization_id = charityorganization_id;
    }
    

 
}