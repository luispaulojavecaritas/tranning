/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.MedidaLuz;

/**
 *
 * @author USUARIO
 */
public interface MedidaLuzDao {
    
    public List<MedidaLuz> findAll(); 
    
    public List<MedidaLuz> findAllActive(); 
    
    public void create (MedidaLuz entidad); 
    
    public void update (MedidaLuz entidad); 
    
    public MedidaLuz findById (Integer id); 
        
}
