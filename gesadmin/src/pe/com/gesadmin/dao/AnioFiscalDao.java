/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.AnioFiscal;

/**
 *
 * @author USUARIO
 */
public interface AnioFiscalDao {
    
    public List<AnioFiscal> findAll(); 
    
    public List<AnioFiscal> findAllActive(); 
    
    public void create (AnioFiscal entidad); 
    
    public void update (AnioFiscal entidad); 
    
    public AnioFiscal findById (Integer id); 
    
    public List<AnioFiscal> findByDescripcion(String AnioFiscal);
    
    public void updateEstado();
    
}
