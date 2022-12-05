
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Einkommen;
import spendensammler.jpa.entities.Quittung;

/**
 *
 * @author Lukas
 */
@Named(value="orgaDatenModel")
@SessionScoped
public class OrgaDatenModel implements Serializable {
    
    @Inject
    private UserController userController; 
    
    @Inject
    private CharityController charityController; 
    
    @Inject
    private SessionData sessionData;
    
    private BarChartModel barModel;
    private BarChartModel barModel2;
    private LineChartModel lineModel;
    private BarChartModel barModel3;
    private LineChartModel lineModel2;
    private LineChartModel lineModel3;
    
    // Aktuelle Jahr berechnen:
    Date dt = new Date();
    int jahraktuell = dt.getYear();
    int aktuellesJahr = jahraktuell + 1900; 
    
    private int jahr = aktuellesJahr;
    private int jahrVon = aktuellesJahr -4;
    private int jahrBis = aktuellesJahr;
    private int jahrVon2 = aktuellesJahr -4;
    private int jahrBis2 = aktuellesJahr;
    private int jahr3 = aktuellesJahr;
    private int jahrVon3 = aktuellesJahr -4;
    private int jahrBis3 = aktuellesJahr;
    private int jahr4 = aktuellesJahr;
    private int jahrVon4 = aktuellesJahr -4;
    private int jahrBis4 = aktuellesJahr;
    
        
    @PostConstruct
    public void init() {
        createBarModel();
        createBarModel2(); 
        createLineModel();
        createBarModel3();
        createLineModel2();
        createLineModel3();
    }
    
    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Spendensumme pro Monat");
        
        // Zwischenspeicher:
        List<Quittung> quittungen = new ArrayList<>();
        Calendar cal = Calendar.getInstance();        
        int monat;
        int zähler = 12;

        List<Number> values = new ArrayList<>();
        quittungen = userController.orgaDiagrammQuittungen(jahr, sessionData.getBenutzer().getCharity().getIdCharity());
        for(int i = 0; i < zähler; i++) {
            double betrag = 0.0;
            for(int j = 0; j < quittungen.size(); j++) {
                cal.setTime(quittungen.get(j).getErstelldatum());
                monat = cal.get(Calendar.MONTH);
                if(monat == i) {
                    betrag = betrag + quittungen.get(j).getSummeQuittung();
                }
            }
            values.add(betrag);
        }
        
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);
        
        
        // Hierfür Checkboxen:
        List<String> labels = new ArrayList<>();
        labels.add("Januar");
        labels.add("Februar");
        labels.add("März");
        labels.add("April");
        labels.add("Mai");
        labels.add("Juni");
        labels.add("Juli");
        labels.add("August");
        labels.add("September");
        labels.add("Oktober");
        labels.add("November");
        labels.add("Dezember");
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);        
        //linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        //title.setText("Balken-Diagramm");
        //options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }
    
    public void createBarModel2() {
        barModel2 = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Spendensumme pro Jahr");
                
        // Differenz:
        int zähler = jahrBis - jahrVon;
        int jahrspeicher = jahrVon;
        int jahrwert = jahrVon;
        
        // Zwischenspeicher:
        List<Einkommen> einkommen = new ArrayList<>();
        
        List<Number> values = new ArrayList<>();
        einkommen = charityController.orgaEinkommen(sessionData.getBenutzer().getCharity().getIdCharity());
        for(int i = 0; i < zähler+1; i++) {
            double wert = 0.0;
            for(int j = 0; j < einkommen.size(); j++) {
                if(einkommen.get(j).getJahrEinkommen() == jahrwert) {
                    wert = wert + einkommen.get(j).getGeldEinkommen();
                }
            }
            values.add(wert);
            jahrwert = jahrwert + 1;
        }    
        
        
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);
        
        
        //Beschriftung:
        List<String> labels = new ArrayList<>();
        
        labels.add(Integer.toString(jahrVon)); 
        for(int i = 0; i < zähler; i++) {
            labels.add(Integer.toString(jahrspeicher + 1));
            jahrspeicher = jahrspeicher + 1;
        }       
        
        data.setLabels(labels);
        barModel2.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);        
        //linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
