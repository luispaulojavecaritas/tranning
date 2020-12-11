package pe.com.gesadmin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.com.gesadmin.dao.ReporteDao;
import pe.com.gesadmin.entity.transfer.ReporteDos;
import pe.com.gesadmin.entity.transfer.ReporteTres;
import pe.com.gesadmin.entity.transfer.ReporteUno;
import pe.com.gesadmin.entity.transfer.ReporteCuatro;

@Stateless
public class ReporteDaoImpl implements ReporteDao {

	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<ReporteUno> getReporteUnoCategoriaAll(Integer idAnioFiscal) {
		// TODO Auto-generated method stub
		List<ReporteUno> listaReporte = new ArrayList<>();

		String nativeQuery = "select" + "listapuesto.idpuesto, " + "listapuesto.descripcionpuesto, "
				+ "listapuesto.idbloque, " + "listapuesto.bloquedescripcion, "
				+ "COALESCE(listanombre.nombres, 'SIN REGISTRAR') as persona, "
				+ "COALESCE(listaanterior.anterior,0.00) as anterior, " + "COALESCE(listaenero.enero,0.00) as enero, "
				+ "COALESCE(listafebrero.febrero,0.00) as febrero, " + "COALESCE(listamarzo.marzo,0.00) as marzo, "
				+ "COALESCE(listaabril.abril,0.00) as abril, " + "COALESCE(listamayo.mayo,0.00) as mayo, "
				+ "COALESCE(listajunio.junio,0.00) as junio, " + "COALESCE(listajulio.julio,0.00) as julio, "
				+ "COALESCE(listaagosto.agosto,0.00) as agosto, "
				+ "COALESCE(listasetiembre.setiembre,0.00) as setiembre, "
				+ "COALESCE(listaoctubre.octubre,0.00) as octubre, "
				+ "COALESCE(listanoviembre.noviembre,0.00) as noviembre, "
				+ "COALESCE(listadiciembre.diciembre,0.00) as diciembre, " + "COALESCE(listatotal.total,0.00) as total "
				+ "from (select pu.id as idpuesto, pu.descripcion as descripcionpuesto, pu.id_bloque  as idbloque, blo.descripcion as bloquedescripcion from puesto pu left join bloque blo on blo.id = pu.id_bloque where pu.id_bloque not in (1) and pu.estado = 1) as listapuesto "
				+ "left join (select pu.id as idpuesto, per.nombre||' '||per.paterno||' '||per.materno as nombres from puesto pu left join puesto_persona_cargo ppc on ppc.id_puesto = pu.id left join persona per on per.id = ppc.id_persona where pu.id_bloque not in (1) and pu.estado = 1 and ppc.id_cargo = 1) as listanombre on listapuesto.idpuesto = listanombre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as anterior FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal < ? and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaanterior on listapuesto.idpuesto = listaanterior.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as enero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'ENE' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaenero on listapuesto.idpuesto = listaenero.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as febrero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'FEB' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listafebrero on listapuesto.idpuesto = listafebrero.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as marzo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'MAR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamarzo on listapuesto.idpuesto = listamarzo.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as abril FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'ABR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaabril on listapuesto.idpuesto = listaabril.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as mayo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'MAY' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamayo on listapuesto.idpuesto = listamayo.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as junio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'JUN' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajunio on listapuesto.idpuesto = listajunio.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as julio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'JUL' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajulio on listapuesto.idpuesto = listajulio.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as agosto FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'AGO' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaagosto on listapuesto.idpuesto = listaagosto.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as setiembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'SET' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listasetiembre on listapuesto.idpuesto = listasetiembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as octubre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'OCT' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaoctubre on listapuesto.idpuesto = listaoctubre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as noviembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'NOV' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listanoviembre on listapuesto.idpuesto = listanoviembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as diciembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'DIC' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listadiciembre on listapuesto.idpuesto = listadiciembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as total FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal <= ? and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listatotal on listapuesto.idpuesto = listatotal.idpuesto "
				+ "order by idbloque asc, idpuesto asc";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, idAnioFiscal);
		query.setParameter(2, idAnioFiscal);
		query.setParameter(3, idAnioFiscal);
		query.setParameter(4, idAnioFiscal);
		query.setParameter(5, idAnioFiscal);
		query.setParameter(6, idAnioFiscal);
		query.setParameter(7, idAnioFiscal);
		query.setParameter(8, idAnioFiscal);
		query.setParameter(9, idAnioFiscal);
		query.setParameter(10, idAnioFiscal);
		query.setParameter(11, idAnioFiscal);
		query.setParameter(12, idAnioFiscal);
		query.setParameter(13, idAnioFiscal);
		query.setParameter(14, idAnioFiscal);

