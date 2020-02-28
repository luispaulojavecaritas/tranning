/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.Orden;
import pe.com.gesadmin.transfer.OrdenTransfer;

/**
 *
 * @author USUARIO
 */
public interface OrdenDao {
    
    public List<Orden> findByPeriodoId(Integer idPeriodo);    
    
    public List<OrdenTransfer> findTransferAll(); 
  
    public void create (Orden entidad); 
    
    public void update (Orden entidad); 
    
    public Orden findById (Integer id); 
    
}