//        title.setText("Balken-Diagramm");
//        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel2.setOptions(options);
    }
    
    public void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        // Differenz:
        int zähler = jahrBis2 - jahrVon2;
        int jahrspeicher = jahrVon2;
        int jahrwert = jahrVon2;
        
        // Zwischenspeicher:
        List<Einkommen> einkommen = new ArrayList<>();
        
        LineChartDataSet dataSet = new LineChartDataSet();     
        List<Object> values = new ArrayList<>();
                
        einkommen = charityController.orgaEinkommen(sessionData.getBenutzer().getCharity().getIdCharity());
        for(int i = 0; i < zähler+1; i++) {
            double wert = 0.0;
            for(int j = 0; j < einkommen.size(); j++) {
                if(einkommen.get(j).getJahrEinkommen() == jahrwert) {
                    wert = wert + einkommen.get(j).getGeldEinkommen();
                }
            }
            values.add(wert);
            jahrwert = jahrwert + 1;
        }   
        
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Einkommen pro jahr");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        //dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        labels.add(Integer.toString(jahrVon2)); 
        for(int i = 0; i < zähler; i++) {
            labels.add(Integer.toString(jahrspeicher + 1));
            jahrspeicher = jahrspeicher + 1;
        }
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
//        title.setText("Linien-Diagramm");
//        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
    }
    
    public void createBarModel3() {
        barModel3 = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Spendenanzahl pro Monat");
        
        // Zwischenspeicher:
        List<Quittung> quittung = new ArrayList<>();
        Calendar cal = Calendar.getInstance();        
        int monat;
        int jahr;
        int zähler = 12;
        
        List<Number> values = new ArrayList<>();
        quittung = charityController.getOrgaQuittung(sessionData.getBenutzer().getCharity().getIdCharity());
        
        for(int i = 0; i < zähler; i++) {
            int wert = 0;
            for(int j = 0; j < quittung.size(); j++) {
                cal.setTime(quittung.get(j).getErstelldatum());
                monat = cal.get(Calendar.MONTH);
                jahr = cal.get(Calendar.YEAR);
                if(monat == i && jahr == this.jahr3) {
                    wert = wert + 1;
                }
            }
            values.add(wert);
        } 
        
        
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);
        
        
        //Beschriftung:
        List<String> labels = new ArrayList<>();
        labels.add("Januar");
        labels.add("Februar");
        labels.add("März");
        labels.add("April");
        labels.add("Mai");
        labels.add("Juni");
        labels.add("Juli");
        labels.add("August");
        labels.add("September");
        labels.add("Oktober");
        labels.add("November");
        labels.add("Dezember");
        
        data.setLabels(labels);
        barModel3.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);        
        //linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
