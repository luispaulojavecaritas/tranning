/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.Ingreso;

/**
 *
 * @author USUARIO
 */
public interface IngresoDao {
    
    public List<Ingreso> findAll(); 
    
    public void create (Ingreso entidad); 
    
    public void update (Ingreso entidad); 
    
    public Ingreso findById (Integer id); 
    
}
