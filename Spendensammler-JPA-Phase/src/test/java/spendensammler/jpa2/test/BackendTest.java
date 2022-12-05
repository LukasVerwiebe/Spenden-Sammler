//package spendensammler.jpa2.test;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.FixMethodOrder;
//import org.junit.runners.MethodSorters;
//import spendensammler.jpa.dao.Datenzugriffsobjekt;
//import spendensammler.jpa.entities.Charity;
//import spendensammler.jpa.entities.Charity_Organisation;
//import spendensammler.jpa2.Prozess;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class BackendTest {
//
//    Datenzugriffsobjekt dao;
//    Prozess prozess;
//    Charity charity1;
//    Charity charity2;
//    Charity charity3;
//    Charity charity4;
//    Charity_Organisation charityOrga1;
//    Charity_Organisation charityOrga2;
//    Charity_Organisation charityOrga3;
//    Charity_Organisation charityOrga4;
//
//    @Before
//    public void ainit() {
//        this.dao = new Datenzugriffsobjekt("Spendensammler-JPA-PhasePU");
//        this.prozess = new Prozess();
//
//        charity1 = new Charity("pauk1", "gaw11", 5,
//                "website1", "worldview1", "fieldsOfAction1",
//                "bericht1", true);
//        charityOrga1 = new Charity_Organisation(1L, 1L, 1L, "tq1", 0, charity1);
//        charity1.setCh_orga(charityOrga1);
//
//        charity2 = new Charity("pauk2", "gaw12", 6,
//                "website2", "worldview2", "fieldsOfAction2",
//                "bericht2", true);
//        charityOrga2 = new Charity_Organisation(2L, 2L, 2L, "tq12", 1, charity2);
//        charity2.setCh_orga(charityOrga2);
//
//        charity3 = new Charity("pauk3", "gaw13", 7,
//                "website3", "worldview3", "fieldsOfAction3",
//                "bericht3", true);
//        charityOrga3 = new Charity_Organisation(3L, 3L, 3L, "tq3", 3, charity3);
//        charity3.setCh_orga(charityOrga3);
//
//        charity4 = new Charity("pauk4", "gaw14", 8,
//                "website4", "worldview4", "fieldsOfAction4",
//                "bericht4", true);
//        charityOrga4 = new Charity_Organisation(4L, 4L, 4L, "tq14", 4, charity4);
//        charity4.setCh_orga(charityOrga4);
//
//        dao.createNewCharity(charity1);
//        dao.createNewCharity(charity2);
//
//    }
//
//    @Test
//   public void btestSearchName(){
//       assertEquals(dao.findeCharityInformationenName(charity1.getName()).get(0).getName(),"pauk1");
//   }
//   @Test
//   public void ctestSearchID(){
//       
//       assertEquals(dao.findeCharityInformationenId(charity1.getId()).get(0).getId(),charity1.getId());
//   }
//   
//   @Test
//   public void dtestSearchGrundungsjahr(){
//     assertEquals(dao.findeCharityInformationenGrüdungsjahr(charity1.getFoundingYear()).get(0).getFoundingYear(),5);
//       
//   }
//   @Test
//   public void etestSearchIDAndName(){
//        assertEquals(dao.findeCharityInformationenId(charity1.getId()).get(0).getId(),charity1.getId());
//                    
//        assertEquals(dao.findeCharityInformationenName(charity1.getName()).get(0).getName(),"pauk1");
//        
//   }
//   
//   @Test
//   public void ftestsearchIDAndGründungsjahr(){
//       assertEquals(dao.findeCharityInformationenId(charity1.getId()).get(0).getId(),charity1.getId());
//        assertEquals(dao.findeCharityInformationenGrüdungsjahr(charity1.getFoundingYear()).get(0).getFoundingYear(),5);
//               }           
//               
//  @Test
//  public void gtestsearchIDAndNameAndGründungsjahr(){
//        assertEquals(dao.findeCharityInformationenId(charity1.getId()).get(0).getId(),charity1.getId());
//                    
//        assertEquals(dao.findeCharityInformationenName(charity1.getName()).get(0).getName(),"pauk1");
//        assertEquals(dao.findeCharityInformationenGrüdungsjahr(charity1.getFoundingYear()).get(0).getFoundingYear(),5);
//  }
//    @Test
//    public void htestCreateCharity() {
//
//        dao.createNewCharity(charity3);
//
//        Charity charityAngelegt = dao.findeCharityInformationenId(charity3.getId()).get(0);
//        assertEquals(charity3.getId(), charityAngelegt.getId());
//        assertEquals(charity3.getAdvertingFinancialInspektion(), charityAngelegt.getAdvertingFinancialInspektion());
//        assertEquals(charity3.getFieldsOfAction(), charityAngelegt.getFieldsOfAction());
//        assertEquals(charity3.getFoundingYear(), charityAngelegt.getFoundingYear());
//        assertEquals(charity3.getHomepage(), charityAngelegt.getHomepage());
//        assertEquals(charity3.getLogoPath(), charityAngelegt.getLogoPath());
//        assertEquals(charity3.getName(), charityAngelegt.getName());
//        assertEquals(charity3.getWorldview(), charityAngelegt.getWorldview());
//        assertEquals(charity3.isDeleted(), charityAngelegt.isDeleted());
//        assertEquals(charity3.getCh_orga().getCharity(), charityAngelegt.getCh_orga().getCharity());
//        assertEquals(charity3.getCh_orga().getEmployeecount(), charityAngelegt.getCh_orga().getEmployeecount());
//        assertEquals(charity3.getCh_orga().getManagmentbody(), charityAngelegt.getCh_orga().getManagmentbody());
//        assertEquals(charity3.getCh_orga().getMembercount(), charityAngelegt.getCh_orga().getMembercount());
//        assertEquals(charity3.getCh_orga().getVolounteersCount(), charityAngelegt.getCh_orga().getVolounteersCount());
//        assertEquals(charity3.getCh_orga().getYear(), charityAngelegt.getCh_orga().getYear());
//
//    }
//
//    @Test
//    public void itestCreateCharity2() {
//        dao.createNewCharity(charity4);
//        Charity charityAngelegt2 = dao.findeCharityInformationenId(charity4.getId()).get(0);
//        assertEquals(charity4.getId(), charityAngelegt2.getId());
//        assertEquals(charity4.getAdvertingFinancialInspektion(), charityAngelegt2.getAdvertingFinancialInspektion());
//        assertEquals(charity4.getFieldsOfAction(), charityAngelegt2.getFieldsOfAction());
//        assertEquals(charity4.getFoundingYear(), charityAngelegt2.getFoundingYear());
//        assertEquals(charity4.getHomepage(), charityAngelegt2.getHomepage());
//        assertEquals(charity4.getLogoPath(), charityAngelegt2.getLogoPath());
//        assertEquals(charity4.getName(), charityAngelegt2.getName());
//        assertEquals(charity4.getWorldview(), charityAngelegt2.getWorldview());
//        assertEquals(charity4.isDeleted(), charityAngelegt2.isDeleted());
//        assertEquals(charity4.getCh_orga().getCharity(), charityAngelegt2.getCh_orga().getCharity());
//        assertEquals(charity4.getCh_orga().getEmployeecount(), charityAngelegt2.getCh_orga().getEmployeecount());
//        assertEquals(charity4.getCh_orga().getManagmentbody(), charityAngelegt2.getCh_orga().getManagmentbody());
//        assertEquals(charity4.getCh_orga().getMembercount(), charityAngelegt2.getCh_orga().getMembercount());
//        assertEquals(charity4.getCh_orga().getVolounteersCount(), charityAngelegt2.getCh_orga().getVolounteersCount());
//        assertEquals(charity4.getCh_orga().getYear(), charityAngelegt2.getCh_orga().getYear());
//    }
//
//    @Test
//    public void jestUpdateCharity() {
//
//        Charity charityursprung = new Charity(
//                charity1.getName(),
//                charity1.getLogoPath(),
//                charity1.getFoundingYear(),
//                charity1.getHomepage(),
//                charity1.getWorldview(),
//                charity1.getFieldsOfAction(),
//                charity1.getFieldsOfAction(),
//                true);
//        Charity_Organisation charityOrgaUrsprung = new Charity_Organisation(
//                charity1.getCh_orga().getMembercount(),
//                charity1.getCh_orga().getEmployeecount(),
//                charity1.getCh_orga().getVolounteersCount(),
//                charity1.getCh_orga().getManagmentbody(),
//                charity1.getCh_orga().getYear(),
//                charity1);
//        charityursprung.setCh_orga(charityOrgaUrsprung);
//        dao.updateCharity(charity1.getId(), "x", "x", 99, "x", "x", "x", "x", 99L, 99L, 99L, "x", 99, true);
//
//        Charity charitychanged = new Charity(
//                charity1.getName(),
//                charity1.getLogoPath(),
//                charity1.getFoundingYear(),
//                charity1.getHomepage(),
//                charity1.getWorldview(),
//                charity1.getFieldsOfAction(),
//                charity1.getFieldsOfAction(),
//                false);
//        Charity_Organisation charityOrgaChanged = new Charity_Organisation(
//                charity1.getCh_orga().getMembercount(),
//                charity1.getCh_orga().getEmployeecount(),
//                charity1.getCh_orga().getVolounteersCount(),
//                charity1.getCh_orga().getManagmentbody(),
//                charity1.getCh_orga().getYear(),
//                charity1);
//        charitychanged.setCh_orga(charityOrgaChanged);
//
//        assertEquals(charityursprung.getId(), charitychanged.getId());
//
//        assertNotEquals(charityursprung.getAdvertingFinancialInspektion(), charitychanged.getAdvertingFinancialInspektion());
//        assertNotEquals(charityursprung.getFieldsOfAction(), charitychanged.getFieldsOfAction());
//        assertNotEquals(charityursprung.getFoundingYear(), charitychanged.getFoundingYear());
//        assertNotEquals(charityursprung.getHomepage(), charitychanged.getHomepage());
//        assertNotEquals(charityursprung.getLogoPath(), charitychanged.getLogoPath());
//        assertNotEquals(charityursprung.getName(), charitychanged.getName());
//        assertNotEquals(charityursprung.getWorldview(), charitychanged.getWorldview());
//        assertNotEquals(charityursprung.isDeleted(), charitychanged.isDeleted());
//        assertEquals(charityursprung.getCh_orga().getCharity(), charitychanged.getCh_orga().getCharity());
//        assertNotEquals(charityursprung.getCh_orga().getEmployeecount(), charitychanged.getCh_orga().getEmployeecount());
//        assertNotEquals(charityursprung.getCh_orga().getManagmentbody(), charitychanged.getCh_orga().getManagmentbody());
//        assertNotEquals(charityursprung.getCh_orga().getMembercount(), charitychanged.getCh_orga().getMembercount());
//        assertNotEquals(charityursprung.getCh_orga().getVolounteersCount(), charitychanged.getCh_orga().getVolounteersCount());
//        assertNotEquals(charityursprung.getCh_orga().getYear(), charitychanged.getCh_orga().getYear());
//        
//        
//    }
//    
//    @Test
//    public void ktestUpdateCharity2(){
//     Charity charityursprung2 = new Charity(
//                charity2.getName(),
//                charity2.getLogoPath(),
//                charity2.getFoundingYear(),
//                charity2.getHomepage(),
//                charity2.getWorldview(),
//                charity2.getFieldsOfAction(),
//                charity2.getFieldsOfAction(),
//                true);
//        Charity_Organisation charityOrgaUrsprung2 = new Charity_Organisation(
//                charity2.getCh_orga().getMembercount(),
//                charity2.getCh_orga().getEmployeecount(),
//                charity2.getCh_orga().getVolounteersCount(),
//                charity2.getCh_orga().getManagmentbody(),
//                charity2.getCh_orga().getYear(),
//                charity2);
//        charityursprung2.setCh_orga(charityOrgaUrsprung2);
//        dao.updateCharity(charity2.getId(), "x", "x", 99, "x", "x", "x", "x", 99L, 99L, 99L, "x", 99, true);
//
//        Charity charitychanged2 = new Charity(
//                charity2.getName(),
//                charity2.getLogoPath(),
//                charity2.getFoundingYear(),
//                charity2.getHomepage(),
//                charity2.getWorldview(),
//                charity2.getFieldsOfAction(),
//                charity2.getFieldsOfAction(),
//                true);
//        Charity_Organisation charityOrgaChanged2 = new Charity_Organisation(
//                charity2.getCh_orga().getMembercount(),
//                charity2.getCh_orga().getEmployeecount(),
//                charity2.getCh_orga().getVolounteersCount(),
//                charity2.getCh_orga().getManagmentbody(),
//                charity2.getCh_orga().getYear(),
//                charity2);
//        charitychanged2.setCh_orga(charityOrgaChanged2);
//
//        assertEquals(charityursprung2.getId(), charitychanged2.getId());
//
//        assertNotEquals(charityursprung2.getAdvertingFinancialInspektion(), charitychanged2.getAdvertingFinancialInspektion());
//        assertNotEquals(charityursprung2.getFieldsOfAction(), charitychanged2.getFieldsOfAction());
//        assertNotEquals(charityursprung2.getFoundingYear(), charitychanged2.getFoundingYear());
//        assertNotEquals(charityursprung2.getHomepage(), charitychanged2.getHomepage());
//        assertNotEquals(charityursprung2.getLogoPath(), charitychanged2.getLogoPath());
//        assertNotEquals(charityursprung2.getName(), charitychanged2.getName());
//        assertNotEquals(charityursprung2.getWorldview(), charitychanged2.getWorldview());
//        assertEquals(charityursprung2.isDeleted(), charitychanged2.isDeleted());
//        assertEquals(charityursprung2.getCh_orga().getCharity(), charitychanged2.getCh_orga().getCharity());
//        assertNotEquals(charityursprung2.getCh_orga().getEmployeecount(), charitychanged2.getCh_orga().getEmployeecount());
//        assertNotEquals(charityursprung2.getCh_orga().getManagmentbody(), charitychanged2.getCh_orga().getManagmentbody());
//        assertNotEquals(charityursprung2.getCh_orga().getMembercount(), charitychanged2.getCh_orga().getMembercount());
//        assertNotEquals(charityursprung2.getCh_orga().getVolounteersCount(), charitychanged2.getCh_orga().getVolounteersCount());
//        assertNotEquals(charityursprung2.getCh_orga().getYear(), charitychanged2.getCh_orga().getYear());
//    }
//
//    @Test
//    public void ltestRemoveCharity() {
//       dao.removeCharity(charity1);
//       assertTrue(dao.findeCharityInformationenId(charity1.getId()).isEmpty());
//       dao.removeCharity(charity2);
//       assertTrue(dao.findeCharityInformationenId(charity2.getId()).isEmpty());
//       
//
//    }
//    
//    @Test
//    public void mremoveCharityWithId(){
//        dao.removeCharityWithId(charity3.getId());
//       assertTrue(dao.findeCharityInformationenId(charity3.getId()).isEmpty());
//       dao.removeCharityWithId(charity4.getId());
//       assertTrue(dao.findeCharityInformationenId(charity4.getId()).isEmpty());
//    
//    }
//    
//
//}
