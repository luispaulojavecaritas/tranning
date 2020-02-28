package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.TipoDocumentoDao;
import pe.com.gesadmin.dao.impl.TipoDocumentoDaoImpl;
import pe.com.gesadmin.entity.TipoDocumento;
import pe.com.gesadmin.service.TipoDocumentoService;


@Stateless
public class TipoDocumentoServiceImpl implements TipoDocumentoService {
	
	@EJB
	TipoDocumentoDao tipoDocumentoDao = new TipoDocumentoDaoImpl();

	@Override
	public List<TipoDocumento> listar() {
		// TODO Auto-generated method stub
		return tipoDocumentoDao.findAll();
	}

	@Override
	public void crear(TipoDocumento entidad) {
		// TODO Auto-generated method stub
		tipoDocumentoDao.create(entidad);
	}

	@Override
	public void actualizar(TipoDocumento entidad) {
		// TODO Auto-generated method stub
		tipoDocumentoDao.update(entidad);
	}

	@Override
	public TipoDocumento recuperar(Integer id) {
		// TODO Auto-generated method stub
		return tipoDocumentoDao.findById(id);
	}

}
