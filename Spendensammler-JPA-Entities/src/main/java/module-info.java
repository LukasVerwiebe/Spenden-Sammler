module Entities {
 
    requires java.desktop;
    requires java.base;
   
    requires java.instrument;
    requires java.sql;
    requires jakarta.jakartaee.api;

    opens spendensammler.jpa.dao;
    opens spendensammler.jpa.dao.exception;
    opens spendensammler.jpa.entities;
    exports spendensammler.jpa.dao;
    exports spendensammler.jpa.dao.exception;
    exports spendensammler.jpa.entities;
    
    
}

