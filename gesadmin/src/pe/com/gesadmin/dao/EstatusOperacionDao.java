/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.EstatusOperacion;

/**
 *
 * @author USUARIO
 */
public interface EstatusOperacionDao {
    
    public List<EstatusOperacion> findAll(); 
    
    public List<EstatusOperacion> findAllActive(); 
    
    public void create (EstatusOperacion entidad); 
    
    public void update (EstatusOperacion entidad); 
    
    public EstatusOperacion findById (Integer id);
    
}
