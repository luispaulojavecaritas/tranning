/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.transfer.PuestoTransfer;

/**
 *
 * @author USUARIO
 */ 

@Local
public interface PuestoService {
       
    public List<Puesto> listar();    
    
    public List<Puesto> listarActivo();  
    
    public List<Puesto> listarActivoLuz(); 
    
    public List<Puesto> listarActivoAgua(); 
    
    public List<Puesto> listarFiltro(boolean flag);
    
    public List<Puesto> listarFiltroNoAdminNiPropiedad();
    
    public List<PuestoTransfer> listarTransfer(); 
  
    public void crear (Puesto entidad); 
    
    public void actualizar (Puesto entidad); 
    
    public Puesto recuperar (Integer id);
    
    
}
