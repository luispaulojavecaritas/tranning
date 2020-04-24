/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;

import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.PuestoPersonaCargo;
import pe.com.gesadmin.transfer.PuestoPersonaTransfer;

/**
 *
 * @author USUARIO
 */
public interface PuestoPersonaCargoDao {
    
    public List<PuestoPersonaCargo> findAll();    
    
    public List<PuestoPersonaCargo> findAllActive(); 
    
    public List<PuestoPersonaCargo> findbyPuestoId(Integer idPuesto); 
      
    public void create (PuestoPersonaCargo entidad); 
    
    public void update (PuestoPersonaCargo entidad); 
    
    public PuestoPersonaCargo findById (Integer id);
    
}
