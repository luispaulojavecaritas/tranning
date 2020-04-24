package pe.com.gesadmin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.ContactoProveedorDao;
import pe.com.gesadmin.entity.ContactoProveedor;
import pe.com.gesadmin.entity.CategoriaOperacion;
import pe.com.gesadmin.transfer.ContactoProveedorTransfer;

@Stateless
public class ContactoProveedorDaoImpl implements ContactoProveedorDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public void create(ContactoProveedor entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(ContactoProveedor entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public ContactoProveedor findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(ContactoProveedor.class, id);

	}
	
	@Override
	public List<ContactoProveedorTransfer> findByProveedorId(Integer id) {
		// TODO Auto-generated method stub
		String query = "select "
				+ "cp.id, "
				+ "td.descripcion, "
				+ "per.nro_documento, per.nombre, per.paterno, per.materno, "
				+ "per.telefono_celular, per.telefono_fijo, per.correo_electronico, "
				+ "pai.descripcion, "
				+ "sex.descripcion, "
				+ "pro.ruc, pro.razon_social, pro.telefono_celular, pro.telefono_fijo, pro.correo_electronico "
				+ "from "
				+ "contacto_proveedor cp "
				+ "left join persona per on cp.id_persona = per.id "
				+ "left join proveedor pro on cp.id_proveedor = pro.id "
				+ "left join tipo_documento td on per.id_tipo_documento = td.id "
				+ "left join pais pai on per.id_pais = pai.id "
				+ "left join sexo sex on per.id_sexo = sex.id "
				+ "where "
				+ "cp.id_proveedor = ? and "
				+ "cp.estado = 1";

		Query q = em.createNativeQuery(query);
		q.setParameter(1, id);
		List<Object[]> resultado = q.getResultList();
		if (resultado == null || resultado.isEmpty()) {
			System.out.println("lista nula o vacia");
			return null;
		} else {
			List<ContactoProveedorTransfer> lista = new ArrayList<>();
			for (Object[] obj : resultado) {
				ContactoProveedorTransfer tarnsfer = new ContactoProveedorTransfer();
				tarnsfer.setId(obj[0].toString() + "");
				tarnsfer.setPerTipoDoc(obj[1].toString() + "");
				tarnsfer.setPerNroDoc(obj[2].toString() + "");
				tarnsfer.setPerNombre(obj[3].toString() + "");
				tarnsfer.setPerPaterno(obj[4].toString() + "");
				tarnsfer.setPerMaterno(obj[5].toString() + "");
				tarnsfer.setPerCelular(obj[6].toString() + "");
				tarnsfer.setPerFijo(obj[7].toString() + "");
				tarnsfer.setPerCorreo(obj[8].toString() + "");
				tarnsfer.setPerPais(obj[9].toString() + "");
				tarnsfer.setPerSexo(obj[10].toString() + "");
				tarnsfer.setProveRuc(obj[11].toString() + "");
				tarnsfer.setProveRazonSocial(obj[12].toString() + "");
				tarnsfer.setProveCelular(obj[13].toString() + "");
				tarnsfer.setProveFijo(obj[14].toString() + "");
				tarnsfer.setProveCorreo(obj[15].toString() + "");
				lista.add(tarnsfer);
			}

			if (lista.size() > 0) {
				return lista;
			} else {
				return null;
			}

		}
	}
	
	@Override
	public List<ContactoProveedor> findByIdProveedor(Integer idProveedor) {
		// TODO Auto-generated method stub
		String query = "Select b FROM ContactoProveedor b where b.proveedor.id = :idProveedor and b.estado = 1";
		TypedQuery<ContactoProveedor> tq = em.createQuery(query, ContactoProveedor.class);
		tq.setParameter("idProveedor", idProveedor);
		List<ContactoProveedor> lista = tq.getResultList();
		return lista;
	}
	

	@Override
	public List<ContactoProveedor> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM ContactoProveedor b";
        TypedQuery<ContactoProveedor> tq = em.createQuery(query, ContactoProveedor.class);
        List<ContactoProveedor> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<ContactoProveedor> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM ContactoProveedor b where b.estado = 1";
        TypedQuery<ContactoProveedor> tq = em.createQuery(query, ContactoProveedor.class);
        List<ContactoProveedor> lista = tq.getResultList();
        return lista;
	}

}