//        title.setText("Balken-Diagramm");
//        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel3.setOptions(options);
    }
    
    public void createLineModel2() {
        lineModel2 = new LineChartModel();
        ChartData data = new ChartData();

        // Differenz:
        int zähler = jahrBis3 - jahrVon3;
        int jahrspeicher = jahrVon3;
        int jahrwert = jahrVon3;
        
        // Zwischenspeicher:
        List<Quittung> quittung = new ArrayList<>();
        Calendar cal = Calendar.getInstance();        
        int monat;
        int jahr = jahrVon3;
        
        LineChartDataSet dataSet = new LineChartDataSet();     
        List<Object> values = new ArrayList<>();
                
        quittung = charityController.getOrgaQuittung(sessionData.getBenutzer().getCharity().getIdCharity());
        
        for(int i = 0; i < zähler+1; i++) {
            int wert = 0;
            for(int j = 0; j < quittung.size(); j++) {
                cal.setTime(quittung.get(j).getErstelldatum());                
                jahr = cal.get(Calendar.YEAR);
                if(jahr == jahrwert) {
                    wert = wert + 1;
                }
            }
            values.add(wert);
            jahrwert = jahrwert + 1;
        }   
        
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Spendenanzahl pro Jahr");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        //dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        labels.add(Integer.toString(jahrVon3)); 
        for(int i = 0; i < zähler; i++) {
            labels.add(Integer.toString(jahrspeicher + 1));
            jahrspeicher = jahrspeicher + 1;
        }
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
//        title.setText("Linien-Diagramm");
//        options.setTitle(title);

        lineModel2.setOptions(options);
        lineModel2.setData(data);
    }
    
    public void createLineModel3() {
        lineModel3 = new LineChartModel();
        ChartData data = new ChartData();

        // Differenz:
        int zähler = jahrBis4 - jahrVon4;
        int jahrspeicher = jahrVon4;
        int jahrwert = jahrVon4;
        
        // Zwischenspeicher:
        List<Quittung> quittung = new ArrayList<>();
        Set<Long> benutzerid = new HashSet<>();              
        int jahresangabe = jahrBis4;
        int differnez = jahrBis4 - jahrVon4;
        int jahresberechnung = jahrVon4;      
        
        LineChartDataSet dataSet = new LineChartDataSet();     
        List<Object> values = new ArrayList<>();
                
        quittung = charityController.getOrgaQuittung(sessionData.getBenutzer().getCharity().getIdCharity());
        for(int i = 0; i < differnez+1; i++) {
            for(int j = 0; j < quittung.size(); j++) {
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(quittung.get(j).getErstelldatum());  
                int jahresangabe2 = cal2.get(Calendar.YEAR);
                if(jahresberechnung == jahresangabe2) {
                   benutzerid.add(quittung.get(j).getBenutzer().getIdBenutzer()); 
                }                
            }      
            values.add(benutzerid.size());
            jahresberechnung = jahresberechnung + 1;
            benutzerid.clear();
        }
        
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Anzahl der Spender pro Jahr");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        //dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        labels.add(Integer.toString(jahrVon4)); 
        for(int i = 0; i < zähler; i++) {
            labels.add(Integer.toString(jahrspeicher + 1));
            jahrspeicher = jahrspeicher + 1;
        }
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
//        title.setText("Linien-Diagramm");
//        options.setTitle(title);

        lineModel3.setOptions(options);
        lineModel3.setData(data);
    }
    
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String updateDiagramm() {
        if(this.jahr > this.aktuellesJahr) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr darf nicht in der Zukunft liegen!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else if(this.jahr < 2015) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr liegt zu weit in der Vergangenheit!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;        
        } else {
            createBarModel();
            return "/Orga/OrgaDaten.xhtml?faces-redirect=true";
        }
    }
    
    public String updateDiagramm2() {
        if(this.jahrVon > this.aktuellesJahr || this.jahrBis > this.aktuellesJahr) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr darf nicht in der Zukunft liegen!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else if(this.jahrVon < 2015 || this.jahrBis < 2015) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr liegt zu weit in der Vergangenheit!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;        
        } else if(this.jahrVon > this.jahrBis) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Wert für 'Von:' darf nicht kleiner sein als der Wert 'Bis:'");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else {
            createBarModel2();
            return "/Orga/OrgaDaten.xhtml?faces-redirect=true";
        }
        
        
    }
    
    public String updateDiagramm3() {
        if(this.jahrVon2 > this.aktuellesJahr || this.jahrBis2 > this.aktuellesJahr) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr darf nicht in der Zukunft liegen!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else if(this.jahrVon2 < 2015 || this.jahrBis2 < 2015) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr liegt zu weit in der Vergangenheit!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;        
        } else if(this.jahrVon2 > this.jahrBis2) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Wert für 'Von:' darf nicht kleiner sein als der Wert 'Bis:'");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else {
           createLineModel();
            return "/Orga/OrgaDaten.xhtml?faces-redirect=true"; 
        }
        
    }
    
    public String updateDiagramm4() {
        if(this.jahr3 > this.aktuellesJahr) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr darf nicht in der Zukunft liegen!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else if(this.jahr3 < 2015) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr liegt zu weit in der Vergangenheit!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;        
        } else {
            createBarModel3();
            return "/Orga/OrgaDaten.xhtml?faces-redirect=true";
        }        
    }
    
    public String updateDiagramm5() {
        if(this.jahrVon3 > this.aktuellesJahr || this.jahrBis3 > this.aktuellesJahr) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr darf nicht in der Zukunft liegen!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else if(this.jahrVon3 < 2015 || this.jahrBis3 < 2015) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr liegt zu weit in der Vergangenheit!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;        
        } else if(this.jahrVon3 > this.jahrBis3) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Wert für 'Von:' darf nicht kleiner sein als der Wert 'Bis:'");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else {
            createLineModel2();
            return "/Orga/OrgaDaten.xhtml?faces-redirect=true";
        }
    }
    
    public String updateDiagramm6() {
        if(this.jahrVon4 > this.aktuellesJahr || this.jahrBis4 > this.aktuellesJahr) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr darf nicht in der Zukunft liegen!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else if(this.jahrVon4 < 2015 || this.jahrBis4 < 2015) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Jahr liegt zu weit in der Vergangenheit!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;        
        } else if(this.jahrVon4 > this.jahrBis4) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Wert für 'Von:' darf nicht kleiner sein als der Wert 'Bis:'");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else {
           createLineModel3();
           return "/Orga/OrgaDaten.xhtml?faces-redirect=true"; 
        }
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

    public int getJahrVon() {
        return jahrVon;
    }

    public void setJahrVon(int jahrVon) {
        this.jahrVon = jahrVon;
    }

    public int getJahrBis() {
        return jahrBis;
    }

    public void setJahrBis(int jahrBis) {
        this.jahrBis = jahrBis;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public int getJahrVon2() {
        return jahrVon2;
    }

    public void setJahrVon2(int jahrVon2) {
        this.jahrVon2 = jahrVon2;
    }

    public int getJahrBis2() {
        return jahrBis2;
    }

    public void setJahrBis2(int jahrBis2) {
        this.jahrBis2 = jahrBis2;
    }

    public BarChartModel getBarModel3() {
        return barModel3;
    }

    public void setBarModel3(BarChartModel barModel3) {
        this.barModel3 = barModel3;
    }

    public int getJahr3() {
        return jahr3;
    }

    public void setJahr3(int jahr3) {
        this.jahr3 = jahr3;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        this.lineModel2 = lineModel2;
    }

    public int getJahrVon3() {
        return jahrVon3;
    }

    public void setJahrVon3(int jahrVon3) {
        this.jahrVon3 = jahrVon3;
    }

    public int getJahrBis3() {
        return jahrBis3;
    }

    public void setJahrBis3(int jahrBis3) {
        this.jahrBis3 = jahrBis3;
    }

    public LineChartModel getLineModel3() {
        return lineModel3;
    }

    public void setLineModel3(LineChartModel lineModel3) {
        this.lineModel3 = lineModel3;
    }

    public int getJahr4() {
        return jahr4;
    }

    public void setJahr4(int jahr4) {
        this.jahr4 = jahr4;
    }

    public int getJahrVon4() {
        return jahrVon4;
    }

    public void setJahrVon4(int jahrVon4) {
        this.jahrVon4 = jahrVon4;
    }

    public int getJahrBis4() {
        return jahrBis4;
    }

    public void setJahrBis4(int jahrBis4) {
        this.jahrBis4 = jahrBis4;
    }
    
    
    
}
