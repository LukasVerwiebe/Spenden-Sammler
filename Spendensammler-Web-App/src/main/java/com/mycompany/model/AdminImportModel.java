
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import com.mycompany.model.SessionData;
import com.mycompany.services.CharityService;
import com.mycompany.services.UserService;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.year;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedProperty;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ColumnResizeEvent;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Charity;

@Named(value="adminImportModel")
@ViewScoped
public class AdminImportModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private UserController userController;
    
    @Inject
    private SessionData sessiondata;
    
    @Inject
    private CharityService charityService;
    
    @Inject
    private UserService userService;
    
    
    @PostConstruct
    public void init() {
    }
    
    private List<Benutzer> benutzer = new ArrayList<Benutzer>();
    private String[] vorname = {"Peter", "Michael", "Timo", "Lukas", "Tobias", "Sandra"};
    private String nachanme = "Mustermann";
    private String email = "benutzer";
    private String emailOrga = "orga";
    private String passwort = "12345678";
    private String[] strasse = {"Kölnerstrasse 1", "Münchnerstrasse 5", "Hamburgerstrasse 7", "Dresdenerstrasse 9", "Berlinerstrasse 3", "Bochumerstrasse 22"};
    private String[] stadt = {"Köln", "Berlin", "München", "Hamburg", "Dresden", "Bochum"};
    private int[] postleitzahl = {50667, 10115, 80639, 20097, 1099, 44797};
    
    //userService.newUser(this.vorname, this.nachname, this.email, this.passwort, this.strasse, this.stadt, "Benutzer");
    
    public void importBenutzer() {
        int num = 0;
        for(int j = 0; j < 100; j++) {
            if(num == 5) {
                num = 0;                        
            }            
            Benutzer benutzerNeu = new Benutzer(vorname[num], nachanme, email + j + "@w-hs.de", passwort, "Benutzer" ,true);
            benutzerNeu.setPostleitzahl(postleitzahl[num]);
            benutzer.add(benutzerNeu);
            userService.createNewUser(benutzer.get(j), stadt[num], strasse[num]);                
            num = num + 1;
            }        
    }
    


    
    
    private int[] orgaid = {17251,
17253,
17255,
17257,
17259,
17261,
17263,
17265,
17267,
17269,
17271,
17273,
17275,
17277,
17279,
17281,
17283,
17285,
17287,
17289,
17291,
17293,
17295,
17297,
17299,
17301,
17303,
17305,
17307,
17309,
17311,
17313,
17315,
17317,
17319,
17321,
17323,
17325,
17327,
17329,
17331,
17333,
17335,
17337,
17339,
17341,
17343,
17345,
17347,
17349,
17351,
17353,
17355,
17357,
17359,
17361,
17363,
17365,
17367,
17369,
17371,
17373,
17375,
17377,
17379,
17381,
17383,
17385,
17387,
17389,
17391,
17393,
17395,
17397,
17399,
17401,
17403,
17405,
17407,
17409,
17411,
17413,
17415,
17417,
17419,
17421,
17423,
17425,
17427,
17429,
17431,
17433,
17435,
17437,
17439,
17441,
17443,
17445,
17447,
17449,
17451,
17453,
17455,
17457,
17459,
17461,
17463,
17465,
17467,
17469,
17471,
17473,
17475,
17477,
17479,
17481,
17483,
17485,
17487,
17489,
17491,
17493,
17495,
17497,
17499,
17501,
17503,
17505,
17507,
17509,
17511,
17513,
17515,
17517,
17519,
17521,
17523,
17525,
17527,
17529,
17531,
17533,
17535,
17537,
17539,
17541,
17543,
17545,
17547,
17549,
17551,
17553,
17555,
17557,
17559,
17561,
17563,
17565,
17567,
17569,
17571,
17573,
17575,
17577,
17579,
17581,
17583,
17585,
17587,
17589,
17591,
17593,
17595,
17597,
17599,
17601,
17603,
17605,
17607,
17609,
17611,
17613,
17615,
17617,
17619,
17621,
17623,
17625,
17627,
17629,
17631,
17633,
17635,
17637,
17639,
17641,
17643,
17645,
17647,
17649,
17651,
17653,
17655,
17657,
17659,
17661,
17663,
17665,
17667,
17669,
17671,
17673,
17675,
17677,
17679,
17681,
17683,
17685,
17687,
17689,
17691,
17693,
17695,
17697,
17699,
17701,
17703};
    
    public void importBenutzerOrga() {
        int num = 0;
        for(int j = 0; j < orgaid.length; j++) {
            if(num == 5) {
                num = 0;                        
            }            
            Benutzer benutzerNeu = new Benutzer(vorname[num], nachanme, emailOrga + j + "@w-hs.de", passwort, "Organisation" ,false);
            benutzerNeu.setPostleitzahl(postleitzahl[num]);
            benutzer.add(benutzerNeu);
            
            List<Charity> charity = charityService.findCharity(orgaid[j]);
            charity.get(0).setBenutzer(benutzerNeu);
            benutzerNeu.setCharity(charity.get(0));
            userService.createNewUser(benutzer.get(j), stadt[num], strasse[num]);                
            num = num + 1;
            }        
    }
    
    private String[] kategorien = {"Ärzte", "Nothilfe", "Kinder-Nothilfe", "Tafel", "Tierheim"};
    
    public void kategorienImport() {
        int num = 0;
        for(int i = 0; i < orgaid.length; i++) {
            if(num == 4) {
                num = 0;                        
            }
            List<Charity> charity = charityService.findCharity(orgaid[i]);
            for(int j = 0; j < 4; j++) {
                if(num == 4) {
                    num = 0;                        
                }
                charityController.newKategorie(kategorien[num], charity.get(0));            
                num = num + 1; 
            }            
        }
    }
    
    private String[] land = {"Deutschland", "Ukraine", "Frankreich", "Uganda", "Gahna"};
    
    public void länderImport() {
        int num = 0;
        for(int i = 0; i < orgaid.length; i++) {
            if(num == 5) {
                num = 0;                        
            }
            List<Charity> charity = charityService.findCharity(orgaid[i]);
            for(int j = 0; j < 5; j++) {
                if(num == 5) {
                    num = 0;                        
                }
                charityController.newPlaceOfAction(land[num], charity.get(0));            
                num = num + 1; 
            }            
        }
    } 
    
    private int[] einnahmenJahr = {2018, 2019, 2020, 2021, 2022};
    private double[] einnahmen = {120000.0, 90000.0, 99000.0, 132000, 66000};
    
    public void EinnahmenImport() {
        int num = 0;
        for(int i = 0; i < orgaid.length; i++) {
            if(num == 5) {
                num = 0;                        
            }
            List<Charity> charity = charityService.findCharity(orgaid[i]);
            for(int j = 0; j < 5; j++) {
                if(num == 5) {
                    num = 0;                        
                }
                //charityController.newPlaceOfAction(land[num], charity.get(0));      
                charityController.createEinkommen(einnahmenJahr[num], einnahmen[num], charity.get(0));
                num = num + 1; 
            }            
        }
    }
    
    private int[] benutzerid = {2018,
2019,
2020,
2021,
2022,
2023,
2024,
2025,
2026,
2027,
2028,
2029,
2030,
2031,
2032,
2033,
2034,
2035,
2036,
2037,
2038,
2039,
2040,
2041,
2042,
2043,
2044,
2045,
2046,
2047,
2048,
2049,
2050,
2051,
2052,
2053,
2054,
2055,
2056,
2057,
2058,
2059,
2060,
2061,
2062,
2063,
2064,
2065,
2066,
2067,
2068,
2069,
2070,
2071,
2072,
2073,
2074,
2075,
2076,
2077,
2078,
2079,
2080,
2081,
2082,
2083,
2084,
2085,
2086,
2087,
2088,
2089,
2090,
2091,
2092,
2093,
2094,
2095,
2096,
2097,
2098,
2099,
2100,
2101,
2102,
2103,
2104,
2105,
2106,
2107,
2108,
2109,
2110,
2111,
2112,
2113,
2114,
2115,
2116,
2117};
    
    
    private double[] spenden = {100, 75, 82.2, 110, 55};
    // 12 mal 100 euro mal 100 benutzer = 120000
    // 12 mal 75 euro mal 100 benutzer = 90000
    // 12 mal 82.5 euro mal 100 benutzer = 99000
    // 12 mal 110 euro mal 100 benutzer = 132000
    // 12 mal 55 euro mal 100 benutzer = 66000
    
    //private double[] einnahmen = {120000.0, 90000.0, 99000.0, 132000, 66000};
    
    public void QuittungImport() {
        int num = 0;
        int jahr = 118;
        int monat = 0;
        Date datum = new Date(jahr, 0, 1);
        
        for(int i = 0; i < orgaid.length; i++) {
            num = 0;                      
            
            List<Charity> charity = charityService.findCharity(orgaid[i]);            
            for(int j = 0; j < benutzerid.length; j++) {
                num = 0;
                jahr = 118;
                List<Benutzer> benutzer = userService.findUser(benutzerid[j]);
                for(int a = 0; a < 60; a++) {
//                    if(num == ) {
//                        num = 0;                        
//                    }
                    switch (a) {
                        case 12:
                            num = num + 1;
                            jahr = 119;
                            monat = 0;
                            break;
                        case 24:
                            num = num + 1;
                            jahr = 120;
                            monat = 0;
                            break;
                        case 36:
                            num = num + 1;
                            jahr = 121;
                            monat = 0;
                            break;
                        case 48:
                            num = num + 1;
                            jahr = 122;
                            monat = 0;
                            break;                        
                        default:
                            break;
                    }
                    datum = new Date(jahr, monat, 1);
                    userController.newQuittungDate(spenden[num], kategorien[num], charity.get(0), benutzer.get(0), datum);
                    monat = monat + 1;
                     
                }
                
            }            
        }
    } 
    
    private String iban = "DE89370400440532013000";
    private String inhaber = "Westfälische Hochschule";
    
    public void bankkontoImport() {
        //int num = 0;
        for(int i = 0; i < orgaid.length; i++) {            
            List<Charity> charity = charityService.findCharity(orgaid[i]); 
            //userController.newBAnkkontoOrga(iban, inhaber);
            //charityController.newPlaceOfAction(charity.get(0));   
        }
    }
    
}