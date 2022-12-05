
package com.mycompany.model;

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
import java.util.List;
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
@Named(value="datenauswertungModel")
@RequestScoped
public class DatenauswertungModel implements Serializable {
    
    @Inject
    private UserController userController; 
    
    @Inject
    private SessionData sessionData;
    
    private BarChartModel barModel;
    private BarChartModel barModel2;
    private LineChartModel lineModel;
    private PieChartModel pieModel;
    
    // Aktuelle Jahr berechnen:
    Date dt = new Date();
    int jahraktuell = dt.getYear();
    int aktuellesJahr = jahraktuell + 1900; 
    
    private int jahr = aktuellesJahr;
    
    
    @PostConstruct
    public void init() {
        createBarModel();
        createBarModel2();
        createLineModel();
        createPieModel();
    }
    
    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Spenden in diesem Jahr");
        
        // Zwischenspeicher:
        List<Quittung> quittungen = new ArrayList<>();
        Calendar cal = Calendar.getInstance();        
        int monat;
        int zähler = 12;

        List<Number> values = new ArrayList<>();
        quittungen = userController.diagrammQuittungen(jahr);
        for(int i = 0; i < zähler; i++) {
            int wert = 0;
            for(int j = 0; j < quittungen.size(); j++) {
                cal.setTime(quittungen.get(j).getErstelldatum());
                monat = cal.get(Calendar.MONTH);
                if(monat == i) {
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

        // Zwischenspeicher:
        List<Quittung> quittungen = new ArrayList<>();
        Calendar cal = Calendar.getInstance();        
        int monat;
        int zähler = 12;
        
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        quittungen = userController.diagrammQuittungen(jahr);
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
        
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Jahreseinnahmen");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        //dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);

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

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
//        title.setText("Linien-Diagramm");
//        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
    }
    
    public void createBarModel2() {
        barModel2 = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Spenden pro Jahr");
        
        // Jahresberechnung:
        Date dt = new Date();
        int jahr = dt.getYear();
        int aktuellesJahr = jahr + 1900;
        
        // Zwischenspeicher:
        List<Einkommen> einkommen = new ArrayList<>();
        

        List<Number> values = new ArrayList<>();
        einkommen = userController.findAllEinkommen();
        for(int i = 0; i < 5; i++) {
            double wert = 0.0;
            for(int j = 0; j < einkommen.size(); j++) {
                
                if(i == 0) {
                    if(einkommen.get(j).getJahrEinkommen() == aktuellesJahr - 4) {
                        wert = wert + einkommen.get(j).getGeldEinkommen();
                    }                    
                } else if(i == 1) {
                    if(einkommen.get(j).getJahrEinkommen() == aktuellesJahr - 3) {
                        wert = wert + einkommen.get(j).getGeldEinkommen();
                    } 
                } else if(i == 2) {
                    if(einkommen.get(j).getJahrEinkommen() == aktuellesJahr - 2) {
                        wert = wert + einkommen.get(j).getGeldEinkommen();
                    } 
                } else if(i == 3) {
                    if(einkommen.get(j).getJahrEinkommen() == aktuellesJahr - 1) {
                        wert = wert + einkommen.get(j).getGeldEinkommen();
                    } 
                } else if(i == 4) {
                    if(einkommen.get(j).getJahrEinkommen() == aktuellesJahr) {
                        wert = wert + einkommen.get(j).getGeldEinkommen();
                    } 
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
        
        
        // Hierfür Checkboxen:
        List<String> labels = new ArrayList<>();
        
        labels.add(Integer.toString(aktuellesJahr - 4));
        labels.add(Integer.toString(aktuellesJahr - 3));
        labels.add(Integer.toString(aktuellesJahr - 2));
        labels.add(Integer.toString(aktuellesJahr - 1));
        labels.add(Integer.toString(aktuellesJahr));       
        
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
    
    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        // Zwischenspeicher:
        List<Benutzer> benutzer = new ArrayList<>();
        List<Benutzer> benutzerorga = new ArrayList<>();
        
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        benutzer = userController.findAllUser();
        benutzerorga = userController.findAllUserOrga();  
        values.add(benutzerorga.size()); 
        values.add(benutzer.size());      
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");        
        bgColors.add("rgb(54, 162, 235)"); 
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Organisationen");
        labels.add("Spender");
        data.setLabels(labels);

        pieModel.setData(data);
    }
    
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String updateDiagramm() {
        createBarModel();
        return "/Orga/OrgaDaten.xhtml?faces-redirect=true";
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

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }
    
    
    
}
