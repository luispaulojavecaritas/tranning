/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Orden;
import pe.com.gesadmin.transfer.OrdenTransfer;

/**
 *
 * @author USUARIO
 */

@Local
public interface OrdenService {
    
    public List<Orden> listarPorPeriodoId(Integer idPeriodo);    
    
    public List<OrdenTransfer> listar(); 
  
    public void crear (Orden entidad); 
    
    public void actualizar (Orden entidad); 
    
    public Orden recuperar (Integer id); 
    
}
