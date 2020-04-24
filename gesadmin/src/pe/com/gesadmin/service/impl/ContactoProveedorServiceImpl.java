package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.ContactoProveedorDao;
import pe.com.gesadmin.dao.impl.ContactoProveedorDaoImpl;
import pe.com.gesadmin.entity.ContactoProveedor;
import pe.com.gesadmin.service.ContactoProveedorService;
import pe.com.gesadmin.transfer.ContactoProveedorTransfer;

@Stateless
public class ContactoProveedorServiceImpl implements ContactoProveedorService {

	@EJB
	ContactoProveedorDao contactoProveedorDao = new ContactoProveedorDaoImpl();
	
	@Override
	public List<ContactoProveedorTransfer> listarPorIdProveedor(Integer idProveedor) {
		// TODO Auto-generated method stub
		return contactoProveedorDao.findByProveedorId(idProveedor);
	}

	@Override
	public void crear(ContactoProveedor entidad) {
		// TODO Auto-generated method stub
		contactoProveedorDao.create(entidad);
	}

	@Override
	public void actualizar(ContactoProveedor entidad) {
		// TODO Auto-generated method stub
		contactoProveedorDao.update(entidad);
	}

	@Override
	public ContactoProveedor recuperar(Integer id) {
		// TODO Auto-generated method stub
		return contactoProveedorDao.findById(id);
	}

	@Override
	public List<ContactoProveedor> listarIdProveedor(Integer idProveedor) {
		// TODO Auto-generated method stub
		return contactoProveedorDao.findByIdProveedor(idProveedor);
	}

	@Override
	public List<ContactoProveedor> listar() {
		// TODO Auto-generated method stub
		return contactoProveedorDao.findAll();
	}
	
	@Override
	public List<ContactoProveedor> listarActivo() {
		// TODO Auto-generated method stub
		return contactoProveedorDao.findAllActive();
	}

}
