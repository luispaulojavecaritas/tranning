/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.Egreso;

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
