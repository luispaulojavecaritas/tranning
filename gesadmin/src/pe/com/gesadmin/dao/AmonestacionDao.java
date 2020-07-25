/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.Amonestacion;

/**
 *
 * @author USUARIO
 */
public interface AmonestacionDao {
    
    public List<Amonestacion> findAll(); 
    
    public List<Amonestacion> findAllActive(); 
    
    public void create (Amonestacion entidad); 
    
    public void update (Amonestacion entidad); 
    
    public Amonestacion findById (Integer id); 
        
    public List<Amonestacion> findByAnioId(Integer idAnio); 
    
    public List<Amonestacion> findByPeriodoId(Integer idPeriodo); 
                
}
