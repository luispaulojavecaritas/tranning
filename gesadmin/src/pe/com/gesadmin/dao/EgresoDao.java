/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.Egreso;

/**
 *
 * @author USUARIO
 */
public interface EgresoDao {
    
    public List<Egreso> findAll(); 
    
    public void create (Egreso entidad); 
    
    public void update (Egreso entidad); 
    
    public Egreso findById (Integer id); 
    
}
