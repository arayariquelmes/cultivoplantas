/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.cultivoWAR.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author sarayar
 */
@Named(value = "plantasManagedBean")
@ViewScoped
public class PlantasManagedBean implements  Serializable{

    private LineChartModel modeloGrafico;
    private LineChartSeries medidasSeries;
    /**
     * Creates a new instance of PlantasManagedBean
     */
    public PlantasManagedBean() {
    }

    @PostConstruct
    public void init(){
        this.cargarGrafico();
    }
    
    private void cargarGrafico(){
        //Construir modelo del gráfico
        this.modeloGrafico = new LineChartModel();
        this.modeloGrafico.setTitle("Mediciones de Plantas Historicas");
        this.modeloGrafico.setZoom(true);
        this.modeloGrafico.getAxis(AxisType.Y).setLabel("Humedad");
        DateAxis fechaAxis = new DateAxis("Fecha");
        fechaAxis.setTickFormat("%d-%m-%y %H:%M:%S");
        fechaAxis.setTickAngle(-50);
        this.modeloGrafico.getAxes().put(AxisType.X, fechaAxis);
        //Definir series
        this.medidasSeries = new LineChartSeries("Medidas");
        this.medidasSeries.set("2020-01-30 03:00:00", 35.5);
        this.medidasSeries.set("2020-01-30 05:00:00", 50.5);
        this.medidasSeries.set("2020-01-31 12:00:00", 20.5);
        this.medidasSeries.set("2020-01-31 15:00:00", 60.3);
        this.modeloGrafico.addSeries(medidasSeries);
        
    }
    
    public LineChartModel getModeloGrafico() {
        return modeloGrafico;
    }

    public void setModeloGrafico(LineChartModel modeloGrafico) {
        this.modeloGrafico = modeloGrafico;
    }
    
    
    
}
