/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.Bloque;

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
