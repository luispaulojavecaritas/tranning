/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.MedidaAgua;

/**
 *
 * @author USUARIO
 */
public interface MedidaAguaDao {
    
    public List<MedidaAgua> findAll(); 
    
    public List<MedidaAgua> findAllActive(); 
    
    public void create (MedidaAgua entidad); 
    
    public void update (MedidaAgua entidad); 
    
    public MedidaAgua findById (Integer id); 
        
}
