/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.cultivoModel.dto;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sarayar
 */
@Entity
@Table(name="plantas")
@NamedQueries({
    @NamedQuery(query = "SELECT p FROM Planta p", name="Planta.findAll")
})
public class Planta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="valor_humedad")
    private double valorHumedad;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValorHumedad() {
        return valorHumedad;
    }

    public void setValorHumedad(double valorHumedad) {
        this.valorHumedad = valorHumedad;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
    
    
}
