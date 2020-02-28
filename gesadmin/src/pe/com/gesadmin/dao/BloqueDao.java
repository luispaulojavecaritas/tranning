/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.Bloque;

/**
 *
 * @author USUARIO
 */
public interface BloqueDao {
    
    public List<Bloque> findAll(); 
    
    public void create (Bloque entidad); 
    
    public void update (Bloque entidad); 
    
    public Bloque findById (Integer id); 
    
}
