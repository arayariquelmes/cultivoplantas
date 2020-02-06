/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.cultivoModel.dao;

import cl.usm.cultivoModel.dto.Planta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sarayar
 */
@Stateless
public class PlantasDAO implements PlantasDAOLocal {

    private EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("CultivoModelPU");
    @Override
    public List<Planta> findAll() {
        EntityManager em = this.emf.createEntityManager();
        try{
            return em.createNamedQuery("Planta.findAll", Planta.class)
                    .getResultList();
        }catch(Exception ex){
            return null;
        }finally{
            em.close();
        }
        
    }

    @Override
    public void add(Planta planta) {
        EntityManager em = this.emf.createEntityManager();
        try{
            em.persist(planta);
            em.flush();
        }catch(Exception ex){
            
        }finally{
            em.close();
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
