/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.ComprobanteCorreccion;

/**
 *
 * @author USUARIO
 */
public interface ComprobanteCorreccionDao {
    
    public List<ComprobanteCorreccion> findAll(); 
    
    public List<ComprobanteCorreccion> findAllActive(); 
    
    public void create (ComprobanteCorreccion entidad); 
    
    public void update (ComprobanteCorreccion entidad); 
    
    public ComprobanteCorreccion findById (Integer id);
    
}
