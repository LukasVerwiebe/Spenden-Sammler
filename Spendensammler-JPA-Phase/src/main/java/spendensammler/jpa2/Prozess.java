package spendensammler.jpa2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spendensammler.jpa.dao.Datenzugriffsobjekt;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Charity_Organisation;
import spendensammler.jpa.entities.Charitydao;
import spendensammler.jpa.entities.Charitydao_locationdao;
import spendensammler.jpa.entities.Charityorganizationdao;
import spendensammler.jpa.entities.Dummy;
import spendensammler.jpa.entities.Location;
import spendensammler.jpa.entities.Locationdao;

public class Prozess {

    private static Datenzugriffsobjekt dao = new Datenzugriffsobjekt("Spendensammler-JPA-PhasePU");
    private static Datenzugriffsobjekt daoAndere = new Datenzugriffsobjekt("Spendensammler-JPA-PhasePU2019");

    public Prozess() {

        this.dao = new Datenzugriffsobjekt("Spendensammler-JPA-PhasePU");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Prozess test = new Prozess();
        
         String pi1 = "AibrweDld2UN0Fl0GR63kT5b78USARiwDuR37QBEz8tWag3BAaJKa3Bg";
         String pi2 = "AY2b2GaGtkAbvhOc8GpLmku5tiPsAWrJjQYyHyveC1f7g1ZzvHpZ4lW6";
         String pi3 = "AibrweDld2UN0Fl0GR63kT5b78USARiwDuR37QBEz8tWag3BAaJKa3Bg";
         int idzahl = 0;
         String currentid="";
         String[] zahlarray = new String[]{pi1,pi2,pi3};
         Charity currentchartiy;
        
         List<Charity> charityall = dao.findAll();
//System.out.println("hello world");
//         System.out.println(zahlarray[0]);
//         System.out.println(zahlarray[1]);
//         System.out.println(zahlarray[2]);
        for (int i = 0; i < charityall.size(); i++) {
        charityall.get(i).setPaypalid(zahlarray[idzahl]);
        currentchartiy = charityall.get(i);
        idzahl = idzahl+1;
        if(idzahl ==3){
        idzahl =0;
        
        }
        dao.updateCharity(currentchartiy.getIdCharity(),currentid);
        }
//        System.out.println(daoAndere.findeLocationInformationencharity_location().size());
//       test.newCharity("Klaus",  "we", 10, 
//                "asf", "ase", "ae", "eas",
//            5L,5L,5L,"awse", 
//            5, true, "Schweden", "ase", "ase");

        /**
         * transfer from 2 to 1
         */
       
//                        List<Charity> charityall = dao.findAll();
//        for (int i = 0; i < charityall.size(); i++) {
//        dao.removeCharity(charityall.get(i));
//        }
//        int intwert;
//        List<Charity> charitys;
//        List<Charitydao> charitydao = daoAndere.findeCharityInformationenAlldao();
//        Charitydao currentElement = charitydao.get(0);
//        HashMap<Long, Long> map = new HashMap<>();
//        List<Charitydao_locationdao> connection = daoAndere.findeLocationInformationencharity_location();
//        Charitydao_locationdao conrete = connection.get(0);
//        for (int i = 0; i < connection.size(); i++) {
//            map.put(connection.get(i).getCharitydao_id(), connection.get(i).getLocations_id());
//        }
//        //charitydao.size()
//        for (int i = 0; i < charitydao.size(); i++) {
//            currentElement = charitydao.get(i);
//            intwert = Integer.parseInt(currentElement.getFoundingyear().toString().substring(0, 4));
//            Charity tobeinserted = new Charity(
//                    currentElement.getName(),
//                    currentElement.getLogopath(),
//                    intwert,
//                    currentElement.getHomepage(),
//                    currentElement.getWorldview(),
//                    currentElement.getFieldsofaction(),
//                    currentElement.getAdvertiesementfinancialinspection()
//            );
//            Charityorganizationdao tobeinsertedorga = daoAndere.findeOrgaInformationeniddao(Long.valueOf(currentElement.getCharityorganization_id()));
//            Charity_Organisation chOrg = new Charity_Organisation(
//                    Long.valueOf(tobeinsertedorga.getFulltimestaffcount()),
//                    Long.valueOf(tobeinsertedorga.getMembercount()),
//                    Long.valueOf(tobeinsertedorga.getUnsalariedstaffcount()),
//                    tobeinsertedorga.getManagementbody(),
//                    2000, tobeinserted);
//            
//            Long locationid = map.get(charitydao.get(i).getId());
//
//            Locationdao tobeinsertedlocationdao = daoAndere.findeLocationInformationeniddao(locationid);
////            System.out.println(tobeinsertedlocationdao.getCity());
////            System.out.println(tobeinsertedlocationdao.getStreet());
////            System.out.println(tobeinsertedlocationdao.getZipcode());
//            Location location = new Location(
//                    tobeinsertedlocationdao.getStreet(),
//                    tobeinsertedlocationdao.getCity(),
//                    tobeinsertedlocationdao.getZipcode()
//            );
//           
////            tobeinserted.addLocation(location);
////            location.setCharity(tobeinserted);
//            tobeinserted.setCh_orga(chOrg);
//            chOrg.setCharity(tobeinserted);
////            tobeinserted.getCh_orga().setIdCharityOrg( tobeinserted.);
//           
//            dao.createNewCharity(tobeinserted);
////            dao.updateCharityorgaid(tobeinserted.getIdCharity());
//            
//        }
        /**
         * remove all
         */

//                List<Charity> charityall = dao.findAll();
//        for (int i = 0; i < charityall.size(); i++) {
//        dao.removeCharity(charityall.get(i));
//        }
        
    }
    public void newCharity(String name, String bild, int year, String website, String worldview, String fieldsOfAction, String bericht,
            Long member, Long employee, Long volunteers, String management, 
            int orgayear
//            ,String street, 
//            String city, 
//            String zipcode
    ) {

        try {

            // Erst Charity anlegen:
            Charity charity = new Charity(name, bild, year, website, worldview, fieldsOfAction, bericht);
            // Dann Charity_Organisation anlegen:
            Charity_Organisation chOrg = new Charity_Organisation(member, employee, volunteers, management, orgayear, charity);

            // Setzen der Verknüpfung zwischen Charity und Charity_Organisation:
            charity.setCh_orga(chOrg);
            chOrg.setCharity(charity);

//            Location location = new Location(street, city, zipcode);
//            charity.addLocation(location);
//            location.setCharity(charity);

            dao.createNewCharity(charity);
           

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public  List<Charity> searchAll() {
        return dao.findAll();

    }

    public  List<Charity> searchName(String name) {
        return dao.findeCharityInformationenName(name);

    }

    public  List<Charity> searchID(Long id) {
        return dao.findeCharityInformationenId(id);

    }

    public void updateCharity(Long id, String name, String bild, int yearg, String website, String worldview, String fieldsOfAction,
            String bericht, Long member, Long employee, Long volunteers, String management, int orgayear, boolean solvenz) {
        dao.updateCharity(id, name, bild, yearg, website, worldview, fieldsOfAction, bericht, member, employee, volunteers, management, orgayear);
    }

    public List<Charity> searchGrüdungsjahr(int jahr) {
        return dao.findeCharityInformationenGrüdungsjahr(jahr);

    }

    public List<Charity> searchIDAndName(Long id, String name) {
        return dao.findeCharityInformationenIDAndName(id, name);

    }

    public List<Charity> searchNameAndGrüdungsjahr(String name, int jahr) {
        return dao.findeCharityInformationenNameAndGrüdungsjahr(name, jahr);

    }

    public List<Charity> searchIDAndGründungsjahr(Long id, int jahr) {
        return dao.findeCharityInformationenIDAndGründungsjahr(id, jahr);

    }

    public List<Charity> searchIDAndNameAndGründungsjahr(long id, String name, int jahr) {
        return dao.findeCharityInformationenIDAndNameAndGründungsjahr(id, name, jahr);

    }

    public void deletecharity(Charity charity) {
        try {
            
            dao.removeCharity(charity);
          
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void removeCharityWithId(Long id) {
        try {
            
            dao.removeCharityWithId(id);
          
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    



}
