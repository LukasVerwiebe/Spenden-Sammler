
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
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
import spendensammler.jpa.entities.Einkommen;
import spendensammler.jpa.entities.Quittung;

/**
 *
 * @author lu80238
 */
@Named(value="userDatenModel")
@SessionScoped
public class UserDatenModel  implements Serializable{
    
    @Inject
    private UserController userController; 
    
    @Inject
    private SessionData sessionData;
    
    @Inject
    private CharityController charityController; 
    
    private BarChartModel barModel;
    private LineChartModel lineModel;
    
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
        createLineModel();
        
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
        quittungen = userController.getUserQuittung(this.jahr, sessionData.getBenutzer().getIdBenutzer());
        
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

//        Title title = new Title();
//        title.setDisplay(true);
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

        barModel.setOptions(options);
    }
    
    public void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        // Differenz:
        int zähler = jahrBis4 - jahrVon4;
        int jahrspeicher = jahrVon4;
        int jahrwert = jahrVon4;
        
        // Zwischenspeicher:
        List<Quittung> quittungen = new ArrayList<>();
        Set<Long> benutzerid = new HashSet<>();              
        int jahresangabe = jahrBis4;
        int differnez = jahrBis4 - jahrVon4;
        int jahresberechnung = jahrVon4;      
        
        LineChartDataSet dataSet = new LineChartDataSet();     
        List<Object> values = new ArrayList<>();
                
        quittungen = userController.getUQuittung(sessionData.getBenutzer().getIdBenutzer());
        for(int i = 0; i < differnez+1; i++) {
            double wert = 0.0;
            for(int j = 0; j < quittungen.size(); j++) {
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(quittungen.get(j).getErstelldatum());  
                int jahresangabe2 = cal2.get(Calendar.YEAR);
                if(jahresberechnung == jahresangabe2) {
                   benutzerid.add(quittungen.get(j).getBenutzer().getIdBenutzer());
                   wert = wert + quittungen.get(j).getSummeQuittung();
                }                
            }      
            values.add(wert);
            jahresberechnung = jahresberechnung + 1;
            benutzerid.clear();
        }

 
        
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Spendensumme pro Jahr");
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
        //Title title = new Title();
       // title.setDisplay(true);
        //title.setText("Linien-Diagramm");
        //options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
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
            return "/User/UserDaten.xhtml?faces-redirect=true";
        }
    }
    
    public String updateDiagramm2() {
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
            createLineModel();
            return "/User/UserDaten.xhtml?faces-redirect=true";
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

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
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

    public int getJahr3() {
        return jahr3;
    }

    public void setJahr3(int jahr3) {
        this.jahr3 = jahr3;
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
