/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.TipoEstatus;

/**
 *
 * @author USUARIO
 */
public interface TipoEstatusDao {
    
    public List<TipoEstatus> findAll(); 
    
    public void create (TipoEstatus entidad); 
    
    public void update (TipoEstatus entidad); 
    
    public TipoEstatus findById (Integer id);
    
}
