/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.TipoDocumento;

/**
 *
 * @author USUARIO
 */
public interface TipoDocumentoDao {
    
    public List<TipoDocumento> findAll(); 
    
    public List<TipoDocumento> findAllActive(); 
    
    public void create (TipoDocumento entidad); 
    
    public void update (TipoDocumento entidad); 
    
    public TipoDocumento findById (Integer id);
    
}
