package spendensammler.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
@Entity
public class Charitydao_locationdao implements Serializable {
     private static final long serialVersionUID = 1L;
    @Id
    private Long charitydao_id;
    private Long locations_id;

    public Charitydao_locationdao() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCharitydao_id() {
        return charitydao_id;
    }

    public Long getLocations_id() {
        return locations_id;
    }

    public void setCharitydao_id(Long charitydao_id) {
        this.charitydao_id = charitydao_id;
    }

    public void setLocations_id(Long locations_id) {
        this.locations_id = locations_id;
    }
    

    

}