		List<Object[]> list = query.getResultList();
		ReporteUno reporte = new ReporteUno();
		
		for (Object[] objeto : list) {
			reporte = new ReporteUno();
			reporte.setIdPuesto(Integer.parseInt(objeto[0].toString()));
			reporte.setDescripcionPuesto(objeto[1].toString());
			reporte.setIdBloque(Integer.parseInt(objeto[2].toString()));
			reporte.setDescripcionBloque(objeto[3].toString());
			reporte.setPersona(objeto[4].toString());
			reporte.setAnterior(Double.parseDouble(objeto[5].toString()));
			reporte.setEnero(Double.parseDouble(objeto[6].toString()));
			reporte.setFebrero(Double.parseDouble(objeto[7].toString()));
			reporte.setMarzo(Double.parseDouble(objeto[8].toString()));
			reporte.setAbril(Double.parseDouble(objeto[9].toString()));
			reporte.setMayo(Double.parseDouble(objeto[10].toString()));
			reporte.setJunio(Double.parseDouble(objeto[11].toString()));
			reporte.setJulio(Double.parseDouble(objeto[12].toString()));
			reporte.setAgosto(Double.parseDouble(objeto[13].toString()));
			reporte.setSetiembre(Double.parseDouble(objeto[14].toString()));
			reporte.setOctubre(Double.parseDouble(objeto[15].toString()));
			reporte.setNoviembre(Double.parseDouble(objeto[16].toString()));
			reporte.setDiciembre(Double.parseDouble(objeto[17].toString()));
			reporte.setTotal(Double.parseDouble(objeto[18].toString()));
	
			listaReporte.add(reporte);					
		}
		
