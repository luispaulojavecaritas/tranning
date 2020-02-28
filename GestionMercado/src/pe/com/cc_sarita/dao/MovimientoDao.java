/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.Movimiento;
import pe.com.cc_sarita.transfer.MovimientoTransfer;

/**
 *
 * @author USUARIO
 */
public interface MovimientoDao {
    
    public List<Movimiento> findAll();    
    
    public List<MovimientoTransfer> findTransferAll(); 
  
    public void create (Movimiento entidad); 
    
    public void update (Movimiento entidad); 
    
    public Movimiento findById (Integer id); 
    
}
