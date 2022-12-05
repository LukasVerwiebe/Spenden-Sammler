
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
public class Charityorganizationdao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private int fulltimestaffcount;
    private String leader;
    private String managementbody;
    private int membercount;
    private boolean membercountinapplicable;
    private int unsalariedstaffcount;
    private String form;

    public Charityorganizationdao(Long membercount, Long employeecount, Long volounteersCount, String managmentbody, int year) {
        this.membercount = membercount.intValue();
        this.fulltimestaffcount = employeecount.intValue();
        this.unsalariedstaffcount = volounteersCount.intValue();
        this.form = managmentbody;
        //this.charity = charity;
    }

    public Charityorganizationdao() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public int getFulltimestaffcount() {
        return fulltimestaffcount;
    }

    public String getLeader() {
        return leader;
    }

    public String getManagementbody() {
        return managementbody;
    }

    public int getMembercount() {
        return membercount;
    }

    public boolean getMembercountinapplicable() {
        return membercountinapplicable;
    }

    public int getUnsalariedstaffcount() {
        return unsalariedstaffcount;
    }

    public String getForm() {
        return form;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setFulltimestaffcount(int fulltimestaffcount) {
        this.fulltimestaffcount = fulltimestaffcount;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public void setManagementbody(String managementbody) {
        this.managementbody = managementbody;
    }

    public void setMembercount(int membercount) {
        this.membercount = membercount;
    }

    public void setMembercountinapplicable(boolean membercountinapplicable) {
        this.membercountinapplicable = membercountinapplicable;
    }

    public void setUnsalariedstaffcount(int unsalariedstaffcount) {
        this.unsalariedstaffcount = unsalariedstaffcount;
    }

    public void setForm(String form) {
        this.form = form;
    }

    

   
    
    

}