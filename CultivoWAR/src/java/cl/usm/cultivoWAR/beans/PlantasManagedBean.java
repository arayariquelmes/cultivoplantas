/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.cultivoWAR.beans;

import cl.usm.cultivoModel.dao.PlantasDAOLocal;
import cl.usm.cultivoModel.dto.Planta;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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

    @Inject
    private PlantasDAOLocal plantasDAO;
    private LineChartModel modeloGrafico;
    private LineChartSeries medidasSeries;
    private List<Planta> registros;
    /**
     * Creates a new instance of PlantasManagedBean
     */
    public PlantasManagedBean() {
    }

    @PostConstruct
    public void init(){
        this.cargarGrafico();
    }
    
    public void cargarGrafico(){
        
        this.registros = this.plantasDAO.findAll();
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//desde que formato convertir o a que formato convertir
        registros.forEach(p->{
           
            this.medidasSeries.set(sdf.format(p.getFecha().getTime()), p.getValorHumedad());
        });
        
        
        this.modeloGrafico.addSeries(medidasSeries);
        
    }
    
    public LineChartModel getModeloGrafico() {
        return modeloGrafico;
    }

    public void setModeloGrafico(LineChartModel modeloGrafico) {
        this.modeloGrafico = modeloGrafico;
    }

    public List<Planta> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Planta> registros) {
        this.registros = registros;
    }
    
    
    
}