		if(listaReporte.isEmpty() || listaReporte == null) {
			return null;
		}else {
			return listaReporte;
		}

	}

	@Override
	public List<ReporteUno> getReporteUnoCategoriaOperacionId(Integer idAnioFiscal, Integer idCategoriaOperacion) {
		// TODO Auto-generated method stub
		List<ReporteUno> listaReporte = new ArrayList<>();
		
		String nativeQuery = "select " + "listapuesto.idpuesto, " + "listapuesto.descripcionpuesto, "
				+ "listapuesto.idbloque, " + "listapuesto.bloquedescripcion, "
				+ "COALESCE(listanombre.nombres, 'SIN REGISTRAR') as persona, "
				+ "COALESCE(listaanterior.anterior,0.00) as anterior, " + "COALESCE(listaenero.enero,0.00) as enero, "
				+ "COALESCE(listafebrero.febrero,0.00) as febrero, " + "COALESCE(listamarzo.marzo,0.00) as marzo, "
				+ "COALESCE(listaabril.abril,0.00) as abril, " + "COALESCE(listamayo.mayo,0.00) as mayo, "
				+ "COALESCE(listajunio.junio,0.00) as junio, " + "COALESCE(listajulio.julio,0.00) as julio, "
				+ "COALESCE(listaagosto.agosto,0.00) as agosto, "
				+ "COALESCE(listasetiembre.setiembre,0.00) as setiembre, "
				+ "COALESCE(listaoctubre.octubre,0.00) as octubre, "
				+ "COALESCE(listanoviembre.noviembre,0.00) as noviembre, "
				+ "COALESCE(listadiciembre.diciembre,0.00) as diciembre, " + "COALESCE(listatotal.total,0.00) as total "
				+ "from (select pu.id as idpuesto, pu.descripcion as descripcionpuesto, pu.id_bloque  as idbloque, blo.descripcion as bloquedescripcion from puesto pu left join bloque blo on blo.id = pu.id_bloque where pu.id_bloque not in (1) and pu.estado = 1) as listapuesto "
				+ "left join (select pu.id as idpuesto, per.nombre||' '||per.paterno||' '||per.materno as nombres from puesto pu left join puesto_persona_cargo ppc on ppc.id_puesto = pu.id left join persona per on per.id = ppc.id_persona where pu.id_bloque not in (1) and pu.estado = 1 and ppc.id_cargo = 1) as listanombre on listapuesto.idpuesto = listanombre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as anterior FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal < ? and op.id_categoria_operacion = ? and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaanterior on listapuesto.idpuesto = listaanterior.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as enero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'ENE' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaenero on listapuesto.idpuesto = listaenero.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as febrero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'FEB' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listafebrero on listapuesto.idpuesto = listafebrero.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as marzo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'MAR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamarzo on listapuesto.idpuesto = listamarzo.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as abril FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'ABR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaabril on listapuesto.idpuesto = listaabril.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as mayo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'MAY' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamayo on listapuesto.idpuesto = listamayo.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as junio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'JUN' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajunio on listapuesto.idpuesto = listajunio.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as julio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'JUL' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajulio on listapuesto.idpuesto = listajulio.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as agosto FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'AGO' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaagosto on listapuesto.idpuesto = listaagosto.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as setiembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'SET' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listasetiembre on listapuesto.idpuesto = listasetiembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as octubre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'OCT' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaoctubre on listapuesto.idpuesto = listaoctubre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as noviembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'NOV' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listanoviembre on listapuesto.idpuesto = listanoviembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as diciembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'DIC' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listadiciembre on listapuesto.idpuesto = listadiciembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as total FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal <= ? and op.id_categoria_operacion = ? and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listatotal on listapuesto.idpuesto = listatotal.idpuesto "
				+ "order by idbloque, idpuesto asc";
		
		
		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, idAnioFiscal);
		query.setParameter(2, idCategoriaOperacion);
		query.setParameter(3, idAnioFiscal);
		query.setParameter(4, idCategoriaOperacion);
		query.setParameter(5, idAnioFiscal);
		query.setParameter(6, idCategoriaOperacion);
		query.setParameter(7, idAnioFiscal);
		query.setParameter(8, idCategoriaOperacion);
		query.setParameter(9, idAnioFiscal);
		query.setParameter(10, idCategoriaOperacion);
		query.setParameter(11, idAnioFiscal);
		query.setParameter(12, idCategoriaOperacion);
		query.setParameter(13, idAnioFiscal);
		query.setParameter(14, idCategoriaOperacion);
		query.setParameter(15, idAnioFiscal);
		query.setParameter(16, idCategoriaOperacion);
		query.setParameter(17, idAnioFiscal);
		query.setParameter(18, idCategoriaOperacion);
		query.setParameter(19, idAnioFiscal);
		query.setParameter(20, idCategoriaOperacion);
		query.setParameter(21, idAnioFiscal);
		query.setParameter(22, idCategoriaOperacion);
		query.setParameter(23, idAnioFiscal);
		query.setParameter(24, idCategoriaOperacion);
		query.setParameter(25, idAnioFiscal);
		query.setParameter(26, idCategoriaOperacion);
		query.setParameter(27, idAnioFiscal);
		query.setParameter(28, idCategoriaOperacion);

		List<Object[]> list = query.getResultList();
		ReporteUno reporte = new ReporteUno();
		
		for (Object[] objeto : list) {
			reporte = new ReporteUno();
			reporte.setIdPuesto(Integer.parseInt(objeto[0].toString()));
			reporte.setDescripcionPuesto(objeto[1].toString());
			reporte.setIdBloque(Integer.parseInt(objeto[2].toString()));
			reporte.setDescripcionBloque(objeto[3].toString());
			reporte.setPersona(objeto[4].toString());
			reporte.setAnterior(Double.parseDouble(objeto[5].toString()));
			reporte.setEnero(Double.parseDouble(objeto[6].toString()));
			reporte.setFebrero(Double.parseDouble(objeto[7].toString()));
			reporte.setMarzo(Double.parseDouble(objeto[8].toString()));
			reporte.setAbril(Double.parseDouble(objeto[9].toString()));
			reporte.setMayo(Double.parseDouble(objeto[10].toString()));
			reporte.setJunio(Double.parseDouble(objeto[11].toString()));
			reporte.setJulio(Double.parseDouble(objeto[12].toString()));
			reporte.setAgosto(Double.parseDouble(objeto[13].toString()));
			reporte.setSetiembre(Double.parseDouble(objeto[14].toString()));
			reporte.setOctubre(Double.parseDouble(objeto[15].toString()));
			reporte.setNoviembre(Double.parseDouble(objeto[16].toString()));
			reporte.setDiciembre(Double.parseDouble(objeto[17].toString()));
			reporte.setTotal(Double.parseDouble(objeto[18].toString()));
	
			listaReporte.add(reporte);					
		}
		
		if(listaReporte.isEmpty() || listaReporte == null) {
			return null;
		}else {
			return listaReporte;
		}

	}

	@Override
	public List<ReporteDos> getReporteDosPuestoId(Integer idPuesto) {
		// TODO Auto-generated method stub
		List<ReporteDos> listaReporte = new ArrayList<>();
		
		String nativeQuery = "select " + 
		"pendiente.idpuesto, " + 
		"pendiente.descripcionpuesto, " + 
		"pendiente.idaniofiscal, " + 
		"pendiente.descripcionaniofiscal, " + 
		"pendiente.idperiodo, " + 
		"pendiente.descripcionperiodo, " + 
		"pendiente.descripcionaniofiscal || pendiente.descripcionperiodo as aniofiscalperiodo, " + 
		"pendiente.idcategoriaoperacion, " + 
		"pendiente.descripcioncategoriaoperacion, " + 
		"COALESCE(pendiente.montototal,0.00) as montodeuda, " + 
		"COALESCE(efectuado.montototal,0.00) as montopagado, " + 
		"(COALESCE(pendiente.montototal,0.00) - COALESCE(efectuado.montototal,0.00)) as saldodeuda " + 
		"from (select op.id_puesto as idpuesto, pu.descripcion  as descripcionpuesto, pe.id_anio_fiscal as idaniofiscal, af.descripcion as descripcionaniofiscal, pe.id as idperiodo, pe.descripcion as descripcionperiodo, op.id_categoria_operacion as idcategoriaoperacion, co.descripcion as descripcioncategoriaoperacion, sum(op.monto) as montototal from operacion op left join puesto pu on pu.id = op.id_puesto left join periodo pe on pe.id = op.id_periodo left join anio_fiscal af on af.id = pe.id_anio_fiscal left join categoria_operacion co on co.id =  op.id_categoria_operacion left join estatus_operacion eo on eo.id = op.id_estatus_operacion where op.estado = 1 and op.id_puesto = ? and op.id_estatus_operacion in (1,2,3) group by idpuesto, descripcionpuesto, idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion) as pendiente " + 
		"left join (select op.id_puesto as idpuesto, pu.descripcion  as descripcionpuesto, pe.id_anio_fiscal as idaniofiscal, af.descripcion as descripcionaniofiscal, pe.id as idperiodo, pe.descripcion as descripcionperiodo, op.id_categoria_operacion as idcategoriaoperacion, co.descripcion as descripcioncategoriaoperacion, sum(op.monto) as montototal from operacion op left join puesto pu on pu.id = op.id_puesto left join periodo pe on pe.id = op.id_periodo left join anio_fiscal af on af.id = pe.id_anio_fiscal left join categoria_operacion co on co.id =  op.id_categoria_operacion left join estatus_operacion eo on eo.id = op.id_estatus_operacion where op.estado = 1 and op.id_puesto = ? and op.id_estatus_operacion = 2 group by idpuesto, descripcionpuesto, idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion) as  efectuado on pendiente.idpuesto = efectuado.idpuesto and  pendiente.idaniofiscal = efectuado.idaniofiscal and  pendiente.idperiodo = efectuado.idperiodo and pendiente.idcategoriaoperacion = efectuado.idcategoriaoperacion " + 
		"order by idcategoriaoperacion asc, idaniofiscal asc, idperiodo asc";
		
		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, idPuesto);
		query.setParameter(2, idPuesto);
		
		List<Object[]> list = query.getResultList();
		ReporteDos reporte = new ReporteDos();
		
		for (Object[] objeto : list) {
			reporte = new ReporteDos();
			reporte.setIdPuesto(Integer.parseInt(objeto[0].toString()));
			reporte.setDescripcionPuesto(objeto[1].toString());
			reporte.setIdAnioFiscal(Integer.parseInt(objeto[2].toString()));
			reporte.setDescripcionAnioFiscal(objeto[3].toString());
			reporte.setIdPeriodo(Integer.parseInt(objeto[4].toString()));
			reporte.setDescripcionPeriodo(objeto[5].toString());
			reporte.setAnioFiscalPeriodo(objeto[6].toString());
			reporte.setIdCategoriaOperacion(Integer.parseInt(objeto[7].toString()));
			reporte.setDescripcionCategoriaOperacion(objeto[8].toString());
			reporte.setMontoDeuda(Double.parseDouble(objeto[9].toString()));
			reporte.setMontoPagado(Double.parseDouble(objeto[10].toString()));
			reporte.setSaldoDeuda(Double.parseDouble(objeto[11].toString()));
			
			listaReporte.add(reporte);
		}
		
		if(listaReporte.isEmpty() || listaReporte == null) {
			return null;
		}else {
			return listaReporte;
		}		
		
	}

	@Override
	public List<ReporteTres> getReporteTresCategoriaAll(Integer idPuesto) {
		// TODO Auto-generated method stub
		List<ReporteTres> listaReporte = new ArrayList<>();
		
		String nativeQuery = "select " +
		"af.id as idaniofiscal, " +
		"af.descripcion as descripcionaniofiscal, " +
		"pe.id as idperiodo, " +
		"pe.descripcion as descripcionperiodo, " +
		"co.id as idcategoriaoperacion, " +
		"co.descripcion as descripcioncategoriaoperacion, " +
		"to_date(to_char(op.registro, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha, " +
		"'' as descripciontipodoc, " +
		"'' as nrodoc, " +
		"sum(op.monto) as ingreso, " +
		"0.00 as salida " +
		"from operacion op " +
		"left join categoria_operacion co on co.id  = op.id_categoria_operacion " +
		"left join periodo pe ON pe.id = op.id_periodo " +
		"left  join anio_fiscal af on af.id = pe.id_anio_fiscal " +
		"where  op.estado = 1 and op.id_puesto = ? and op.id_estatus_operacion in (1,2,3) " +
		"group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, salida " +
		"union " +
		"select " +
		"af.id as idaniofiscal, " +
		"af.descripcion as descripcionaniofiscal, " +
		"pe.id as idperiodo, " +
		"pe.descripcion as descripcionperiodo, " +
		"co.id as idcategoriaoperacion, " +
		"co.descripcion as descripcioncategoriaoperacion, " +
		"to_date(to_char(op.fecha_pago, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha, " +
		"op.tipo_doc as descripciontipodoc, " +
		"op.nro_doc as nrodoc, " +
		"0.00 as ingreso, " +
		"sum(op.monto) as salida " +
		"from operacion op " +
		"left join categoria_operacion co on co.id  = op.id_categoria_operacion " +
		"left join periodo pe ON pe.id = op.id_periodo " +
		"left  join anio_fiscal af on af.id = pe.id_anio_fiscal " +
		"where  op.estado = 1 and op.id_puesto = ? and op.id_estatus_operacion = 2 " +
		"group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, ingreso " +
		"order by fecha asc, idperiodo asc";
		
		
		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, idPuesto);
		query.setParameter(2, idPuesto);
		
		List<Object[]> list = query.getResultList();
		ReporteTres reporte = new ReporteTres();
		
		for (Object[] objeto : list) {
			reporte = new ReporteTres();
			reporte.setIdAnioFiscal(Integer.parseInt(objeto[0].toString()));
			reporte.setDescripcionAnioFiscal(objeto[1].toString());
			reporte.setIdPeriodo(Integer.parseInt(objeto[2].toString()));
			reporte.setDescripcionPeriodo(objeto[3].toString());
			reporte.setIdCategoriaOperacion(Integer.parseInt(objeto[4].toString()));
			reporte.setDescripcionCategoriaOperacion(objeto[5].toString());
			reporte.setFecha(objeto[6].toString());
			reporte.setDescripcionDocumento(objeto[7].toString());
			reporte.setNroDocumento(objeto[8].toString());
			reporte.setIngreso(Double.parseDouble(objeto[9].toString()));
			reporte.setEgreso(Double.parseDouble(objeto[10].toString()));
			reporte.setSaldo(Double.parseDouble(objeto[11].toString()));
			
			listaReporte.add(reporte);
		}
		
		if(listaReporte.isEmpty() || listaReporte == null) {
			return null;
		}else {
			return listaReporte;
		}	
	}

	@Override
	public List<ReporteTres> getReporteTresCategoriaOperacionId(Integer idPuesto, Integer idCategoriaOperacion) {
		// TODO Auto-generated method stub
		List<ReporteTres> listaReporte = new ArrayList<>();
		
		String nativeQuery = "select " + 
		"af.id as idaniofiscal, " + 
		"af.descripcion as descripcionaniofiscal, " + 
		"pe.id as idperiodo, " + 
		"pe.descripcion as descripcionperiodo, " + 
		"co.id as idcategoriaoperacion, " + 
		"co.descripcion as descripcioncategoriaoperacion, " + 
		"to_date(to_char(op.registro, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha, " + 
		"'' as descripciontipodoc, " + 
		"'' as nrodoc, " + 
		"sum(op.monto) as ingreso, " + 
		"0.00 as salida " + 
		"from operacion op " + 
		"left join categoria_operacion co on co.id  = op.id_categoria_operacion " + 
		"left join periodo pe ON pe.id = op.id_periodo " + 
		"left  join anio_fiscal af on af.id = pe.id_anio_fiscal " + 
		"where op.estado = 1 and op.id_puesto = ? and op.id_categoria_operacion = ? and op.id_estatus_operacion in (1,2,3) " + 
		"group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, salida " + 
		"union " + 
		"select " + 
		"af.id as idaniofiscal, " + 
		"af.descripcion as descripcionaniofiscal, " + 
		"pe.id as idperiodo, " + 
		"pe.descripcion as descripcionperiodo, " + 
		"co.id as idcategoriaoperacion, " + 
		"co.descripcion as descripcioncategoriaoperacion, " + 
		"to_date(to_char(op.fecha_pago, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha, " + 
		"op.tipo_doc as descripciontipodoc, " + 
		"op.nro_doc as nrodoc, " + 
		"0.00 as ingreso, " + 
		"sum(op.monto) as salida " + 
		"from operacion op " + 
		"left join categoria_operacion co on co.id  = op.id_categoria_operacion " + 
		"left join periodo pe ON pe.id = op.id_periodo " + 
		"left  join anio_fiscal af on af.id = pe.id_anio_fiscal " + 
		"where  op.estado = 1 and op.id_puesto = ? and op.id_categoria_operacion = ? and op.id_estatus_operacion = 2 " + 
		"group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, ingreso " + 
		"order by fecha asc, idperiodo asc";
				
		
		
		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, idPuesto);
		query.setParameter(2, idCategoriaOperacion);
		query.setParameter(3, idPuesto);
		query.setParameter(4, idCategoriaOperacion);
		
		List<Object[]> list = query.getResultList();
		ReporteTres reporte = new ReporteTres();
		
		for (Object[] objeto : list) {
			reporte = new ReporteTres();
			reporte.setIdAnioFiscal(Integer.parseInt(objeto[0].toString()));
			reporte.setDescripcionAnioFiscal(objeto[1].toString());
			reporte.setIdPeriodo(Integer.parseInt(objeto[2].toString()));
			reporte.setDescripcionPeriodo(objeto[3].toString());
			reporte.setIdCategoriaOperacion(Integer.parseInt(objeto[4].toString()));
			reporte.setDescripcionCategoriaOperacion(objeto[5].toString());
			reporte.setFecha(objeto[6].toString());
			reporte.setDescripcionDocumento(objeto[7].toString());
			reporte.setNroDocumento(objeto[8].toString());
			reporte.setIngreso(Double.parseDouble(objeto[9].toString()));
			reporte.setEgreso(Double.parseDouble(objeto[10].toString()));
			reporte.setSaldo(Double.parseDouble(objeto[11].toString()));
			
			listaReporte.add(reporte);
		}
		
		if(listaReporte.isEmpty() || listaReporte == null) {
			return null;
		}else {
			return completarSaldoReporteTres(listaReporte);
		}
	}
	
	
	public List<ReporteTres> completarSaldoReporteTres(List<ReporteTres> listaIn){
		
		List<ReporteTres> listaResultado = new ArrayList<>();
		ReporteTres reporte = new ReporteTres();
		
		for(int i=0; i<= listaIn.size()-1; i++) {
			reporte = new ReporteTres();
			reporte = listaIn.get(i);
			
			Double saldoAnterior = (i==0)?0.0:listaIn.get(i-1).getSaldo();
			
			Double ingresoActual =  (listaIn.get(i).getIngreso() == null)?0.0:listaIn.get(i).getIngreso();
			Double egresoActual =  (listaIn.get(i).getEgreso() == null)?0.0:listaIn.get(i).getEgreso();
			
			Double saldoActual = (saldoAnterior + ingresoActual) - egresoActual; 
			reporte.setSaldo(saldoActual);
			
			listaResultado.add(reporte);
		}
		
		return listaResultado;
		
	}

	@Override
	public List<ReporteCuatro> getReporteCuatroDiaNoAnulados(String dia) {
		// TODO Auto-generated method stub
		
		List<ReporteCuatro> listaReporte = new ArrayList<>();
		
		String nativeQuery = "select " + 
		"to_date('2020-12-01', 'YYYY/MM/DD')-1  as fechapagodate, " + 
		"null as idtipooperacion, " + 
		"null as idcategoriaoperacion, " + 
		"'' as fechapagocadena, " + 
		"'' as tipodocumento, " + 
		"'' as nrodocumento, " + 
		"'' as descripcioncategoriaoperacion, " + 
		"'' as periodoaniofiscal, " + 
		"'' as concepto, " + 
		"COALESCE(sum(op.monto),0.0) as montoingreso, " + 
		"(select COALESCE(sum(op.monto),0.0) as montoegreso from operacion op where op.fecha_pago < to_date(?, 'YYYY/MM/DD' ) and op.estado = 1 and op.id_tipo_operacion = 2 and op.id_estatus_operacion = 2) " + 
		"from operacion op " + 
		"where op.fecha_pago < to_date(?, 'YYYY/MM/DD' ) and op.estado = 1 and op.id_tipo_operacion = 1 and op.id_estatus_operacion = 2 " + 
		"UNION " + 
		"select " + 
		"op.fecha_pago as fechapagodate, " + 
		"op.id_tipo_operacion as idtipooperacion, " + 
		"op.id_categoria_operacion as idcategoriaoperacion, " + 
		"to_char(op.fecha_pago , 'YYYY-MM-DD') as fechapagocadena, " + 
		"op.tipo_doc as tipodocumento, " + 
		"op.nro_doc as nrodocumento, " + 
		"co.descripcion as descripcioncategoriaoperacion, " + 
		"af.descripcion || peri.descripcion as periodoaniofiscal, " + 
		"pu.descripcion ||' '||pe.nombre  ||' '|| pe.paterno  ||' '|| pe.materno as concepto, " + 
		"COALESCE(sum(op.monto),0.0) as montoingreso, " + 
		"0.00 as montoegreso " + 
		"from operacion op " + 
		"left join puesto pu on pu.id = op.id_puesto " + 
		"left join persona pe on pe.id = op.id_persona_responsable_operacion " + 
		"left join categoria_operacion co on co.id = op.id_categoria_operacion " + 
		"left join periodo peri on peri.id = op.id_periodo " + 
		"left join anio_fiscal af on af.id = peri.id_anio_fiscal " + 
		"where to_char(op.fecha_pago , 'YYYY-MM-DD')  = ? and op.estado = 1 and op.id_tipo_operacion = 1 and op.id_estatus_operacion = 2 " + 
		"group by fechapagodate, idtipooperacion, idcategoriaoperacion, fechapagocadena, tipodocumento, nrodocumento, descripcioncategoriaoperacion, periodoaniofiscal, concepto, montoegreso " + 
		"UNION " + 
		"select " + 
		"op.fecha_pago as fechapagodate, " + 
		"op.id_tipo_operacion as idtipooperacion, " + 
		"op.id_categoria_operacion as idcategoriaoperacion, " + 
		"to_char(op.fecha_pago , 'YYYY-MM-DD') as fechapagocadena, " + 
		"op.tipo_doc as tipodocumento, " + 
		"op.nro_doc as nrodocumento, " + 
		"co.descripcion as descripcioncategoriaoperacion, " + 
		"af.descripcion || peri.descripcion as periodoaniofiscal, " + 
		"pu.descripcion ||' '||pe.nombre  ||' '|| pe.paterno  ||' '|| pe.materno as concepto, " + 
		"COALESCE(sum(op.monto),0.0) as montoingreso, " + 
		"0.00 as montoegreso " + 
		"from operacion op " + 
		"left join puesto pu on pu.id = op.id_puesto " + 
		"left join persona pe on pe.id = op.id_persona_responsable_operacion " + 
		"left join categoria_operacion co on co.id = op.id_categoria_operacion " + 
		"left join periodo peri on peri.id = op.id_periodo " + 
		"left join anio_fiscal af on af.id = peri.id_anio_fiscal " + 
		"where to_char(op.fecha_pago , 'YYYY-MM-DD')  = ? and op.estado = 1 and op.id_tipo_operacion = 2 and op.id_estatus_operacion = 2 " + 
		"group by fechapagodate, idtipooperacion, idcategoriaoperacion, fechapagocadena, tipodocumento, nrodocumento, descripcioncategoriaoperacion, periodoaniofiscal, concepto, montoegreso " + 
		"order by fechapagodate asc, idtipooperacion asc, idcategoriaoperacion asc";
		
		
		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, dia);
		query.setParameter(2, dia);
		query.setParameter(3, dia);
		query.setParameter(4, dia);
		
		List<Object[]> list = query.getResultList();
		ReporteCuatro reporte = new ReporteCuatro();
		
		for (Object[] objeto : list) {
			reporte = new ReporteCuatro();
			reporte.setFechaPagoCadena(objeto[0].toString());
			reporte.setIdTipoOperacion(Integer.parseInt(objeto[1].toString()));
			reporte.setIdCategoriaOperacion(Integer.parseInt(objeto[2].toString()));
			reporte.setFechaPagoCadena(objeto[3].toString());
			reporte.setTipoDocumento(objeto[4].toString());
			reporte.setNroDocumento(objeto[5].toString());
			reporte.setDescripcionCategoriaOperacion(objeto[6].toString());
			reporte.setPeriodoAnioFiscal(objeto[7].toString());
			reporte.setConcepto(objeto[8].toString());
			reporte.setMontoIngreso(Double.parseDouble(objeto[9].toString()));
			reporte.setMontoEgreso(Double.parseDouble(objeto[10].toString()));
			reporte.setMontoSaldo(Double.parseDouble(objeto[11].toString()));
			
			listaReporte.add(reporte);
		}
		
		if(listaReporte.isEmpty() || listaReporte == null) {
			return null;
		}else {
			return completarSaldoReporteCuatro(listaReporte);
		}		

	}
	
	
	public List<ReporteCuatro> completarSaldoReporteCuatro(List<ReporteCuatro> listaIn){
		
		List<ReporteCuatro> listaResultado = new ArrayList<>();
		ReporteCuatro reporte = new ReporteCuatro();
		
		for(int i=0; i<= listaIn.size()-1; i++) {
			reporte = new ReporteCuatro();
			reporte = listaIn.get(i);
			
			Double saldoAnterior = (i==0)?0.0:listaIn.get(i-1).getMontoSaldo();
			
			Double ingresoActual =  (listaIn.get(i).getMontoIngreso() == null)?0.0:listaIn.get(i).getMontoIngreso();
			Double egresoActual =  (listaIn.get(i).getMontoEgreso() == null)?0.0:listaIn.get(i).getMontoEgreso();
			
			Double saldoActual = (saldoAnterior + ingresoActual) - egresoActual; 
			reporte.setMontoSaldo(saldoActual);
			
			listaResultado.add(reporte);
		}
		
		return listaResultado;
		
	}

}
