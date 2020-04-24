/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.Periodo;

/**
 *
 * @author USUARIO
 */
public interface PeriodoDao {
    
    public List<Periodo> findAll(); 
    
    public List<Periodo> findAllActive(); 
    
    public List<Periodo> findByIdAnioFiscal(Integer idAnioFiscal); 
    
    public void create (Periodo entidad); 
    
    public void update (Periodo entidad); 
    
    public Periodo findById (Integer id); 
    
    public void updateEstado();
    
    public void updateEstadoAfterCreateAnioFiscal();
    
    
}
