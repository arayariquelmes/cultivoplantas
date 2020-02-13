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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
    private Date fechaMedida;
    private double valor;
    
    /**
     * Creates a new instance of PlantasManagedBean
     */
    public PlantasManagedBean() {
    }

    @PostConstruct
    public void init(){
        this.cargarGrafico();
        this.fechaMedida = new Date();//El constructor de Date devuelve la fecha actual
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
            // java 8 -> java <8
            //DateTimeFormatter - > SimpleDateFormat
            //LocalDate -> Date
            //LocalDateTime-> Date
            //ZonedDateTime -> Calendar
            this.medidasSeries.set(sdf.format(p.getFecha().getTime()), p.getValorHumedad());
        });
        
        
        this.modeloGrafico.addSeries(medidasSeries);
        
    }
    
    public void registrarMedida(ActionEvent ev){
        Planta p = new Planta();
        //Patron singleton
        //Obtengo un calendar, "equivalente a crear un objeto"
        Calendar fecha = Calendar.getInstance();
        //Defino el tiempo del calendar con el objeto Date
        fecha.setTime(fechaMedida);
        //Paso el calendar a Planta
        p.setFecha(fecha);
        p.setValorHumedad(valor);
        this.plantasDAO.add(p);
        recargarRegistros();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Medida registrada"));
    }
    
    private void recargarRegistros(){
        this.registros = this.plantasDAO.findAll();
        this.cargarGrafico();
    }
    public void descartarMedida(Planta planta){
        //1. Eliminar planta
        this.plantasDAO.remove(planta);        
        //2. Recargar lista de plantas y cargar grafico
        this.recargarRegistros();
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

    public Date getFechaMedida() {
        return fechaMedida;
    }

    public void setFechaMedida(Date fechaMedida) {
        this.fechaMedida = fechaMedida;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
}
