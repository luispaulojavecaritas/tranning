/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.TipoServicio;

/**
 *
 * @author USUARIO
 */
public interface TipoServicioDao {
    
    public List<TipoServicio> findAll(); 
    
    public List<TipoServicio> findAllActive(); 
    
    public void create (TipoServicio entidad); 
    
    public void update (TipoServicio entidad); 
    
    public TipoServicio findById (Integer id); 
    
    
}
