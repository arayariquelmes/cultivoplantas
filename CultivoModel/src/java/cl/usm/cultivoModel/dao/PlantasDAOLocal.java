/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.cultivoModel.dao;

import cl.usm.cultivoModel.dto.Planta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sarayar
 */
@Local
public interface PlantasDAOLocal {
    public List<Planta> findAll();
    public void add(Planta planta);
    public boolean remove(Planta planta);
    public void removeAll();
}
