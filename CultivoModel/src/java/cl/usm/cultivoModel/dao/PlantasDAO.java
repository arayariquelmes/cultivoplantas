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
        try {
            return em.createNamedQuery("Planta.findAll", Planta.class)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }

    }

    @Override
    public void add(Planta planta) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.persist(planta);
            em.flush();
        } catch (Exception ex) {

        } finally {
            em.close();
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean remove(Planta planta) {
        //BoilerPlate Code - > Codigo utilizado una cachá de veces
        EntityManager em = this.emf.createEntityManager();
        try {
            em.remove(em.find(Planta.class, planta.getId()));
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public void removeAll() {
        EntityManager em = this.emf.createEntityManager();
        try {
            //Con JPA -> createQuery o createNamedQuery
            //Con SQL -> createNativeQuery
            em.createQuery("DELETE FROM Planta").executeUpdate();
            em.flush();
            //em.createNativeQuery("SELECT * FROM plantas INNER JOIN a, etc etc")
        } catch (Exception ex) {
           
        } finally {
            em.close();
        }
    }

}
