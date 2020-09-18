

select * from periodo p;
select * from puesto p2;
select  * from estatus_operacion eo;

select  * from anio_fiscal af;

--PRIMER REPORTE ANUAL TODOS LOS PUESTOS
select pu.id as id, pu.descripcion as descripcion, 
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  af.id < 3) as ANTERIOR,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'ENE' and af.id = 3) as ENERO,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'FEB' and af.id = 3) as FEBRERO,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'MAR' and af.id = 3) as MARZO,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'ABR' and af.id = 3) as ABRIL,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'MAY' and af.id = 3) as MAYO,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'JUN' and af.id = 3) as JUNIO,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'JUL' and af.id = 3) as JULIO,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'AGOSTO' and af.id = 3) as AGOSTO,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'SET' and af.id = 3) as SETIEMBRE,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'OCT' and af.id = 3) as OCTUBRE,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'NOV' and af.id = 3) as NOVIEMBRE,
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'DIC' and af.id = 3) as DICIEMBRE, 
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and af.estado = 3) as TOTAL 
from puesto pu  group by id, descripcion, ANTERIOR, ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SETIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE, TOTAL order by id asc;






-- select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, sum(op.monto) as OP_MONTO, eo.descripcion as EST_OPE 
select sum(oo.monto ) from operacion oo where oo.id_puesto = 4  and oo.estado = 1 and oo.id_estatus_operacion in (1,3) and oo.id_categoria_operacion =;
select sum(oo.monto ) from operacion oo where oo.id_puesto = 4  and oo.estado = 1 and oo.id_estatus_operacion = 2;


--SEGUNDO REPORTE PUESTO OPERACIONES AGRUPADAS
select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, sum(op.monto) as OP_MONTO, eo.descripcion as EST_OPE
from operacion op 
left join puesto pu on pu.id = op.id_puesto 
left join periodo pe on pe.id = op.id_periodo 
left join anio_fiscal af on af.id = pe.id_anio_fiscal 
left join categoria_operacion co on co.id =  op.id_categoria_operacion 
left join estatus_operacion eo on eo.id = op.id_estatus_operacion 
where op.estado = 1 and op.id_puesto = 4 and op.id_estatus_operacion in (1,3) 
group by IDPUESTO, PUESTO_DESCRIPCION, ANIO_PERIO, CAT_OPE_DES, EST_OPE;

select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, sum(op.monto) as OP_MONTO, eo.descripcion as EST_OPE
from operacion op 
left join puesto pu on pu.id = op.id_puesto 
left join periodo pe on pe.id = op.id_periodo 
left join anio_fiscal af on af.id = pe.id_anio_fiscal 
left join categoria_operacion co on co.id =  op.id_categoria_operacion 
left join estatus_operacion eo on eo.id = op.id_estatus_operacion 
where op.estado = 1 and op.id_puesto = 4 and op.id_estatus_operacion = 2 
group by IDPUESTO, PUESTO_DESCRIPCION, ANIO_PERIO, CAT_OPE_DES, EST_OPE;



select pendiente.IDPUESTO, pendiente.PUESTO_DESCRIPCION, pendiente.ANIO_PERIO, pendiente.CAT_OPE_DES ,COALESCE(pendiente.OP_MONTO,0.00) as PAGO_PENDIENTE, COALESCE(efectuado.OP_MONTO,0.00) as PAGO_EFECTUADO, (COALESCE(pendiente.OP_MONTO,0.00) - COALESCE(efectuado.OP_MONTO,0.00)) as SALDO from 
(
select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, sum(op.monto) as OP_MONTO 
from operacion op 
left join puesto pu on pu.id = op.id_puesto 
left join periodo pe on pe.id = op.id_periodo 
left join anio_fiscal af on af.id = pe.id_anio_fiscal 
left join categoria_operacion co on co.id =  op.id_categoria_operacion 
left join estatus_operacion eo on eo.id = op.id_estatus_operacion 
where op.estado = 1 and op.id_puesto = 4 and op.id_estatus_operacion in (1,2,3) 
group by IDPUESTO, PUESTO_DESCRIPCION, ANIO_PERIO, CAT_OPE_DES 
) as pendiente
left join 
(
select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, sum(op.monto) as OP_MONTO 
from operacion op 
left join puesto pu on pu.id = op.id_puesto 
left join periodo pe on pe.id = op.id_periodo 
left join anio_fiscal af on af.id = pe.id_anio_fiscal 
left join categoria_operacion co on co.id =  op.id_categoria_operacion 
left join estatus_operacion eo on eo.id = op.id_estatus_operacion 
where op.estado = 1 and op.id_puesto = 4 and op.id_estatus_operacion = 2 
group by IDPUESTO, PUESTO_DESCRIPCION, ANIO_PERIO, CAT_OPE_DES 
) as  efectuado 
on pendiente.IDPUESTO = efectuado.IDPUESTO and  
pendiente.CAT_OPE_DES = efectuado.CAT_OPE_DES;
--auanod esta pagada la opracion no cruza 








select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, sum(op.monto) as OP_MONTO, eo.descripcion as EST_OPE
from operacion op 
left join puesto pu on pu.id = op.id_puesto 
left join periodo pe on pe.id = op.id_periodo 
left join anio_fiscal af on af.id = pe.id_anio_fiscal 
left join categoria_operacion co on co.id =  op.id_categoria_operacion 
left join estatus_operacion eo on eo.id = op.id_estatus_operacion 
where op.estado = 1 and op.id_puesto = 4 and op.id_estatus_operacion in (1,3) 
group by IDPUESTO, PUESTO_DESCRIPCION, ANIO_PERIO, CAT_OPE_DES, EST_OPE;



select pu.id as id, pu.descripcion as descripcion  
from puesto pu  group by id, descripcion, ANTERIOR, ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SETIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE, TOTAL order by id asc;


select pe.id as id, pe.descripcion as descripcion, 
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'ENE' and af.id = 3) as ENERO 
from periodo pe;


