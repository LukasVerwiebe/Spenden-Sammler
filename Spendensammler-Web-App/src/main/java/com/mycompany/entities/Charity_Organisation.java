
package spendensammler.jpa.entities;

import jakarta.persistence.CascadeType;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 *
 * @author lu80238
 */
@Entity
public class Charity_Organisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCharityOrg;
    
    private Long membercount;
    private Long employeecount;
    private Long volounteersCount;
    private String managmentbody;
    private int orgaYear;
    
    @OneToOne
    private Charity charity;

    public Charity_Organisation(Long membercount, Long employeecount, Long volounteersCount, String managmentbody, int year, Charity charity) {
        this.membercount = membercount;
        this.employeecount = employeecount;
        this.volounteersCount = volounteersCount;
        this.managmentbody = managmentbody;
        this.orgaYear = year;
    }

    public Charity_Organisation() {
    }

    public Long getIdCharityOrg() {
        return idCharityOrg;
    }

    public void setIdCharityOrg(Long idCharityOrg) {
        this.idCharityOrg = idCharityOrg;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMembercount() {
        return membercount;
    }

    public Long getEmployeecount() {
        return employeecount;
    }

    public Long getVolounteersCount() {
        return volounteersCount;
    }

    public String getManagmentbody() {
        return managmentbody;
    }

    public int getOrgaYear() {
        return orgaYear;
    }

    public Charity getCharity() {
        return charity;
    }    

    public void setMembercount(Long membercount) {
        this.membercount = membercount;
    }

    public void setEmployeecount(Long employeecount) {
        this.employeecount = employeecount;
    }

    public void setVolounteersCount(Long volounteersCount) {
        this.volounteersCount = volounteersCount;
    }

    public void setManagmentbody(String managmentbody) {
        this.managmentbody = managmentbody;
    }   

    public void setOrgaYear(int orgaYear) {
        this.orgaYear = orgaYear;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCharityOrg != null ? idCharityOrg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Charity_Organisation)) {
            return false;
        }
        Charity_Organisation other = (Charity_Organisation) object;
        if ((this.idCharityOrg == null && other.idCharityOrg != null) || (this.idCharityOrg != null && !this.idCharityOrg.equals(other.idCharityOrg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "spendensammler.jpa.entities.Charity_Organisation[ id=" + idCharityOrg + " ]";
    }
    
}