/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.MedidaServicio;

/**
 *
 * @author USUARIO
 */
public interface MedidaServicioDao {
    
    public List<MedidaServicio> findAll(); 
    
    public List<MedidaServicio> findAllActive(); 
    
    public List<MedidaServicio> findByPeriodoId(Integer idPeriodo);
    
    public List<MedidaServicio> findByPeriodoIdAll(Integer idPeriodo);
    
    public List<MedidaServicio> findByPeriodoActive();
    
    public List<MedidaServicio>  findByPeriodoidTiposervicioid (Integer idPeriodo, Integer idTipoServicio);
    
    public List<MedidaServicio> findByAnioId(Integer idAnioFiscal);
    
    public List<MedidaServicio> findByAnioIdAll(Integer idAnioFiscal);
    
    public void create (MedidaServicio entidad); 
    
    public void update (MedidaServicio entidad); 
    
    public MedidaServicio findById (Integer id); 
    
    public MedidaServicio findByPuestoidPeriodoidTiposervicioid (Integer idPeriodo, Integer idPuesto, Integer idTipoServicio); 
    
    public void delete (Integer id);
    
    
        
}
