-- creacion de base de datos gesadmindb
CREATE DATABASE gesadmindb;
 
-- utilizacion de base de datos creada - gesadmindb
USE gesadmindb;

-- creacion de tablas
CREATE TABLE anio_fiscal (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE periodo (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
    id_anio_fiscal int NOT NULL,
    dias int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
	
CREATE TABLE tipo_documento (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
	
CREATE TABLE sexo (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL, 
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE pais (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
	
CREATE TABLE bloque (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);    
    
CREATE TABLE puesto (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	id_bloque int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);	
	
CREATE TABLE cargo (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);	
	
CREATE TABLE puesto_persona_cargo (
    id serial PRIMARY KEY,
    descripcion varchar(50),
    estado int NOT NULL,
	id_puesto int NOT NULL,
	id_persona int NOT NULL,
	id_cargo int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);	

CREATE TABLE persona (
    id serial PRIMARY KEY,
    nombre varchar(50) NOT NULL,
    paterno varchar(50),
	materno varchar(50),
	id_tipo_documento int NOT NULL,
	nro_documento varchar(20) NOT NULL,
	id_pais int NOT NULL,
	direccion varchar(100),
	telefono_fijo varchar(10),
	telefono_celular varchar(10),
	correo_electronico varchar(50),
	id_sexo int NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);	
	
CREATE TABLE proveedor (
    id serial PRIMARY KEY,
	ruc varchar(50) NOT NULL,
    razon_social varchar(100) NOT NULL,
	domicilio_fiscal varchar(100),
    rubro varchar(50),
	telefono_fijo varchar(10),
	telefono_celular varchar(10),
	correo_electronico varchar(50),
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE contacto_proveedor (
    id serial PRIMARY KEY,
	id_proveedor int NOT NULL,
	id_persona int NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);	

CREATE TABLE tipo_operacion (
    id serial PRIMARY KEY,
	descripcion varchar(100) NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
	
	
CREATE TABLE categoria_operacion (
    id serial PRIMARY KEY,
	descripcion varchar(100) NOT NULL,
	estado int NOT NULL,
	id_tipo_operacion int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
    
CREATE TABLE estatus_operacion (
    id serial PRIMARY KEY,
	descripcion varchar(100) NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
	
CREATE TABLE operacion(
    id serial PRIMARY KEY,
	descripcion varchar(200),
	descripcion_eliminacion varchar(200),
	descripcion_monto varchar(200),
	monto decimal NOT NULL,
	fecha_vencimiento date,
	fecha_pago timestamp,
	id_periodo int NOT NULL,
    id_tipo_operacion int NOT NULL,
    id_categoria_operacion int NOT NULL,
    id_estatus_operacion int NOT NULL,
	id_proveedor int,	
    id_puesto int,
    id_persona_responsable_operacion int,
    id_usuario int,
    id_usuario_eliminacion int,
	estado int NOT NULL,
	tipo_doc varchar(50),
	nro_doc varchar(50),
	registro timestamp DEFAULT CURRENT_TIMESTAMP, 
	registro_eliminacion timestamp
	);

CREATE TABLE movimiento (
    id serial PRIMARY KEY,	
	operacion_id int NOT NULL,
	operacion_tipo varchar(50),
	operacion_descripcion varchar(150),
	operacion_situacion varchar(50),
	operacion_fecha_vencimiento varchar(50),
	operacion_fecha_pago varchar(50),
	operacion_importe decimal,
	anio_fiscal varchar(50),
	periodo varchar(50),
	proveedor_ruc varchar(50),
	proveedor_razon_social varchar(200),
	puesto varchar(50),
	bloque varchar(50),
	persona_tipo_doc varchar(50),
	persona_nro_doc varchar(50),
	persona_nombre varchar(50),
	persona_paterno varchar(50),
	persona_materno varchar(50),
	estado int NOT NULL,
	tipo_doc varchar(50),
	nro_doc varchar(50),	
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
    
CREATE TABLE usuario (
    id serial PRIMARY KEY,
	id_persona int NOT NULL,
	usuario varchar(50) NOT NULL,
	clave varchar(50) NOT NULL,
	rol varchar(50), 
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE variable(
    id serial PRIMARY KEY,
	descripcion varchar(100) NOT NULL,
	monto decimal NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE tipo_servicio (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE medida_servicio(
    id serial PRIMARY KEY,
	id_periodo int NOT NULL,
	id_tipo_servicio int NOT NULL,
	id_puesto int NOT NULL,
	id_usuario int,
	medida decimal NOT NULL,
	medida_anterior decimal NOT NULL,
	consumo decimal NOT NULL,
	estado int NOT NULL,
	descripcion varchar(100),
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE tipo_amonestacion(
    id serial PRIMARY KEY,
	descripcion varchar(100) NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE amonestacion(
    id serial PRIMARY KEY,
	id_periodo int NOT NULL,
	id_puesto int NOT NULL,
	id_persona int NOT NULL,
	id_usuario int,
	id_tipo_amonestacion int NOT NULL,
	id_puesto_persona_cargo int NOT null,
	descripcion varchar(1500),
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE comprobante_correccion(
    id serial PRIMARY KEY,
    id_operacion int NOT NULL,
	id_usuario int,
	descripcion_usuario varchar(150),
	motivo varchar(1500),
	monto decimal NOT NULL,
	monto_descripcion varchar(200) NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE recibo_egreso(
    id serial PRIMARY KEY,
    id_operacion int NOT NULL,
	id_usuario int,
	descripcion_usuario varchar(150),
	motivo varchar(1500),
	monto decimal NOT NULL,
	monto_descripcion varchar(200) NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

	
    
-- creacion de llaves foraneas
alter table puesto add constraint fk_puesto_bloque foreign key (id_bloque) references bloque(id);

alter table periodo add constraint fk_periodo_aniofiscal foreign key (id_anio_fiscal) references anio_fiscal(id);

alter table puesto_persona_cargo add constraint fk_puestopersonacargo_puesto foreign key (id_puesto) references puesto(id);
alter table puesto_persona_cargo add constraint fk_puestopersonacargo_persona foreign key (id_persona) references persona(id);
alter table puesto_persona_cargo add constraint fk_puestopersonacargo_cargo foreign key (id_cargo) references cargo(id);

alter table persona add constraint fk_persona_tipodocumento foreign key (id_tipo_documento) references tipo_documento(id);
alter table persona add constraint fk_persona_pais foreign key (id_pais) references pais(id);
alter table persona add constraint fk_persona_sexo foreign key (id_sexo) references sexo(id);

alter table contacto_proveedor add constraint fk_contactoproveedor_persona foreign key (id_persona) references persona(id);
alter table contacto_proveedor add constraint fk_contactoproveedor_proveedor foreign key (id_proveedor) references proveedor(id);

alter table categoria_operacion add constraint fk_categoriaoperacion_tipooperacion foreign key (id_tipo_operacion) references tipo_operacion(id);


alter table operacion add constraint fk_operacion_proveedor foreign key (id_proveedor) references proveedor(id);
alter table operacion add constraint fk_operacion_puesto foreign key (id_puesto) references puesto(id);
alter table operacion add constraint fk_operacion_tipooperacion foreign key (id_tipo_operacion) references tipo_operacion(id);
alter table operacion add constraint fk_operacion_categoriaoperacion foreign key (id_categoria_operacion) references categoria_operacion(id);
alter table operacion add constraint fk_operacion_estatusoperacion foreign key (id_estatus_operacion) references estatus_operacion(id);
alter table operacion add constraint fk_operacion_personaresponsableoperacion foreign key (id_persona_responsable_operacion) references persona(id);
alter table operacion add constraint fk_operacion_periodo foreign key (id_periodo) references periodo(id);

alter table usuario add constraint fk_usuario_persona foreign key (id_persona) references persona(id);

alter table medida_servicio add constraint fk_medidaservicio_periodo foreign key (id_periodo) references periodo(id);
alter table medida_servicio add constraint fk_medidaservicio_puesto foreign key (id_puesto) references puesto(id);
alter table medida_servicio add constraint fk_medidaservicio_tiposervicio foreign key (id_tipo_servicio) references tipo_servicio(id);



alter table amonestacion add constraint fk_amonestacion_periodo foreign key (id_periodo) references periodo(id);
alter table amonestacion add constraint fk_amonestacion_puesto foreign key (id_puesto) references puesto(id);
alter table amonestacion add constraint fk_amonestacion_persona foreign key (id_persona) references persona(id);
alter table amonestacion add constraint fk_amonestacion_tipoamonestacion foreign key (id_tipo_amonestacion) references tipo_amonestacion(id);
alter table amonestacion add constraint fk_amonestacion_puestopersonacargo foreign key (id_puesto_persona_cargo) references puesto_persona_cargo(id);

alter table comprobante_correccion add constraint fk_comprobantecorreccion_operacion foreign key (id_operacion) references operacion(id);
alter table recibo_egreso add constraint fk_reciboegreso_operacion foreign key (id_operacion) references operacion(id);
     

--creacion restriccion de clave unica
alter table periodo ADD CONSTRAINT periodo_uniqueKey UNIQUE (descripcion, id_anio_fiscal);
alter table anio_fiscal ADD CONSTRAINT aniofiscal_uniqueKey UNIQUE (descripcion);
alter table bloque ADD CONSTRAINT bloque_uniqueKey UNIQUE (descripcion);
alter table cargo ADD CONSTRAINT cargo_uniqueKey UNIQUE (descripcion);
alter table pais ADD CONSTRAINT pais_uniqueKey UNIQUE (descripcion);
alter table sexo ADD CONSTRAINT sexo_uniqueKey UNIQUE (descripcion);
alter table puesto ADD CONSTRAINT puesto_uniqueKey UNIQUE (descripcion, id_bloque);
alter table estatus_operacion ADD CONSTRAINT estatusoperacion_uniqueKey UNIQUE (descripcion);
alter table tipo_documento ADD CONSTRAINT tipodocumento_uniqueKey UNIQUE (descripcion);
alter table comprobante_correccion ADD CONSTRAINT comprobantecorreccion_uniqueKey UNIQUE (id_operacion);
alter table recibo_egreso ADD CONSTRAINT reciboegreso_uniqueKey UNIQUE (id_operacion);


--alter table medida_servicio ADD CONSTRAINT medidaservicio_uniqueKey UNIQUE (id_periodo, id_tipo_servicio, id_puesto);


descripcion_monto


-- ALTER table orden drop foreign key fk_orden_tipoorden;
-- ALTER table movimiento add constraint fk_movimiento_idorden foreign key (id_orden) references orden(id);
-- ALTER TABLE proveedor ALTER COLUMN ruc TYPE varchar(50);
-- ALTER TABLE persona ALTER COLUMN nro_documento TYPE varchar(20);
-- ALTER TABLE tipo_orden RENAME TO tipo_operacion;
-- ALTER table periodo DROP CONSTRAINT periodo_uniqueKey;
--  alter table medida_servicio add column medida_anterior decimal NOT NULL;
-- alter table medida_servicio add column descripcion varchar(100);
 alter table operacion add column descripcion_monto varchar(100);
-- ALTER TABLE medida_servicio ALTER COLUMN descripcion DROP NOT NULL;

-- ALTER TABLE periodo ALTER COLUMN dias add constraint  NOT NULL;
-- ALTER TABLE movimiento ALTER COLUMN operacion_importe  decimal;
-- ALTER TABLE periodo ADD COLUMN new_column_name data_type;
-- ALTER TABLE amonestacion ADD COLUMN id_puesto_persona_cargo int NOT NULL;
-- ALTER TABLE periodo ADD COLUMN dias int;
-- ALTER TABLE amonestacion RENAME COLUMN id_pesona TO id_persona;
-- TRUNCATE TABLE medida_servicio;

-- drop TABLE medida_servicio ;



select pu.id, pu.descripcion, pu.estado 
from puesto pu 
where 
pu.estado = 1 and pu.id_bloque <> 1 and 
pu.id not in (select ppc.id_puesto from puesto_persona_cargo ppc left join persona  per on ppc.id_persona  = per.id where ppc.estado = 1 and per.nombre like '%ASOCIACION%');


select distinct  ppc.id_puesto from puesto_persona_cargo ppc left join persona  per on ppc.id_persona  = per.id where ppc.estado = 1 and per.nombre like '%ASOCIACION%'; 





-- insercion de data
---INICIO PRUEBA
insert into bloque (descripcion, estado) values ('ADM', 1);
insert into bloque (descripcion, estado) values ('A', 1);
insert into bloque (descripcion, estado) values ('B', 1);


insert into puesto (id_bloque, descripcion, estado) values (1, 'OFIADM', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'1A', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'2A', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'1B', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'2B', 1);
--- FIN PRUEBA

-- insercion de data

insert into bloque (descripcion, estado) values ('ADM', 1);
insert into bloque (descripcion, estado) values ('A', 1);
insert into bloque (descripcion, estado) values ('B', 1);
insert into bloque (descripcion, estado) values ('C', 1);
insert into bloque (descripcion, estado) values ('D', 1);
insert into bloque (descripcion, estado) values ('E', 1);
insert into bloque (descripcion, estado) values ('F', 1);
insert into bloque (descripcion, estado) values ('G', 1);
insert into bloque (descripcion, estado) values ('H', 1);
insert into bloque (descripcion, estado) values ('I', 1);
insert into bloque (descripcion, estado) values ('K', 1);
insert into bloque (descripcion, estado) values ('L', 1);
insert into bloque (descripcion, estado) values ('M', 1);
insert into bloque (descripcion, estado) values ('N', 1);
insert into bloque (descripcion, estado) values ('O', 1);
insert into bloque (descripcion, estado) values ('P', 1);
insert into bloque (descripcion, estado) values ('Q', 1);
insert into bloque (descripcion, estado) values ('R', 1);
insert into bloque (descripcion, estado) values ('S', 1);
insert into bloque (descripcion, estado) values ('T', 1);
/*
insert into bloque (id, descripcion, estado) values (1, 'ADM', 1);
insert into bloque (id, descripcion, estado) values (2, 'A', 1);
insert into bloque (id, descripcion, estado) values (3, 'B', 1);
insert into bloque (id, descripcion, estado) values (4, 'C', 1);
insert into bloque (id, descripcion, estado) values (5, 'D', 1);
insert into bloque (id, descripcion, estado) values (6, 'E', 1);
insert into bloque (id, descripcion, estado) values (7, 'F', 1);
insert into bloque (id, descripcion, estado) values (8, 'G', 1);
insert into bloque (id, descripcion, estado) values (9, 'H', 1);
insert into bloque (id, descripcion, estado) values (10, 'I', 1);
insert into bloque (id, descripcion, estado) values (11, 'K', 1);
insert into bloque (id, descripcion, estado) values (12, 'L', 1);
insert into bloque (id, descripcion, estado) values (13, 'M', 1);
insert into bloque (id, descripcion, estado) values (14, 'N', 1);
insert into bloque (id, descripcion, estado) values (15, 'O', 1);
insert into bloque (id, descripcion, estado) values (16, 'P', 1);
insert into bloque (id, descripcion, estado) values (17, 'Q', 1);
insert into bloque (id, descripcion, estado) values (18, 'R', 1);
insert into bloque (id, descripcion, estado) values (19, 'S', 1);
insert into bloque (id, descripcion, estado) values (20, 'T', 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('bloque', 'id'), (SELECT MAX(id) FROM bloque)+1);
*/




insert into puesto (id_bloque, descripcion, estado) values (1,'OFIADM', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A1', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A2', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A3', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A4', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A5', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A6', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A7', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A8', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A9', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A10', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A11', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A12', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A12A', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A13', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A14', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A15', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A16', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A17', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A18', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A19', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A20', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A21', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A22', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A23', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A24', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A25', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A26', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A27', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A28', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'A29', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B1', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B1A', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B1B', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B2', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B3', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B4', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B5', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B6', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B7', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B8', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B9', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B9A', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B10', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B11', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B12', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B13', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B14', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B15', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B16', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B17', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B18', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B19', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'B20', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C1', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C2', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C3', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C4', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C6', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C7', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C8', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C9', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C9A', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C10', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C10A', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C11', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C12', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C13', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C14', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C15', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C16', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C17', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C18', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C19', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C20', 1);
insert into puesto (id_bloque, descripcion, estado) values (4,'C22', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D1', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D2', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D3', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D4', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D5', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D6', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D7', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D8', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D9', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D10', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D11', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D12', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D13', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D14', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D15', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D16', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D17', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D18', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D19', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D20', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D21', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D22', 1);
insert into puesto (id_bloque, descripcion, estado) values (5,'D22A', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E1', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E2', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E3', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E4', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E5', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E6', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E7', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E8', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E9', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E10', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E11', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E12', 1);
insert into puesto (id_bloque, descripcion, estado) values (6,'E13', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F1', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F1A', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F2', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F3', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F4', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F5', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F6', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F7', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F7A', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F8', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F9', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F10', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F11', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F12', 1);
insert into puesto (id_bloque, descripcion, estado) values (7,'F13', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G1', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G1A', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G2', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G3', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G3A', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G4', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G4A', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G5', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G6', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G7', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G7A', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G8', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G9', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G10', 1);
insert into puesto (id_bloque, descripcion, estado) values (8,'G11', 1);
insert into puesto (id_bloque, descripcion, estado) values (9,'H1', 1);
insert into puesto (id_bloque, descripcion, estado) values (9,'H2', 1);
insert into puesto (id_bloque, descripcion, estado) values (9,'H3', 1);
insert into puesto (id_bloque, descripcion, estado) values (9,'H4', 1);
insert into puesto (id_bloque, descripcion, estado) values (9,'H5', 1);
insert into puesto (id_bloque, descripcion, estado) values (9,'H6', 1);
insert into puesto (id_bloque, descripcion, estado) values (9,'H7', 1);
insert into puesto (id_bloque, descripcion, estado) values (9,'H7A', 1);
insert into puesto (id_bloque, descripcion, estado) values (10,'I1', 1);
insert into puesto (id_bloque, descripcion, estado) values (10,'I2', 1);
insert into puesto (id_bloque, descripcion, estado) values (10,'I3', 1);
insert into puesto (id_bloque, descripcion, estado) values (10,'I4', 1);
insert into puesto (id_bloque, descripcion, estado) values (10,'I5', 1);
insert into puesto (id_bloque, descripcion, estado) values (10,'I6', 1);
insert into puesto (id_bloque, descripcion, estado) values (10,'I7', 1);
insert into puesto (id_bloque, descripcion, estado) values (10,'I8', 1);
insert into puesto (id_bloque, descripcion, estado) values (10,'I9', 1);
insert into puesto (id_bloque, descripcion, estado) values (11,'K1', 1);
insert into puesto (id_bloque, descripcion, estado) values (11,'K2', 1);
insert into puesto (id_bloque, descripcion, estado) values (11,'K3', 1);
insert into puesto (id_bloque, descripcion, estado) values (11,'K4', 1);
insert into puesto (id_bloque, descripcion, estado) values (11,'K5', 1);
insert into puesto (id_bloque, descripcion, estado) values (11,'K6', 1);
insert into puesto (id_bloque, descripcion, estado) values (11,'K7', 1);
insert into puesto (id_bloque, descripcion, estado) values (11,'K8', 1);
insert into puesto (id_bloque, descripcion, estado) values (11,'K9', 1);
insert into puesto (id_bloque, descripcion, estado) values (11,'K10', 1);
insert into puesto (id_bloque, descripcion, estado) values (12,'L1', 1);
insert into puesto (id_bloque, descripcion, estado) values (12,'L2', 1);
insert into puesto (id_bloque, descripcion, estado) values (12,'L3', 1);
insert into puesto (id_bloque, descripcion, estado) values (12,'L4', 1);
insert into puesto (id_bloque, descripcion, estado) values (12,'L5', 1);
insert into puesto (id_bloque, descripcion, estado) values (12,'L6', 1);
insert into puesto (id_bloque, descripcion, estado) values (12,'L7', 1);
insert into puesto (id_bloque, descripcion, estado) values (12,'L8', 1);
insert into puesto (id_bloque, descripcion, estado) values (12,'L9', 1);
insert into puesto (id_bloque, descripcion, estado) values (12,'L10', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M1', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M2', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M3', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M4', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M5', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M6', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M7', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M8', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M9', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M10', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M11', 1);
insert into puesto (id_bloque, descripcion, estado) values (13,'M12', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N1', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N2', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N3', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N4', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N5', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N6', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N7', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N8', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N9', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N10', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N11', 1);
insert into puesto (id_bloque, descripcion, estado) values (14,'N12', 1);
insert into puesto (id_bloque, descripcion, estado) values (15,'O1', 1);
insert into puesto (id_bloque, descripcion, estado) values (15,'O2', 1);
insert into puesto (id_bloque, descripcion, estado) values (15,'O3', 1);
insert into puesto (id_bloque, descripcion, estado) values (15,'O4', 1);
insert into puesto (id_bloque, descripcion, estado) values (15,'O5', 1);
insert into puesto (id_bloque, descripcion, estado) values (15,'O6', 1);
insert into puesto (id_bloque, descripcion, estado) values (15,'O7', 1);
insert into puesto (id_bloque, descripcion, estado) values (15,'O8', 1);
insert into puesto (id_bloque, descripcion, estado) values (15,'O9', 1);
insert into puesto (id_bloque, descripcion, estado) values (15,'O10', 1);
insert into puesto (id_bloque, descripcion, estado) values (16,'P1', 1);
insert into puesto (id_bloque, descripcion, estado) values (16,'P2', 1);
insert into puesto (id_bloque, descripcion, estado) values (16,'P3', 1);
insert into puesto (id_bloque, descripcion, estado) values (16,'P4', 1);
insert into puesto (id_bloque, descripcion, estado) values (16,'P5', 1);
insert into puesto (id_bloque, descripcion, estado) values (16,'P6', 1);
insert into puesto (id_bloque, descripcion, estado) values (16,'P7', 1);
insert into puesto (id_bloque, descripcion, estado) values (16,'P8', 1);
insert into puesto (id_bloque, descripcion, estado) values (16,'P9', 1);
insert into puesto (id_bloque, descripcion, estado) values (16,'P10', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q1', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q2', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q3', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q4', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q5', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q6', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q7', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q8', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q9', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q10', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q11', 1);
insert into puesto (id_bloque, descripcion, estado) values (17,'Q12', 1);
insert into puesto (id_bloque, descripcion, estado) values (18,'R1', 1);
insert into puesto (id_bloque, descripcion, estado) values (18,'R2', 1);
insert into puesto (id_bloque, descripcion, estado) values (18,'R3', 1);
insert into puesto (id_bloque, descripcion, estado) values (18,'R4', 1);
insert into puesto (id_bloque, descripcion, estado) values (18,'R5', 1);
insert into puesto (id_bloque, descripcion, estado) values (18,'R6', 1);
insert into puesto (id_bloque, descripcion, estado) values (18,'R7', 1);
insert into puesto (id_bloque, descripcion, estado) values (18,'R8', 1);
insert into puesto (id_bloque, descripcion, estado) values (18,'R9', 1);
insert into puesto (id_bloque, descripcion, estado) values (18,'R10', 1);
insert into puesto (id_bloque, descripcion, estado) values (19,'S1', 1);
insert into puesto (id_bloque, descripcion, estado) values (19,'S2', 1);
insert into puesto (id_bloque, descripcion, estado) values (19,'S3', 1);
insert into puesto (id_bloque, descripcion, estado) values (19,'S4', 1);
insert into puesto (id_bloque, descripcion, estado) values (19,'S5', 1);
insert into puesto (id_bloque, descripcion, estado) values (19,'S6', 1);
insert into puesto (id_bloque, descripcion, estado) values (19,'S7', 1);
insert into puesto (id_bloque, descripcion, estado) values (19,'S8', 1);
insert into puesto (id_bloque, descripcion, estado) values (19,'S9', 1);
insert into puesto (id_bloque, descripcion, estado) values (19,'S10', 1);
insert into puesto (id_bloque, descripcion, estado) values (20,'T1', 1);
insert into puesto (id_bloque, descripcion, estado) values (20,'T2', 1);
insert into puesto (id_bloque, descripcion, estado) values (20,'T3', 1);
insert into puesto (id_bloque, descripcion, estado) values (20,'T4', 1);
insert into puesto (id_bloque, descripcion, estado) values (20,'T5', 1);
insert into puesto (id_bloque, descripcion, estado) values (20,'T6', 1);
insert into puesto (id_bloque, descripcion, estado) values (20,'T7', 1);
insert into puesto (id_bloque, descripcion, estado) values (20,'T8', 1);
insert into puesto (id_bloque, descripcion, estado) values (20,'T9', 1);
insert into puesto (id_bloque, descripcion, estado) values (20,'T10', 1);
/*
insert into puesto (id, id_bloque, descripcion, estado) values (1, 1,'OFIADM', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (2, 2,'A1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (3, 2,'A2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (4, 2,'A3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (5, 2,'A4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (6, 2,'A5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (7, 2,'A6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (8, 2,'A7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (9, 2,'A8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (10, 2,'A9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (11, 2,'A10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (12, 2,'A11', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (13, 2,'A12', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (14, 2,'A12A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (15, 2,'A13', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (16, 2,'A14', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (17, 2,'A15', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (18, 2,'A16', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (19, 2,'A17', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (20, 2,'A18', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (21, 2,'A19', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (22, 2,'A20', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (23, 2,'A21', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (24, 2,'A22', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (25, 2,'A23', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (26, 2,'A24', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (27, 2,'A25', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (28, 2,'A26', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (29, 2,'A27', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (30, 2,'A28', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (31, 2,'A29', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (32, 3,'B1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (33, 3,'B1A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (34, 3,'B1B', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (35, 3,'B2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (36, 3,'B3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (37, 3,'B4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (38, 3,'B5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (39, 3,'B6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (40, 3,'B7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (41, 3,'B8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (42, 3,'B9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (43, 3,'B9A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (44, 3,'B10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (45, 3,'B11', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (46, 3,'B12', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (47, 3,'B13', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (48, 3,'B14', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (49, 3,'B15', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (50, 3,'B16', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (51, 3,'B17', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (52, 3,'B18', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (53, 3,'B19', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (54, 3,'B20', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (55, 4,'C1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (56, 4,'C2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (57, 4,'C3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (58, 4,'C4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (59, 4,'C6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (60, 4,'C7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (61, 4,'C8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (62, 4,'C9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (63, 4,'C9A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (64, 4,'C10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (65, 4,'C10A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (66, 4,'C11', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (67, 4,'C12', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (68, 4,'C13', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (69, 4,'C14', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (70, 4,'C15', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (71, 4,'C16', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (72, 4,'C17', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (73, 4,'C18', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (74, 4,'C19', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (75, 4,'C20', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (76, 4,'C22', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (77, 5,'D1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (78, 5,'D2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (79, 5,'D3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (80, 5,'D4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (81, 5,'D5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (82, 5,'D6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (83, 5,'D7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (84, 5,'D8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (85, 5,'D9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (86, 5,'D10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (87, 5,'D11', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (88, 5,'D12', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (89, 5,'D13', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (90, 5,'D14', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (91, 5,'D15', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (92, 5,'D16', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (93, 5,'D17', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (94, 5,'D18', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (95, 5,'D19', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (96, 5,'D20', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (97, 5,'D21', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (98, 5,'D22', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (99, 5,'D22A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (100, 6,'E1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (101, 6,'E2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (102, 6,'E3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (103, 6,'E4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (104, 6,'E5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (105, 6,'E6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (106, 6,'E7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (107, 6,'E8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (108, 6,'E9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (109, 6,'E10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (110, 6,'E11', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (111, 6,'E12', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (112, 6,'E13', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (113, 7,'F1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (114, 7,'F1A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (115, 7,'F2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (116, 7,'F3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (117, 7,'F4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (118, 7,'F5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (119, 7,'F6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (120, 7,'F7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (121, 7,'F7A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (122, 7,'F8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (123, 7,'F9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (124, 7,'F10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (125, 7,'F11', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (126, 7,'F12', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (127, 7,'F13', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (128, 8,'G1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (129, 8,'G1A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (130, 8,'G2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (131, 8,'G3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (132, 8,'G3A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (133, 8,'G4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (134, 8,'G4A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (135, 8,'G5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (136, 8,'G6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (137, 8,'G7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (138, 8,'G7A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (139, 8,'G8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (140, 8,'G9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (141, 8,'G10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (142, 8,'G11', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (143, 9,'H1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (144, 9,'H2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (145, 9,'H3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (146, 9,'H4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (147, 9,'H5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (148, 9,'H6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (149, 9,'H7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (150, 9,'H7A', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (151, 10,'I1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (152, 10,'I2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (153, 10,'I3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (154, 10,'I4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (155, 10,'I5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (156, 10,'I6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (157, 10,'I7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (158, 10,'I8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (159, 10,'I9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (160, 11,'K1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (161, 11,'K2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (162, 11,'K3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (163, 11,'K4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (164, 11,'K5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (165, 11,'K6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (166, 11,'K7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (167, 11,'K8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (168, 11,'K9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (169, 11,'K10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (170, 12,'L1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (171, 12,'L2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (172, 12,'L3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (173, 12,'L4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (174, 12,'L5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (175, 12,'L6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (176, 12,'L7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (177, 12,'L8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (178, 12,'L9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (179, 12,'L10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (180, 13,'M1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (181, 13,'M2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (182, 13,'M3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (183, 13,'M4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (184, 13,'M5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (185, 13,'M6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (186, 13,'M7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (187, 13,'M8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (188, 13,'M9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (189, 13,'M10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (190, 13,'M11', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (191, 13,'M12', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (192, 14,'N1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (193, 14,'N2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (194, 14,'N3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (195, 14,'N4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (196, 14,'N5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (197, 14,'N6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (198, 14,'N7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (199, 14,'N8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (200, 14,'N9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (201, 14,'N10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (202, 14,'N11', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (203, 14,'N12', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (204, 15,'O1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (205, 15,'O2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (206, 15,'O3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (207, 15,'O4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (208, 15,'O5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (209, 15,'O6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (210, 15,'O7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (211, 15,'O8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (212, 15,'O9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (213, 15,'O10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (214, 16,'P1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (215, 16,'P2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (216, 16,'P3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (217, 16,'P4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (218, 16,'P5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (219, 16,'P6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (220, 16,'P7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (221, 16,'P8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (222, 16,'P9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (223, 16,'P10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (224, 17,'Q1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (225, 17,'Q2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (226, 17,'Q3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (227, 17,'Q4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (228, 17,'Q5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (229, 17,'Q6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (230, 17,'Q7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (231, 17,'Q8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (232, 17,'Q9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (233, 17,'Q10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (234, 17,'Q11', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (235, 17,'Q12', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (236, 18,'R1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (237, 18,'R2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (238, 18,'R3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (239, 18,'R4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (240, 18,'R5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (241, 18,'R6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (242, 18,'R7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (243, 18,'R8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (244, 18,'R9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (245, 18,'R10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (246, 19,'S1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (247, 19,'S2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (248, 19,'S3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (249, 19,'S4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (250, 19,'S5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (251, 19,'S6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (252, 19,'S7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (253, 19,'S8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (254, 19,'S9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (255, 19,'S10', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (256, 20,'T1', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (257, 20,'T2', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (258, 20,'T3', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (259, 20,'T4', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (260, 20,'T5', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (261, 20,'T6', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (262, 20,'T7', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (263, 20,'T8', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (264, 20,'T9', 1);
insert into puesto (id, id_bloque, descripcion, estado) values (265, 20,'T10', 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('puesto', 'id'), (SELECT MAX(id) FROM puesto)+1);
*/


insert into pais (descripcion, estado) values ('PERU', 1);
insert into pais (descripcion, estado) values ('ARGENTINA', 1);
insert into pais (descripcion, estado) values ('BOLIVIA', 1);
insert into pais (descripcion, estado) values ('COLOMBIA', 1);
insert into pais (descripcion, estado) values ('VENEZUELA', 1);
/*
insert into pais (id, descripcion, estado) values (1, 'PERU', 1);
insert into pais (id, descripcion, estado) values (2, 'ARGENTINA', 1);
insert into pais (id, descripcion, estado) values (3, 'BOLIVIA', 1);
insert into pais (id, descripcion, estado) values (4, 'COLOMBIA', 1);
insert into pais (id, descripcion, estado) values (5, 'VENEZUELA', 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('pais', 'id'), (SELECT MAX(id) FROM pais)+1);
*/

insert into sexo (descripcion, estado) values ('MASCULINO', 1);
insert into sexo (descripcion, estado) values ('FEMENINO', 1);
/*
insert into sexo (id, descripcion, estado) values (1, 'MASCULINO', 1);
insert into sexo (id, descripcion, estado) values (2, 'FEMENINO', 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('sexo', 'id'), (SELECT MAX(id) FROM sexo)+1);
*/

insert into estatus_operacion (descripcion, estado) values ('PENDIENTE', 1);
insert into estatus_operacion (descripcion, estado) values ('EFECTUADA', 1);
insert into estatus_operacion (descripcion, estado) values ('VENCIDA', 1);
insert into estatus_operacion (descripcion, estado) values ('CANCELADA', 1);
/*
insert into estatus_operacion (id, descripcion, estado) values (1, 'PENDIENTE', 1);
insert into estatus_operacion (id, descripcion, estado) values (2, 'EFECTUADA', 1);
insert into estatus_operacion (id, descripcion, estado) values (3, 'VENCIDA', 1);
insert into estatus_operacion (id, descripcion, estado) values (4, 'CANCELADA', 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('estatus_operacion', 'id'), (SELECT MAX(id) FROM estatus_operacion)+1);
*/

insert into tipo_documento (descripcion, estado) values ('DNI', 1);
insert into tipo_documento (descripcion, estado) values ('CARNET EXTRANJERIA', 1);
insert into tipo_documento (descripcion, estado) values ('PASAPORTE', 1);
insert into tipo_documento (descripcion, estado) values ('PTP', 1);
/*
insert into tipo_documento (id, descripcion, estado) values (1, 'DNI', 1);
insert into tipo_documento (id, descripcion, estado) values (2, 'CARNET EXTRANJERIA', 1);
insert into tipo_documento (id, descripcion, estado) values (3, 'PASAPORTE', 1);
insert into tipo_documento (id, descripcion, estado) values (4, 'PTP', 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('tipo_documento', 'id'), (SELECT MAX(id) FROM tipo_documento)+1);
*/

insert into proveedor (ruc, razon_social, domicilio_fiscal, rubro,  estado) values ('20269985900', 'ENEL DISTRIBUCION PERU S.A.A.', 'CAL. CESAR LOPEZ ROJAS NRO. 201 URB. MARANGA 7MA ETAPA, SAN MIGUEL', 'GENERACION Y DIST. ENERGIA ELECTRICA.', 1 );
insert into proveedor (ruc, razon_social, domicilio_fiscal, rubro,  estado) values ('20100152356', 'SERV AGUA POTAB Y ALCANT DE LIMA-SEDAPAL', 'AUTOP.RAMIRO PRIALE NRO. 210 LA ATARJEA (KM. 1 AUTOPISTA RAMIRO PRIALE), EL AGUSTINO', 'CAPTACION , DEPURACION Y DIST. DE AGUA', 1);
insert into proveedor (ruc, razon_social, domicilio_fiscal, rubro,  estado) values ('20503825911', 'ASOC. DE COM. C.C. SARITA COLONIA', 'MZ. A LT. 1 URB. LA ALBORADA DE SANTA ROSA, SAN MARTIN DE PORRES', 'ACTIVIDADES OTRAS ASOCIACIONES NCP.', 1);

insert into cargo (descripcion, estado) values ('PROPIETARIO', 1);
insert into cargo (descripcion, estado) values ('INQUILINO', 1);
insert into cargo (descripcion, estado) values ('ADMINISTRATIVO', 1);
/*
insert into cargo (id, descripcion, estado) values (1, 'PROPIETARIO', 1);
insert into cargo (id, descripcion, estado) values (2, 'INQUILINO', 1);
insert into cargo (id, descripcion, estado) values (3, 'ADMINISTRATIVO', 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('cargo', 'id'), (SELECT MAX(id) FROM cargo)+1);
*/

insert into tipo_operacion (descripcion, estado) values ('INGRESO', 1);
insert into tipo_operacion (descripcion, estado) values ('EGRESO', 1);
/*
insert into tipo_operacion (id, descripcion, estado) values (1, 'INGRESO', 1);
insert into tipo_operacion (id, descripcion, estado) values (2, 'EGRESO', 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('tipo_operacion', 'id'), (SELECT MAX(id) FROM tipo_operacion)+1);
*/



insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROSENDO', 'ACEVEDO', 'ALEGRE', 1, '06952452', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA ROSA', 'ACUÑA', 'VDA. DE ROJAS', 1, '25427159', 1, 'LAS ORQUIDEAS MZ F LT 28 CALLAO', '', '912869815', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROLANDO', 'ADOLFO', 'SALVADOR', 1, '44444093', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ANTONIA MAURA', 'APAZA', 'MAMANI', 1, '25623157', 1, '', '5385305', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ANTONIA', 'APCHO', 'LOAYZA', 1, '08619817', 1, 'ASOC VIV STA MARIA DEL VALLE III ETAPA MZ D LOTE 16 - SMP', '', '940362592', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ADRIANA', 'APOLINO', 'QUISPE', 1, '25471913', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROLANDO', 'AQUISE', 'HUANCA', 1, '25759465', 1, '', '', '997256543', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROLANDO', 'AQUISE', 'HUANCA', 1, '25759465', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SANTA MAGNA', 'ARANA', 'FLORION', 1, '25801311', 1, 'ASOC LOS DOMINICOS DE SANTA ROSA ETAPA I MZ D LOTE 11 - SMP', '', '940453132', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ELEUTERIA', 'ARANGO', 'MENDIETA', 1, '25803121', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA', 'ARAUJO', 'DIAZ', 1, '10118340', 1, 'AV SANTA ROSA MZ A LOTE 19 - ASOC. STA. ROSITA - SMP', '5752051', '983449683', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JUSTINA', 'AYMA', 'DE CLEMENTE', 1, '06141598', 1, 'URB. EL ALAMO MZ Y LOTE 11 - CALLAO', '5755437', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('DULIA', 'AYMARA', 'BARRIENTOS', 1, '09956856', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROSAURA YOLANDA', 'BALCONA', 'BALCONA', 1, '10764765', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MERCEDES ZORAIDA', 'BALDEON', 'TOLEDO', 1, '25640499', 1, 'PASAJE AREQUIPA 115 CARMEN DE LA LEGUA CALLAO', '', '991208801', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('LUIS ALBERTO', 'BARRIAL', 'CORIMANYA', 1, '40228666', 1, 'MZ B LT09 LOS PINOS SANTAROSA - SMP', '4484775', '994718328', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARLENY FAUSTINA', 'BARRUETO', 'RODRIGUEZ', 1, '16479882', 1, 'CALLE JORGE COLQUE 195, URB SAN JUAN MASIAS - CALLAO', '', '986307664', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('TEODORICO ENRIQUE', 'BAUTISTA', 'BAUTISTA', 1, '25861159', 1, 'URB LA ENCANTADA DE SANTA ROSA SMP MZ A LOTE 8 - SMP', '', '993630989', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ULISES EDILBERTO', 'BAUTISTA', 'RUIZ', 1, '27569708', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SILVIA', 'BEATRIZ', 'QUIQUE', 1, '06085298', 1, 'MZ F LT 13 ASOC MONTECARLO II ETAPA - SMP', '', '982948227', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CIRILO IGNACIO', 'BELLIDO', 'LEON', 1, '25564036', 1, '', '', '951717235', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA', 'BENITES', 'CONDOR', 1, '06874948', 1, '', '', '998668281', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('BERTHA', 'BLANCO', 'DE GUADALUPE', 1, '25420082', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARLENY', 'BLANCO', 'MARIN', 1, '42785616', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ESTEFIA', 'CABEZAS', 'INTUSCA', 1, '25460465', 1, 'MZ D T 6 URB AS FRESAS - CALLAO', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JUAN', 'CACERES', 'CHIPA', 1, '08686028', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CIPRIANO', 'CAHUANA', 'GUTIERREZ', 1, '09023012', 1, 'URB EL ALAMO MZ A LOTE 31 - CALLAO', '', '991388353', 'CIPRIANOCAHUANA.49@GMAIL.COM', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('DAVID', 'CAMPOS', 'APCHO', 1, '40694315', 1, 'URB. SAN JUAN MASIAS MZ N LOTE 38 PSJ. PIURA CALLAO', '5771635', '995231868', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('DORA', 'CAMPOS', 'BRICEÑO', 1, '25634676', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CECILIA', 'CAMPOS', 'CUELLAR', 1, '09079879', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('TIMOTEO MARDONIO', 'CANCHARI', 'SEDANO', 1, '20683866', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CECILIA', 'CARPIO', 'HUAHUALUQUE', 1, '25570132', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SERGIO', 'CARRILLO', 'CONDORI', 1, '25754773', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CESAR', 'CARRILLO', 'VALDIVIA', 1, '41192100', 1, '', '5746853', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('YANETT', 'CARRION', 'ALVAREZ', 1, '25863429', 1, 'URB LAS BEGONIAS DE SANTA ROSA LOTE D 31 - SMP', '5754671', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CLARA', 'CARRIZALES', 'LIPA', 1, '25575703', 1, '', '', '994270013', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('GRISELDA ELBA', 'CASTILLO', 'SALDAMANDO', 1, '25649002', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('EUFEMIA', 'CATALAN', 'BAZAN', 1, '40816678', 1, '', '', '959135157', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('EMPERATRIZ', 'CCOLQUE', 'CCAYA', 1, '09218087', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('PERCY', 'CHALCO', 'VARGAS', 1, '40188735', 1, 'CALLE LOS ROSALES ASOC. LAS CASUARINAS  DE SANTA ROSA MZ A LOTE 2 - SMP', '', '942664131', 'GRUPOCHALLCO@HOTMAIL.COM', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('BENIGNA', 'CHAMANA', 'DE ALVA', 1, '08947714', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('BERNARDO', 'CHAMBILLA', 'CHOQUE', 1, '01814252', 1, 'URB. LOS NISPEROS MZ E LOTE 2A - SMP', '', '989145963', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MANUEL', 'CHAVEZ', 'RIQUELME', 1, '08524134', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JULIA', 'CONDE', 'PARIONA', 1, '41725750', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CARMEN ROSA', 'CHIUYARE', 'MENDOZA', 1, '1111111', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MONICA', 'CONDO', 'DE LA CRUZ', 1, '40749877', 1, 'URB JOSE BOTERIN MZ C2 LOTE 8 - CALLAO', '4650807', '999072234', 'MCONDO3030@GMAIL.COM', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('VILMA', 'CONDORI', 'HUAMARAIME', 1, '41580866', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARCELINA', 'CONDORI', 'QUISPE', 1, '25454784', 1, 'LOS PORTALES DE SANTA ROSA MZ A LT 13 - SMP', '5741585', '998303057', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SUSANA EUATAQUIA', 'CONDORI', 'HUAMAN', 1, '40292460', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SEGUNDINA', 'CONDORI', 'HUAMANRAYME', 1, '09030735', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA LUZ', 'CORDOVA', 'DE CCAHUANA', 1, '25464267', 1, 'URB. PREVI MZ 31 LOTE 6 II ETAPA - PREVI CALLAO', '', '994444688', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CECILIA ISABEL', 'CORNEJO', 'TORPOCO', 1, '44360177', 1, 'ASOC. SAN VALENTIN I ETAPA  MZ A LOTE 7 - SMP', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CARLOS DANILO', 'CRUZADO', 'CUEVA', 1, '27989489', 1, 'ASOC. LOS OLIVOS DE SAN VICENTE MZ D LOTE 26', '5749300', '997432657', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('VICTORIA', 'CUADROS', 'GUTIERREZ', 1, '06907963', 1, 'URB. EL ALAMO MZ B LOTE 2 - CALLAO', '', '959312660', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('TEOFILA', 'CUELLAR', 'NINAMANCO', 1, '25494177', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JUAN', 'CUMPA', 'ARROYOL', 1, '25742846', 1, '', '5752169', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('GUSTAVO HERMINIO', 'CURI', 'JUAREZ', 1, '25744016', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SATURNINO', 'CUTIPA', 'LUQUE', 1, '25528553', 1, 'ASOC DE VIV LOS NISPEROS MZ N LOTE 10 - SMP', '', '995078241', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('EMILIANA', 'CUTIPA', 'LUQUE', 1, '25411993', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('BERTHA', 'DAMIAN', 'QUISPE', 1, '25846508', 1, 'AV. PACASMAYO MZ B1 LOTE 07 - EL ALAMO - CALLAO', '', '992569996', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ANGELICA PRIMITIVA', 'DAVILA', 'FLORES', 1, '08531835', 1, 'ASOC LAS CASUARINAS MZ G LOTE 17 - SMP', '4844466', '991455289', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ALBINO ISIDRO', 'DE LA CRUZ', 'RAMOS', 1, '09903097', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ADELA', 'DE LA CRUZ', 'RAMOS', 1, '08621574', 1, '', '4536129', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('AGRIPINA LUCY', 'DE LA CRUZ', 'BONIFACIO', 1, '09931805', 1, 'URB. BELLO HORIZONTE MZ.I LT 09 -C0MAS', '5573201', '988069446', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JULIO CESAR', 'DELGADILLO', 'NINANYA', 1, '1111112', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MIRIAM ELIZABETH', 'DELGADILLO', 'NINANYA', 1, '20436427', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('GREGORIO', 'DENOS', 'LUNA', 1, '09276706', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROSA MARUJA', 'DEZA', 'DE CARHUATANTA', 1, '25641480', 1, 'MZ H LOTE 01 ASOC. VIV.  MI TERRUÑO - SMP', '4844189', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('DAISE NOEMI', 'DIAZ', 'FLORES', 1, '41878213', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ADRIANA', 'DIAZ', 'HUAMANCHAY', 1, '10135707', 1, 'LA ENCANTADA DE SANTA ROSA MZ A LOTE 27 - SMP', '', '942521885', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('DAISI NOEMI', 'DIAZ', 'FLORES', 1, '41878213', 1, 'PRO.VIVIENDA LOS JARDINES DE SANTA ROSA ETAP.11 MZ. D LT 18 SAN MARTIN DE PORRES', '5058921', '950407761', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('RICARDO', 'DOMINGUEZ', 'FLORENCIO', 1, '25421136', 1, '', '', '961101938', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CESAR ALCIDES', 'DURAN', 'SANTAMARIA', 1, '42059946', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('YANETH YUDITH', 'DURAN', 'TAQUIO', 1, '43308690', 1, ' MZ M LOTE 28 ASOC. VIV. CHICMABAMBA - SMP', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ALICIA', 'ECHACCAYA', 'GUTIERREZ', 1, '40190628', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARCIANO ANTONIO', 'EGUSQUIZA', 'MATOS', 1, '25558989', 1, 'MZF. LT 40 ASOC. VIVIENDA .RESID CALIFORNIA SAN MARTIN DE PORRES', '5745719', '947494451', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ANTONIO JULIO', 'ELESCANO', 'DE LA CRUZ', 1, '40105907', 1, '', '', '997708849', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('LUZMILA', 'ESCALANTE', 'DE TORRES', 1, '25461452', 1, 'CALLE LAS DALIAS MZ G LOTE 37 JOSE BOTTERIN - CALLAO', '4201622', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('YOLANDA ELISA', 'FELIX', 'MURGA', 1, '06191481', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ZOILA', 'FERRARI', 'ZEVALLOS', 1, '10124768', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('PAMELA KERLY', 'FLORES', 'FLORES', 1, '71528771', 1, 'AV VENEZUELA ASENT. H. LOS CEDROS MZ I LOTE 15 - VENTANILLA', '', '918981508', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARTIN JULIO', 'FLORES', 'RAMOS', 1, '1111113', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA LUISA', 'GABANCHO', 'LOAYZA', 1, '25559664', 1, 'LA ALBORADA MZ B LOTE 16 II ETAPA - SMP', '', '943521830', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('NANCY DEMETRIA', 'GAMBOA', 'NAJARRO DE FELIPE', 1, '09008027', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIBEL ROSARIO', 'GAMBOA', 'PALOMINO', 1, '25718314', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ANGELICA', 'GARCIA', 'MINAYA DE OSORIO', 1, '25432052', 1, 'GAMBETA BAJA ESTE, SECTOR II MZ A2 LOTE 8 - CALLAO', '4294012', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ANTONIO MERCEDES', 'GARCIA', 'PRETEL', 1, '06254991', 1, 'URB PALERMO MZ Q LOTE 27 - LIMA', '', '996413599', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ANGELICA', 'GARCIA', 'MINAYA', 1, '25432052', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('FRANCISCA DONATA', 'GARCIA', 'PRUDENCIO', 1, '25862001', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CLEMENCIA ALEJANDRINA', 'GARCIA', 'SILVA', 1, '25709060', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('VICTOR', 'GODOY', 'RAMOS', 1, '41071349', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('EUFEMIA NANCY', 'GOMEZ', 'TOLENTINO', 1, '25806446', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA LUZMILA', 'GOMEZ', 'SAUÑE', 1, '25416700', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA CATALINA', 'GONZALES', 'PROLEON', 1, '25781340', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MIRELA RAQUEL', 'GONZALES', 'LARA', 1, '41550720', 1, 'ASENT. H. LOS OLIVOS DE SAN VICENTE MZ A LOTE 18 - SMP', '', '949512316', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('GLADYS SALINAS', 'GONZALES', 'BENITO', 1, '09360855', 1, 'JR.JOSE QUIÑONEZ 371 URB. VALDIVIEZO - ATE', '3260852', '999238223', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SARA MARITZA', 'GONZALES', 'FLORES', 1, '41686882', 1, 'URB. BRAYSON MZ W LOTE 18 - SMP', '', '977867414', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('LILIANA MARUJA', 'GONZALEZ', 'CABELLOS', 1, '07498388', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SANDRA MAGALY', 'GONZALEZ', 'CABELLOS', 1, '41205819', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MANUEL', 'GOÑAS', 'DAZA', 1, '25815950', 1, '', '7669933', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ADALINO ANTONIO', 'GRON', 'ECHEVARRIA', 1, '08667129', 1, '', '', '958448158', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('DONATO', 'GUADALUPE', 'ROJAS', 1, '25756590', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROMELIO', 'GUEVARA', 'DIAZ', 1, '10506560', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROSALYN', 'GUIZADO', 'RAMIREZ', 1, '41044641', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CHAVELA FAUSTA', 'HERRERA', 'MATOS', 1, '40847195', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ASUNCIONA', 'HERRERA', 'ROMERO', 1, '06919105', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('NINFA FANA', 'HERRERA', 'MATOS', 1, '25500103', 1, 'MZ G LOTE 05 URB LA QUILLA - CALLAO', '', '979024451', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA', 'HUAMAN', 'QUISPE DE HUAMANI', 1, '07394986', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ANTONIETA', 'HUAMAN', 'PELAEZ', 1, '08666278', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ANA MARIA', 'HUAMAN', 'ATAYPOMA', 1, '07138061', 1, '', '', '995169611', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ANNABELLA', 'HUAMAN', 'MORENO', 1, '25712957', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('PEDRO', 'HUAMANI', 'RODRIGUEZ', 1, '08341606', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('EVELY KARINA', 'HUAROMO', 'FIGUEROA', 1, '43154608', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('DIN MARIA', 'ICHACCAYA', 'TOLEDANO', 1, '25755438', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('LIDIA NORMA', 'IGNACIO', 'CCENCHO', 1, '06839141', 1, '', '', '993122259', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CINTHIA PATRICIA', 'JARA', 'PAREDES', 1, '45218965', 1, 'URB LOS CIPRESES MZ W LOTE 15 - SMP', '5748945', '990726064', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARUJA GUADALUPE', 'JARA', 'REGALADO', 1, '10616424', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARTINA', 'JIHUAÑA', 'MAMANI', 1, '25567592', 1, 'MZ J LOTE 14 ASOC. LAS CASUARINAS DE SANTA ROSA - SMP', '5747823', '932755059', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARGARITA', 'JIHUAÑA', 'CHAMBI', 1, '07750474', 1, 'ASOC. CHICMABAMBA MZ A LT 3 TERCERA ETAPA', '', '973282802', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('GUILLERMINA', 'JIHUAÑA', 'MAMANI', 1, '01860292', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JESUS PAULINO', 'JURADO', 'HUAMANCAYO', 1, '25457507', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA EMILIANA', 'LAGUA', 'RIMEY', 1, '32026777', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('FRANCISCA', 'LARICO', 'COLQUEHUANCA', 1, '07318692', 1, 'MZ C LOTE 21 PRADRERAS DE NARANJAL- SMP', '', '952242466', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('YOLANDA', 'LARICO', 'COLQUEHUANCA', 1, '07568573', 1, 'MZ D LOTE 24 URB SAN VALENTIN I ETAPA - SMP', '', '975198793', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CLAUDIO', 'LAURA', 'PERALTA', 1, '10104658', 1, '', '', '998729841', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('TOMASA FELICITA', 'LAZARO', 'CASTRO', 1, '25428542', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('NANCY YRENE', 'LEON', 'SALAS', 1, '08009213', 1, 'CALLE LAS AMAPOLAS 168, URB EL PALMAR - SURCO', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CELESTINA LUZMILA', 'LEON', 'JERONIMO', 1, '25849460', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JUAN MAXIMILIANO', 'LEYVA', 'CHINCHAY', 1, '25814800', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('TEOFILO', 'LIMACHE', 'HUALLPA', 1, '08334409', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('GUMERCINDA', 'LIPA', 'ALVAREZ', 1, '01551318', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ISABEL', 'LIPA', 'CARRIZALES', 1, '25803300', 1, 'MZ C LT 09 URB. SANTA FE - VENTANILLA- CALLAO', '', '993031757', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CALIXTO', 'LIPE', 'CARRIZALES', 1, '25803119', 1, 'ASOC. PROP 7 DE AGOSTO MZ I LOTE 8 - CALLAO', '', '939573431', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JUANA', 'LLANO', 'FERNANDEZ', 1, '42328390', 1, 'PRO VIV LOS OLIVOS DE SAN VICENTE MZ A LOTE 4 - SMP', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('VILMA VIRGILIA', 'LLANTO', 'HUAROMO', 1, '42565492', 1, 'URB. FILADELFIA 1 ETAPA MZ. G LT. 28 - SMP', '', '961928939', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARILU', 'LOAYZA', 'OCHOA', 1, '25756525', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CLAUDIA', 'LOPEZ', 'HINOSTROZA', 1, '25646055', 1, 'PASAJE 16 DE OCTUBRE MZ F2 LOTE 23 AAHH BOCANEGRA - CALLAO', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CIRO', 'LOPEZ', 'HINOSTROZA', 1, '25632398', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MERY LUZ', 'LUNA', 'BEYZAGA', 1, '08547405', 1, 'URB. LOS ROSALES MZ B LOTE 11 - SMP', '5741062', '989135464', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('EUSEBIA', 'LUQUE', 'APAZA', 1, '25473902', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('BENIGNA', 'LUQUE', 'CHOQUEHUANCA', 1, '07701761', 1, 'ASOC VIV CHICMABAMBA MZ G LT 13 SPM ', '5751994', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JUANA', 'LUQUE', 'CHURA', 1, '25816125', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('VIRGINIA', 'LUQUE', 'CUTIPA', 1, '41201511', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JOSE CONCEPCION', 'LUQUE', 'QUISPE', 1, '25455019', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('GLADYS', 'MADERA', 'QUISPE', 1, '08662364', 1, 'CALLE 14 ASOC SAN FRANCISCO DE CAYRAN MZ D1 LT 23 - SMP', '', '920618655', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('LOURDES', 'MADERA', 'QUISPE', 1, '10453024', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ALEJANDRO ADAN', 'MALASQUEZ', 'HUAMAN', 1, '25471772', 1, '', '', '989564146', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CARMELA', 'MALCA', 'VIVANCO', 1, '08631884', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('FELICITAS', 'MALLQUI', 'MARTEL', 1, '1111114', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROSA', 'MAMANI', 'LIPE', 1, '25568112', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARUJA', 'MAMANI', 'CONDORI', 1, '25569605', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CLEMENTINA', 'MARTINEZ', 'BERROCAL DE TENORIO', 1, '25808994', 1, 'URB. EL ALAMO MZ D-01 LOTE 18', '', '949150279', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MAGALI', 'MAYORGA', 'DE LEON', 1, '10161930', 1, 'ASOC VIVIENDAS CHICMABAMBA MZ H LOTE 2 - SMP', '5750048', '947227322', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA CARMEN', 'MAYTA', 'MENDOZA', 1, '25815003', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ORLANDO', 'MELENDEZ', 'TAFUR', 1, '08581767', 1, 'CALLE 6 MZ P LOTE 28 URB SANTA ELISA 1ERA ETAPA - LOS OLIVOS', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('VIOLETA', 'MENDIETA', 'PADILLA', 1, '07880157', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('VILMA MARY', 'MILLA', 'COTRINA DE CARRILLO', 1, '06710965', 1, 'URB EL ALAMO MZ V LOTE 5 - CALLAO', '', '990052776', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JULIA AIDA', 'MONTALVO', 'SAENZ', 1, '08515000', 1, 'PSJ. LOS MILAGROS 558 DPTO. 402 CONJ. HAB. CIUDAD SATELITE SANTA ROSA', '5741852', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JUSTA', 'MORALES', 'MARIÑO', 1, '31155166', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('BERNARDO DARIO', 'MORENO', 'QUESADA', 1, '08458649', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SELENE', 'MORETTI', 'VEGA DE VASQUEZ', 1, '42748818', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JOSE LUIS', 'MORIANO', 'FERNANDEZ', 1, '43320162', 1, '1RO DE MAYO MZ AB LT02 INDEPENDENCIA', '', '975606059', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JACINTA', 'NAJARRO', 'SOLIS', 1, '25494719', 1, 'URB. EL ALAMO MZ D1 LT 23 - CALLAO', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SATURNINA', 'OCHOA', 'ROJAS', 1, '25450370', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MELANY CONSUELO', 'OCON', 'ROMERO', 1, '10678775', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('GLADYS FELICITA', 'OLIVARES', 'SEGUNDO', 1, '25759218', 1, 'URB. EL ALAMO  MZ D1 LOTE 4 - CALLAO', '', '940777669', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CESAR GUSTAVO', 'OLORTEGUI', 'MASGO', 1, '46854867', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('VICTOR ELADIO', 'OLORTEGUI', 'LLASHAG', 1, '25487807', 1, 'URB. LAS 200 MILLAS MZ J LOTE 32 - CALLAO', '', '992338726', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JUANA', 'ORE', 'FLORES', 1, '25505679', 1, 'ASOC. LAS COLINAS AV LOS DOMINICOS MZ N LOTE 11', '', '974512192', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('WILFREDO ADRIAN', 'PACHECO', 'CARRILLO', 1, '08158721', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ALEJANDRINA', 'PAJUELO', 'GARCIA DE ALVARADO', 1, '07956441', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JORGE LUIS', 'PALOMINO', 'CHAVEZ', 1, '09362503', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('EVARISTA ERNESTINA', 'PALOMINO', 'LLASACA', 1, '25713056', 1, '', '', '946056070', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('FELISA', 'PALOMINO', 'ECHACCAYA DE RAMIREZ', 1, '25810529', 1, 'CALLE EL OLIVAR MZ K LOTE 34 URB LAS FRESAS - CALLAO', '4875561', '997059314', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SARA MAGDALENA', 'PALOMINO', 'GRADOS', 1, '22486967', 1, 'URB. LOS LIRIOS MZ D LOTE 3 - CALLAO', '4915226', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('NORKA MARGOT', 'PAREDES', 'PAREJA', 1, '10621029', 1, 'URB. LOS CIPRESES MZ W LOTE 15 - SMP', '5748945', '995588705', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ALICIA YOLANDA', 'PARICAHUA', 'QUISPE', 1, '25806123', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('TEODOSIO', 'PARIONA', 'VILCA', 1, '25496207', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('DILMER', 'PASAPERA', 'CARAMANTIN', 1, '25510868', 1, 'ASOC. LOS CHASQUIS MZ C LOTE 3 - SMP', '7596732', '986613108', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MILAGROS', 'PASTOR', 'CUADROS', 1, '25570667', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CARMEN', 'PAUCAR', 'GUTIERREZ', 1, '25815298', 1, '', '', '980260080', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROCIO DEL PILAR', 'PAZ', 'CARLOS', 1, '25750617', 1, 'URB AEROPUERTO MZ G LOTE 18 (AV. QUILCA 214) - CALLAO', '', '997875427', 'CHIO_PC05@HOTMAIL.COM', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIO', 'PEREZ', 'QUISPE', 1, '25832368', 1, 'AV. PACASMAYO URB ALBORADA DE SANTA ROSA ETAPA 3 MZ A LOTE 5 - SMP', '4333030', '981349785', 'MIJU_65@HOTMAIL.COM', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CIRILO', 'PEREZ', 'CAMARGO', 1, '25474849', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIBEL ROSARIO', 'PEREZ', 'LLANGE', 1, '06083761', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('AVILIA', 'PICOY', 'RIVERA', 1, '04071822', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('GLORIA ANTONIA', 'PICOY', 'RIVERA', 1, '04081660', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('LUCIA MEZARINA', 'PINEDO', 'DE COLLANTES', 1, '25513210', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('FELICITA', 'PRETEL', 'GUTIÉRREZ', 1, '06149925', 1, 'P. JOVEN 1ERO SETIEMBRE MZ S LOTE 12 - LIMA', '', '988320001', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('VENIGNA', 'PRETEL', 'GUTIERREZ', 1, '08936257', 1, 'DEADELFA 1 ETAPA MZ C LOTE 24 - SMP', '5747339', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('RICHARD', 'PUJADAS', 'SARMIENTO', 1, '25717974', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JORGE LUIS', 'PUMACAYO', 'PACHECO', 1, '45798170', 1, '', '6558267', '940464724', 'JORGELPP6@GMAIL.COM', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SILVIA BEATRIZ', 'QUIQUE', 'TANTA DE MORENO', 1, '06085298', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('OLGA', 'QUISPE', 'VERASTEGUI', 1, '09442848', 1, 'CALLE EL OLIVAR URB LA QUILLA MZ C LOTE 16 - CALLAO', '6571670', '963316561', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('WILSON', 'QUISPE', 'QUISPE', 1, '46420779', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MANUEL', 'QUISPE', 'CALLATA', 1, '08450662', 1, 'LOS LIRIOS MZ E LOTE 4 - SMP', '7753801', '970808435', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ALEXANDER ARMANDO', 'QUISPE', 'CACERES', 1, '25717754', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('PRIMITIVO', 'QUISPE', 'GUTIERREZ', 1, '10457521', 1, ' MZ M LOTE 28 ASOC. VIV. CHICMABAMBA - SMP', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('PAUL MILTON', 'QUISPE', 'HUAMANI', 1, '10537321', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CELSA', 'QUISPE', 'PINELO', 1, '09896246', 1, '', '', '997923510', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('FRANCISCO', 'QUISPE', 'MAMANI', 1, '25813991', 1, 'MZ H LOTE 16  III ETAPA -  LOS JAZMINES - CALLAO', '', '934667054', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('PRIMITIVO', 'QUISPE', 'GUTIERREZ', 1, '10457521', 1, ' MZ M LOTE 28 ASOC. VIV. CHICMABAMBA - SMP', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('RODRIGO', 'QUISPE', 'GUTIERREZ', 1, '25479237', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ALFREDO', 'QUISPE', 'SAYAS', 1, '25783394', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('LIDIA TEOFANES', 'QUISPE', 'CHIPANA', 1, '25562398', 1, 'URB LAS FRESAS MZ K LOTE 16 - CALLAO', '5023581', '995677843', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('RODRIGO', 'QUISPE', 'GUTIERREZ', 1, '10494931', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('PRIMITIVO', 'QUISPE', 'GUTIERREZ', 1, '10457521', 1, ' MZ M LOTE 28 ASOC. VIV. CHICMABAMBA - SMP', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ALEJANDRO', 'QUITO', 'APARICIO', 1, '32026762', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JULIA ISABEL', 'RAMOS', 'ROJAS', 1, '41635386', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('DIONICIA LEONARDA', 'RAMOS', 'LOPEZ', 1, '15344309', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SANDRA GISELA', 'REYES', 'SOTO', 1, '1111115', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('FELIPE MAXIMO', 'RIOS', 'MAYTA', 1, '06036267', 1, 'P. JOVEN 1ERO SETIEMBRE MZ S LOTE 12 - LIMA', '', '958669998', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARGARITA', 'RIOS', 'MAYTA', 1, '08056236', 1, 'PSJE 3 MZ S LOTE 12 P. JOVEN  1ERO DE SETIEMBRE', '4844296', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ANA KARINA', 'RIOS', 'PRETEL', 1, '42805248', 1, 'P. JOVEN 1ERO SETIEMBRE MZ S LOTE 12 - LIMA', '', '999006879', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROCIO', 'RIOS', 'MANTARI', 1, '40654009', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('REYNA ISABEL', 'RIOS', 'DE CASTELLANO', 1, '09642529', 1, '', '', '942441213', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ELEODORO HUMBERTO', 'RIVERA', 'ZACARIAS', 1, '43310669', 1, 'ASOC. VIVIENDA CHICMABAMBA III ETAPA MZ L LOTE 2 - SMP', '4840375', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CARMEN ROSA', 'RIVERA', 'DE VILLON', 1, '25516735', 1, '', '', '992634302', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CLEVER', 'RIVERA', 'PRETEL', 1, '10443159', 1, 'DEADELFA 1 ETAPA MZ C LOTE 24 - SMP', '', '921070297', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('LUCILIA CECILIA', 'ROJAS', 'LEON', 1, '07580847', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CARMELITA', 'ROSALES', 'DAZA', 1, '22880250', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('RODOLFO', 'ROSALES', 'ROQUE', 1, '1111116', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JUANA PERSEVERANDA', 'RUIZ', 'BLAS', 1, '10185466', 1, 'URB. PROVIV. LOS ALISOS DE OQUENDO ETAPA II MZ A LOTE 23', '', '945302320', 'JOSE_292008@HOTMAIL.COM', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('BALVINA', 'RUIZ', 'RIOS', 1, '07169864', 1, 'AV. PRINCIPAL  MZ I LOTE 21 URB. EL ALAMO - CALLAO', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ELIZABETH MARICELA', 'SALAZAR', 'CAJACHAGUA', 1, '21263715', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CATY', 'SALAZAR', 'LAZO', 1, '09855585', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('DIANA MARILYN', 'SALDAÑA', 'CONDORI', 1, '41412755', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('KARIN VANESSA', 'SALINAS', 'SOLIS', 1, '41316275', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ARTURO', 'SALVADOR', 'PABLO', 1, '09457391', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('FANNY', 'SANCHEZ', 'TIMANA', 1, '1111117', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JULIA MARTHA', 'SANCHEZ', 'ROQUE', 1, '40017830', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA NELIDA', 'SANCHEZ', 'BURGA', 1, '41302727', 1, 'URB VENECIA  MZ F LOTE 26 - SMP', '', '949229861', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('FLOR GIECELA', 'SANCHEZ', 'POMA', 1, '1111118', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SIMEONA', 'SARMIENTO', 'DE PUJADAS', 1, '06695761', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA ELENA', 'SAYAS', 'ESPINOZA DE QUISPE', 1, '25517461', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('VALENTINA', 'SILVA', 'DE CUARESMA', 1, '25432987', 1, 'URB LOS OLIVOS DE SAN VICENTE MZ A LOTE 19 - SMP', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('BELISARIO', 'SOCA', 'ARANGO', 1, '25802023', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('HERLINDA', 'SOTO', 'TAIPE', 1, '25848299', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('BARBARITA SANTOS', 'SOTO', 'MENDOZA', 1, '10457682', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JOSEFINA', 'TACUSI', 'CCORIMANYA', 1, '09275885', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MIRNA', 'TAFUR', 'TAFUR', 1, '33949997', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('NILDA', 'TAFUR', 'TAFUR', 1, '10451512', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROSA LUZ', 'TARAZONA', 'MANRIQUE', 1, '40423222', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MAURA', 'TENORIO', 'OLANYA', 1, '25743636', 1, 'URB MI TERRUÑO MZ L LOTE 14 - SMP', '', '946850346', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('FRANCISCO', 'TICONA', 'ALVAREZ', 1, '25482090', 1, 'URB RAMON CASTILLA MZ I LOTE 8 - CALLAO', '7241583', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ARCELA DOMINGA', 'TITO', 'UCHPAS DE LA CRUZ', 1, '10458320', 1, 'AAHH SANTA ROSA DE PIEROLA MZ A LOTE 3 - CALLAO', '', '934324810', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ALFREDO', 'TOLEDANO', 'CUIBA', 1, '25845169', 1, 'EX FUNDO OQUENDO KM8 CARRETERA VENTANILLA - CALLAO', '', '991203137', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('REYNA ISABEL', 'TORO', 'CORONEL', 1, '25407349', 1, 'BENJAMIN DOY 2777 EL PACIFICO SAN MARTIN DE PORRES', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('LUCILA ENGELICA', 'TORPOCO', 'BARRANTES', 1, '07154854', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('CEFERINA MARCELINA', 'TORRES', 'MONTAÑEZ', 1, '25555705', 1, 'ASOC PROP OLIVOS DE SANTA ROSA MZ H LOTE 20 - CALLAO', '5756311', '913650954', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('DOMINGA SUSANA', 'TORRES', 'MONTAÑÉZ', 1, '25455307', 1, 'URB LOS ALAMOS MZ A LOTE 14 - CALLAO', '5756311', '970507173', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JASSON ADRIAN', 'TRAUCO', 'PAREDES', 1, '45834682', 1, 'CALLE SANTA ROSA URB. LOS CIPRESES MZ W LOTE 15 - SMP', '', '958408694', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('TEOFILO', 'TRUJILLO', 'LEON', 1, '22880080', 1, 'ASOC SANTA ISABELL MZ A LT 30 SMP', '', '974678677', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('ROSA ELVIRA', 'TUNGA', 'SANTISTEBAN', 1, '17543276', 1, 'LOS OLIVOS DE SAN VICENTE MZ C LOTE 7 - SMP', '', '999152527', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA', 'UNTUL', 'BURGOS', 1, '07333973', 1, '', '', '931198676', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JUANA', 'VARGAS', 'VARGAS', 1, '25330732', 1, 'URB. LAS FRESAS MZ N LOTE 06', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JOSE LUIS', 'VASQUEZ', 'GUTTI', 1, '41039584', 1, 'MZ D LOTE 08 LLOS ROSALES DE SANTA ROSA- SMP', '', '980646930', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JORGE LUIS', 'VELASQUEZ', 'PARDO', 1, '15425017', 1, 'URB STA APOLONIA MZ L LOTE 11 - SMP', '5720825', '920828333', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JESUS ORLANDO', 'VELASQUEZ', 'RAMIREZ', 1, '25561137', 1, 'AV. JOSE OLAYA MZ C LOTE 06 AA.HH PIEDRA LIZA TABOADA - CALLAO', '', '993768984', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('SABINA', 'VELASQUEZ', 'ROMERO', 1, '06572873', 1, '', '', '977376150', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MANUELA', 'VILCA', 'YANAPA', 1, '09904563', 1, '', '', '', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARTA ANTONIA', 'YAURI', 'GARCIA', 1, '10453205', 1, 'CALLE 4 MZ.A LT. 20 ASOC.LOS GIRASOLES DE SANTA ROSA 2DA ETAP SAN MARTIN DE PORRES.', '5747277', '972392461', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('EMMA DORA', 'YAURICASA', 'CHOQUE', 1, '25416356', 1, 'AV PASEO LOS FRANCISCANOS 402 DPTO 402 - CIUDAD SATELITE STA ROSA - CALLAO', '4840192', '942187765', 'EDYCH61@HOTMAIL.COM', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('MARIA RICARDINA', 'ZAQUINAULA', 'GOMEZ', 1, '06842325', 1, 'URB SAN REMO MZ A LOTE 15 - SMP', '', '992915865', '', 2, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('PROPIEDAD ASOCIACION', ' ', ' ', 1, '9999992', 1, '', '', '', '', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('LUIS PAULO', 'JAVE', 'CARITAS', 1, '70498682', 1, 'CALLE LOS LIRIOS MZ A LT 23 URB. FILADELFIA 2DA ETAPA - SMP', '4304588', '987814050', 'PAULO.JAVE.CARITAS@GMAIL.COM', 1, 1);
insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('JESSICA LIZBETH', 'JAVE', 'CARITAS', 1, '42911900', 1, 'CALLE LOS LIRIOS MZ A LT 23 URB. FILADELFIA 2DA ETAPA - SMP', '', '982402819', 'PAULO.JAVE.CARITAS@GMAIL.COM', 2, 1);


/*
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (1, 'ROSENDO', 'ACEVEDO', 'ALEGRE', 1, '06952452', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (2, 'MARIA ROSA', 'ACUÑA', 'VDA. DE ROJAS', 1, '25427159', 1, 'LAS ORQUIDEAS MZ F LT 28 CALLAO', '', '912869815', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (3, 'ROLANDO', 'ADOLFO', 'SALVADOR', 1, '44444093', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (4, 'ANTONIA MAURA', 'APAZA', 'MAMANI', 1, '25623157', 1, '', '5385305', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (5, 'ANTONIA', 'APCHO', 'LOAYZA', 1, '08619817', 1, 'ASOC VIV STA MARIA DEL VALLE III ETAPA MZ D LOTE 16 - SMP', '', '940362592', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (6, 'ADRIANA', 'APOLINO', 'QUISPE', 1, '25471913', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (7, 'ROLANDO', 'AQUISE', 'HUANCA', 1, '25759465', 1, '', '', '997256543', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (8, 'ROLANDO', 'AQUISE', 'HUANCA', 1, '25759465', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (9, 'SANTA MAGNA', 'ARANA', 'FLORION', 1, '25801311', 1, 'ASOC LOS DOMINICOS DE SANTA ROSA ETAPA I MZ D LOTE 11 - SMP', '', '940453132', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (10, 'ELEUTERIA', 'ARANGO', 'MENDIETA', 1, '25803121', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (11, 'MARIA', 'ARAUJO', 'DIAZ', 1, '10118340', 1, 'AV SANTA ROSA MZ A LOTE 19 - ASOC. STA. ROSITA - SMP', '5752051', '983449683', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (12, 'JUSTINA', 'AYMA', 'DE CLEMENTE', 1, '06141598', 1, 'URB. EL ALAMO MZ Y LOTE 11 - CALLAO', '5755437', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (13, 'DULIA', 'AYMARA', 'BARRIENTOS', 1, '09956856', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (14, 'ROSAURA YOLANDA', 'BALCONA', 'BALCONA', 1, '10764765', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (15, 'MERCEDES ZORAIDA', 'BALDEON', 'TOLEDO', 1, '25640499', 1, 'PASAJE AREQUIPA 115 CARMEN DE LA LEGUA CALLAO', '', '991208801', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (16, 'LUIS ALBERTO', 'BARRIAL', 'CORIMANYA', 1, '40228666', 1, 'MZ B LT09 LOS PINOS SANTAROSA - SMP', '4484775', '994718328', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (17, 'MARLENY FAUSTINA', 'BARRUETO', 'RODRIGUEZ', 1, '16479882', 1, 'CALLE JORGE COLQUE 195, URB SAN JUAN MASIAS - CALLAO', '', '986307664', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (18, 'TEODORICO ENRIQUE', 'BAUTISTA', 'BAUTISTA', 1, '25861159', 1, 'URB LA ENCANTADA DE SANTA ROSA SMP MZ A LOTE 8 - SMP', '', '993630989', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (19, 'ULISES EDILBERTO', 'BAUTISTA', 'RUIZ', 1, '27569708', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (20, 'SILVIA', 'BEATRIZ', 'QUIQUE', 1, '06085298', 1, 'MZ F LT 13 ASOC MONTECARLO II ETAPA - SMP', '', '982948227', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (21, 'CIRILO IGNACIO', 'BELLIDO', 'LEON', 1, '25564036', 1, '', '', '951717235', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (22, 'MARIA', 'BENITES', 'CONDOR', 1, '06874948', 1, '', '', '998668281', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (23, 'BERTHA', 'BLANCO', 'DE GUADALUPE', 1, '25420082', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (24, 'MARLENY', 'BLANCO', 'MARIN', 1, '42785616', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (25, 'ESTEFIA', 'CABEZAS', 'INTUSCA', 1, '25460465', 1, 'MZ D T 6 URB AS FRESAS - CALLAO', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (26, 'JUAN', 'CACERES', 'CHIPA', 1, '08686028', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (27, 'CIPRIANO', 'CAHUANA', 'GUTIERREZ', 1, '09023012', 1, 'URB EL ALAMO MZ A LOTE 31 - CALLAO', '', '991388353', 'CIPRIANOCAHUANA.49@GMAIL.COM', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (28, 'DAVID', 'CAMPOS', 'APCHO', 1, '40694315', 1, 'URB. SAN JUAN MASIAS MZ N LOTE 38 PSJ. PIURA CALLAO', '5771635', '995231868', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (29, 'DORA', 'CAMPOS', 'BRICEÑO', 1, '25634676', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (30, 'CECILIA', 'CAMPOS', 'CUELLAR', 1, '09079879', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (31, 'TIMOTEO MARDONIO', 'CANCHARI', 'SEDANO', 1, '20683866', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (32, 'CECILIA', 'CARPIO', 'HUAHUALUQUE', 1, '25570132', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (33, 'SERGIO', 'CARRILLO', 'CONDORI', 1, '25754773', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (34, 'CESAR', 'CARRILLO', 'VALDIVIA', 1, '41192100', 1, '', '5746853', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (35, 'YANETT', 'CARRION', 'ALVAREZ', 1, '25863429', 1, 'URB LAS BEGONIAS DE SANTA ROSA LOTE D 31 - SMP', '5754671', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (36, 'CLARA', 'CARRIZALES', 'LIPA', 1, '25575703', 1, '', '', '994270013', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (37, 'GRISELDA ELBA', 'CASTILLO', 'SALDAMANDO', 1, '25649002', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (38, 'EUFEMIA', 'CATALAN', 'BAZAN', 1, '40816678', 1, '', '', '959135157', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (39, 'EMPERATRIZ', 'CCOLQUE', 'CCAYA', 1, '09218087', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (40, 'PERCY', 'CHALCO', 'VARGAS', 1, '40188735', 1, 'CALLE LOS ROSALES ASOC. LAS CASUARINAS DE SANTA ROSA MZ A LOTE 2 - SMP', '', '942664131', 'GRUPOCHALLCO@HOTMAIL.COM', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (41, 'BENIGNA', 'CHAMANA', 'DE ALVA', 1, '08947714', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (42, 'BERNARDO', 'CHAMBILLA', 'CHOQUE', 1, '01814252', 1, 'URB. LOS NISPEROS MZ E LOTE 2A - SMP', '', '989145963', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (43, 'MANUEL', 'CHAVEZ', 'RIQUELME', 1, '08524134', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (44, 'JULIA', 'CONDE', 'PARIONA', 1, '41725750', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (45, 'CARMEN ROSA', 'CHIUYARE', 'MENDOZA', 1, '1111111', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (46, 'MONICA', 'CONDO', 'DE LA CRUZ', 1, '40749877', 1, 'URB JOSE BOTERIN MZ C2 LOTE 8 - CALLAO', '4650807', '999072234', 'MCONDO3030@GMAIL.COM', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (47, 'VILMA', 'CONDORI', 'HUAMARAIME', 1, '41580866', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (48, 'MARCELINA', 'CONDORI', 'QUISPE', 1, '25454784', 1, 'LOS PORTALES DE SANTA ROSA MZ A LT 13 - SMP', '5741585', '998303057', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (49, 'SUSANA EUATAQUIA', 'CONDORI', 'HUAMAN', 1, '40292460', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (50, 'SEGUNDINA', 'CONDORI', 'HUAMANRAYME', 1, '09030735', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (51, 'MARIA LUZ', 'CORDOVA', 'DE CCAHUANA', 1, '25464267', 1, 'URB. PREVI MZ 31 LOTE 6 II ETAPA - PREVI CALLAO', '', '994444688', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (52, 'CECILIA ISABEL', 'CORNEJO', 'TORPOCO', 1, '44360177', 1, 'ASOC. SAN VALENTIN I ETAPA MZ A LOTE 7 - SMP', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (53, 'CARLOS DANILO', 'CRUZADO', 'CUEVA', 1, '27989489', 1, 'ASOC. LOS OLIVOS DE SAN VICENTE MZ D LOTE 26', '5749300', '997432657', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (54, 'VICTORIA', 'CUADROS', 'GUTIERREZ', 1, '06907963', 1, 'URB. EL ALAMO MZ B LOTE 2 - CALLAO', '', '959312660', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (55, 'TEOFILA', 'CUELLAR', 'NINAMANCO', 1, '25494177', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (56, 'JUAN', 'CUMPA', 'ARROYOL', 1, '25742846', 1, '', '5752169', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (57, 'GUSTAVO HERMINIO', 'CURI', 'JUAREZ', 1, '25744016', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (58, 'SATURNINO', 'CUTIPA', 'LUQUE', 1, '25528553', 1, 'ASOC DE VIV LOS NISPEROS MZ N LOTE 10 - SMP', '', '995078241', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (59, 'EMILIANA', 'CUTIPA', 'LUQUE', 1, '25411993', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (60, 'BERTHA', 'DAMIAN', 'QUISPE', 1, '25846508', 1, 'AV. PACASMAYO MZ B1 LOTE 07 - EL ALAMO - CALLAO', '', '992569996', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (61, 'ANGELICA PRIMITIVA', 'DAVILA', 'FLORES', 1, '08531835', 1, 'ASOC LAS CASUARINAS MZ G LOTE 17 - SMP', '4844466', '991455289', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (62, 'ALBINO ISIDRO', 'DE LA CRUZ', 'RAMOS', 1, '09903097', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (63, 'ADELA', 'DE LA CRUZ', 'RAMOS', 1, '08621574', 1, '', '4536129', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (64, 'AGRIPINA LUCY', 'DE LA CRUZ', 'BONIFACIO', 1, '09931805', 1, 'URB. BELLO HORIZONTE MZ.I LT 09 -C0MAS', '5573201', '988069446', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (65, 'JULIO CESAR', 'DELGADILLO', 'NINANYA', 1, '1111112', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (66, 'MIRIAM ELIZABETH', 'DELGADILLO', 'NINANYA', 1, '20436427', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (67, 'GREGORIO', 'DENOS', 'LUNA', 1, '09276706', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (68, 'ROSA MARUJA', 'DEZA', 'DE CARHUATANTA', 1, '25641480', 1, 'MZ H LOTE 01 ASOC. VIV. MI TERRUÑO - SMP', '4844189', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (69, 'DAISE NOEMI', 'DIAZ', 'FLORES', 1, '41878213', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (70, 'ADRIANA', 'DIAZ', 'HUAMANCHAY', 1, '10135707', 1, 'LA ENCANTADA DE SANTA ROSA MZ A LOTE 27 - SMP', '', '942521885', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (71, 'DAISI NOEMI', 'DIAZ', 'FLORES', 1, '41878213', 1, 'PRO.VIVIENDA LOS JARDINES DE SANTA ROSA ETAP.11 MZ. D LT 18 SAN MARTIN DE PORRES', '5058921', '950407761', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (72, 'RICARDO', 'DOMINGUEZ', 'FLORENCIO', 1, '25421136', 1, '', '', '961101938', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (73, 'CESAR ALCIDES', 'DURAN', 'SANTAMARIA', 1, '42059946', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (74, 'YANETH YUDITH', 'DURAN', 'TAQUIO', 1, '43308690', 1, ' MZ M LOTE 28 ASOC. VIV. CHICMABAMBA - SMP', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (75, 'ALICIA', 'ECHACCAYA', 'GUTIERREZ', 1, '40190628', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (76, 'MARCIANO ANTONIO', 'EGUSQUIZA', 'MATOS', 1, '25558989', 1, 'MZF. LT 40 ASOC. VIVIENDA .RESID CALIFORNIA SAN MARTIN DE PORRES', '5745719', '947494451', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (77, 'ANTONIO JULIO', 'ELESCANO', 'DE LA CRUZ', 1, '40105907', 1, '', '', '997708849', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (78, 'LUZMILA', 'ESCALANTE', 'DE TORRES', 1, '25461452', 1, 'CALLE LAS DALIAS MZ G LOTE 37 JOSE BOTTERIN - CALLAO', '4201622', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (79, 'YOLANDA ELISA', 'FELIX', 'MURGA', 1, '06191481', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (80, 'ZOILA', 'FERRARI', 'ZEVALLOS', 1, '10124768', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (81, 'PAMELA KERLY', 'FLORES', 'FLORES', 1, '71528771', 1, 'AV VENEZUELA ASENT. H. LOS CEDROS MZ I LOTE 15 - VENTANILLA', '', '918981508', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (82, 'MARTIN JULIO', 'FLORES', 'RAMOS', 1, '1111113', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (83, 'MARIA LUISA', 'GABANCHO', 'LOAYZA', 1, '25559664', 1, 'LA ALBORADA MZ B LOTE 16 II ETAPA - SMP', '', '943521830', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (84, 'NANCY DEMETRIA', 'GAMBOA', 'NAJARRO DE FELIPE', 1, '09008027', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (85, 'MARIBEL ROSARIO', 'GAMBOA', 'PALOMINO', 1, '25718314', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (86, 'ANGELICA', 'GARCIA', 'MINAYA DE OSORIO', 1, '25432052', 1, 'GAMBETA BAJA ESTE, SECTOR II MZ A2 LOTE 8 - CALLAO', '4294012', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (87, 'ANTONIO MERCEDES', 'GARCIA', 'PRETEL', 1, '06254991', 1, 'URB PALERMO MZ Q LOTE 27 - LIMA', '', '996413599', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (88, 'ANGELICA', 'GARCIA', 'MINAYA', 1, '25432052', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (89, 'FRANCISCA DONATA', 'GARCIA', 'PRUDENCIO', 1, '25862001', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (90, 'CLEMENCIA ALEJANDRINA', 'GARCIA', 'SILVA', 1, '25709060', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (91, 'VICTOR', 'GODOY', 'RAMOS', 1, '41071349', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (92, 'EUFEMIA NANCY', 'GOMEZ', 'TOLENTINO', 1, '25806446', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (93, 'MARIA LUZMILA', 'GOMEZ', 'SAUÑE', 1, '25416700', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (94, 'MARIA CATALINA', 'GONZALES', 'PROLEON', 1, '25781340', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (95, 'MIRELA RAQUEL', 'GONZALES', 'LARA', 1, '41550720', 1, 'ASENT. H. LOS OLIVOS DE SAN VICENTE MZ A LOTE 18 - SMP', '', '949512316', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (96, 'GLADYS SALINAS', 'GONZALES', 'BENITO', 1, '09360855', 1, 'JR.JOSE QUIÑONEZ 371 URB. VALDIVIEZO - ATE', '3260852', '999238223', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (97, 'SARA MARITZA', 'GONZALES', 'FLORES', 1, '41686882', 1, 'URB. BRAYSON MZ W LOTE 18 - SMP', '', '977867414', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (98, 'LILIANA MARUJA', 'GONZALEZ', 'CABELLOS', 1, '07498388', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (99, 'SANDRA MAGALY', 'GONZALEZ', 'CABELLOS', 1, '41205819', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (100, 'MANUEL', 'GOÑAS', 'DAZA', 1, '25815950', 1, '', '7669933', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (101, 'ADALINO ANTONIO', 'GRON', 'ECHEVARRIA', 1, '08667129', 1, '', '', '958448158', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (102, 'DONATO', 'GUADALUPE', 'ROJAS', 1, '25756590', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (103, 'ROMELIO', 'GUEVARA', 'DIAZ', 1, '10506560', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (104, 'ROSALYN', 'GUIZADO', 'RAMIREZ', 1, '41044641', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (105, 'CHAVELA FAUSTA', 'HERRERA', 'MATOS', 1, '40847195', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (106, 'ASUNCIONA', 'HERRERA', 'ROMERO', 1, '06919105', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (107, 'NINFA FANA', 'HERRERA', 'MATOS', 1, '25500103', 1, 'MZ G LOTE 05 URB LA QUILLA - CALLAO', '', '979024451', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (108, 'MARIA', 'HUAMAN', 'QUISPE DE HUAMANI', 1, '07394986', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (109, 'ANTONIETA', 'HUAMAN', 'PELAEZ', 1, '08666278', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (110, 'ANA MARIA', 'HUAMAN', 'ATAYPOMA', 1, '07138061', 1, '', '', '995169611', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (111, 'ANNABELLA', 'HUAMAN', 'MORENO', 1, '25712957', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (112, 'PEDRO', 'HUAMANI', 'RODRIGUEZ', 1, '08341606', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (113, 'EVELY KARINA', 'HUAROMO', 'FIGUEROA', 1, '43154608', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (114, 'DIN MARIA', 'ICHACCAYA', 'TOLEDANO', 1, '25755438', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (115, 'LIDIA NORMA', 'IGNACIO', 'CCENCHO', 1, '06839141', 1, '', '', '993122259', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (116, 'CINTHIA PATRICIA', 'JARA', 'PAREDES', 1, '45218965', 1, 'URB LOS CIPRESES MZ W LOTE 15 - SMP', '5748945', '990726064', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (117, 'MARUJA GUADALUPE', 'JARA', 'REGALADO', 1, '10616424', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (118, 'MARTINA', 'JIHUAÑA', 'MAMANI', 1, '25567592', 1, 'MZ J LOTE 14 ASOC. LAS CASUARINAS DE SANTA ROSA - SMP', '5747823', '932755059', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (119, 'MARGARITA', 'JIHUAÑA', 'CHAMBI', 1, '07750474', 1, 'ASOC. CHICMABAMBA MZ A LT 3 TERCERA ETAPA', '', '973282802', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (120, 'GUILLERMINA', 'JIHUAÑA', 'MAMANI', 1, '01860292', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (121, 'JESUS PAULINO', 'JURADO', 'HUAMANCAYO', 1, '25457507', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (122, 'MARIA EMILIANA', 'LAGUA', 'RIMEY', 1, '32026777', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (123, 'FRANCISCA', 'LARICO', 'COLQUEHUANCA', 1, '07318692', 1, 'MZ C LOTE 21 PRADRERAS DE NARANJAL- SMP', '', '952242466', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (124, 'YOLANDA', 'LARICO', 'COLQUEHUANCA', 1, '07568573', 1, 'MZ D LOTE 24 URB SAN VALENTIN I ETAPA - SMP', '', '975198793', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (125, 'CLAUDIO', 'LAURA', 'PERALTA', 1, '10104658', 1, '', '', '998729841', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (126, 'TOMASA FELICITA', 'LAZARO', 'CASTRO', 1, '25428542', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (127, 'NANCY YRENE', 'LEON', 'SALAS', 1, '08009213', 1, 'CALLE LAS AMAPOLAS 168, URB EL PALMAR - SURCO', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (128, 'CELESTINA LUZMILA', 'LEON', 'JERONIMO', 1, '25849460', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (129, 'JUAN MAXIMILIANO', 'LEYVA', 'CHINCHAY', 1, '25814800', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (130, 'TEOFILO', 'LIMACHE', 'HUALLPA', 1, '08334409', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (131, 'GUMERCINDA', 'LIPA', 'ALVAREZ', 1, '01551318', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (132, 'ISABEL', 'LIPA', 'CARRIZALES', 1, '25803300', 1, 'MZ C LT 09 URB. SANTA FE - VENTANILLA- CALLAO', '', '993031757', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (133, 'CALIXTO', 'LIPE', 'CARRIZALES', 1, '25803119', 1, 'ASOC. PROP 7 DE AGOSTO MZ I LOTE 8 - CALLAO', '', '939573431', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (134, 'JUANA', 'LLANO', 'FERNANDEZ', 1, '42328390', 1, 'PRO VIV LOS OLIVOS DE SAN VICENTE MZ A LOTE 4 - SMP', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (135, 'VILMA VIRGILIA', 'LLANTO', 'HUAROMO', 1, '42565492', 1, 'URB. FILADELFIA 1 ETAPA MZ. G LT. 28 - SMP', '', '961928939', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (136, 'MARILU', 'LOAYZA', 'OCHOA', 1, '25756525', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (137, 'CLAUDIA', 'LOPEZ', 'HINOSTROZA', 1, '25646055', 1, 'PASAJE 16 DE OCTUBRE MZ F2 LOTE 23 AAHH BOCANEGRA - CALLAO', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (138, 'CIRO', 'LOPEZ', 'HINOSTROZA', 1, '25632398', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (139, 'MERY LUZ', 'LUNA', 'BEYZAGA', 1, '08547405', 1, 'URB. LOS ROSALES MZ B LOTE 11 - SMP', '5741062', '989135464', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (140, 'EUSEBIA', 'LUQUE', 'APAZA', 1, '25473902', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (141, 'BENIGNA', 'LUQUE', 'CHOQUEHUANCA', 1, '07701761', 1, 'ASOC VIV CHICMABAMBA MZ G LT 13 SPM ', '5751994', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (142, 'JUANA', 'LUQUE', 'CHURA', 1, '25816125', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (143, 'VIRGINIA', 'LUQUE', 'CUTIPA', 1, '41201511', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (144, 'JOSE CONCEPCION', 'LUQUE', 'QUISPE', 1, '25455019', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (145, 'GLADYS', 'MADERA', 'QUISPE', 1, '08662364', 1, 'CALLE 14 ASOC SAN FRANCISCO DE CAYRAN MZ D1 LT 23 - SMP', '', '920618655', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (146, 'LOURDES', 'MADERA', 'QUISPE', 1, '10453024', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (147, 'ALEJANDRO ADAN', 'MALASQUEZ', 'HUAMAN', 1, '25471772', 1, '', '', '989564146', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (148, 'CARMELA', 'MALCA', 'VIVANCO', 1, '08631884', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (149, 'FELICITAS', 'MALLQUI', 'MARTEL', 1, '1111114', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (150, 'ROSA', 'MAMANI', 'LIPE', 1, '25568112', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (151, 'MARUJA', 'MAMANI', 'CONDORI', 1, '25569605', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (152, 'CLEMENTINA', 'MARTINEZ', 'BERROCAL DE TENORIO', 1, '25808994', 1, 'URB. EL ALAMO MZ D-01 LOTE 18', '', '949150279', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (153, 'MAGALI', 'MAYORGA', 'DE LEON', 1, '10161930', 1, 'ASOC VIVIENDAS CHICMABAMBA MZ H LOTE 2 - SMP', '5750048', '947227322', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (154, 'MARIA CARMEN', 'MAYTA', 'MENDOZA', 1, '25815003', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (155, 'ORLANDO', 'MELENDEZ', 'TAFUR', 1, '08581767', 1, 'CALLE 6 MZ P LOTE 28 URB SANTA ELISA 1ERA ETAPA - LOS OLIVOS', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (156, 'VIOLETA', 'MENDIETA', 'PADILLA', 1, '07880157', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (157, 'VILMA MARY', 'MILLA', 'COTRINA DE CARRILLO', 1, '06710965', 1, 'URB EL ALAMO MZ V LOTE 5 - CALLAO', '', '990052776', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (158, 'JULIA AIDA', 'MONTALVO', 'SAENZ', 1, '08515000', 1, 'PSJ. LOS MILAGROS 558 DPTO. 402 CONJ. HAB. CIUDAD SATELITE SANTA ROSA', '5741852', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (159, 'JUSTA', 'MORALES', 'MARIÑO', 1, '31155166', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (160, 'BERNARDO DARIO', 'MORENO', 'QUESADA', 1, '08458649', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (161, 'SELENE', 'MORETTI', 'VEGA DE VASQUEZ', 1, '42748818', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (162, 'JOSE LUIS', 'MORIANO', 'FERNANDEZ', 1, '43320162', 1, '1RO DE MAYO MZ AB LT02 INDEPENDENCIA', '', '975606059', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (163, 'JACINTA', 'NAJARRO', 'SOLIS', 1, '25494719', 1, 'URB. EL ALAMO MZ D1 LT 23 - CALLAO', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (164, 'SATURNINA', 'OCHOA', 'ROJAS', 1, '25450370', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (165, 'MELANY CONSUELO', 'OCON', 'ROMERO', 1, '10678775', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (166, 'GLADYS FELICITA', 'OLIVARES', 'SEGUNDO', 1, '25759218', 1, 'URB. EL ALAMO MZ D1 LOTE 4 - CALLAO', '', '940777669', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (167, 'CESAR GUSTAVO', 'OLORTEGUI', 'MASGO', 1, '46854867', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (168, 'VICTOR ELADIO', 'OLORTEGUI', 'LLASHAG', 1, '25487807', 1, 'URB. LAS 200 MILLAS MZ J LOTE 32 - CALLAO', '', '992338726', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (169, 'JUANA', 'ORE', 'FLORES', 1, '25505679', 1, 'ASOC. LAS COLINAS AV LOS DOMINICOS MZ N LOTE 11', '', '974512192', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (170, 'WILFREDO ADRIAN', 'PACHECO', 'CARRILLO', 1, '08158721', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (171, 'ALEJANDRINA', 'PAJUELO', 'GARCIA DE ALVARADO', 1, '07956441', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (172, 'JORGE LUIS', 'PALOMINO', 'CHAVEZ', 1, '09362503', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (173, 'EVARISTA ERNESTINA', 'PALOMINO', 'LLASACA', 1, '25713056', 1, '', '', '946056070', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (174, 'FELISA', 'PALOMINO', 'ECHACCAYA DE RAMIREZ', 1, '25810529', 1, 'CALLE EL OLIVAR MZ K LOTE 34 URB LAS FRESAS - CALLAO', '4875561', '997059314', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (175, 'SARA MAGDALENA', 'PALOMINO', 'GRADOS', 1, '22486967', 1, 'URB. LOS LIRIOS MZ D LOTE 3 - CALLAO', '4915226', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (176, 'NORKA MARGOT', 'PAREDES', 'PAREJA', 1, '10621029', 1, 'URB. LOS CIPRESES MZ W LOTE 15 - SMP', '5748945', '995588705', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (177, 'ALICIA YOLANDA', 'PARICAHUA', 'QUISPE', 1, '25806123', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (178, 'TEODOSIO', 'PARIONA', 'VILCA', 1, '25496207', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (179, 'DILMER', 'PASAPERA', 'CARAMANTIN', 1, '25510868', 1, 'ASOC. LOS CHASQUIS MZ C LOTE 3 - SMP', '7596732', '986613108', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (180, 'MILAGROS', 'PASTOR', 'CUADROS', 1, '25570667', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (181, 'CARMEN', 'PAUCAR', 'GUTIERREZ', 1, '25815298', 1, '', '', '980260080', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (182, 'ROCIO DEL PILAR', 'PAZ', 'CARLOS', 1, '25750617', 1, 'URB AEROPUERTO MZ G LOTE 18 (AV. QUILCA 214) - CALLAO', '', '997875427', 'CHIO_PC05@HOTMAIL.COM', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (183, 'MARIO', 'PEREZ', 'QUISPE', 1, '25832368', 1, 'AV. PACASMAYO URB ALBORADA DE SANTA ROSA ETAPA 3 MZ A LOTE 5 - SMP', '4333030', '981349785', 'MIJU_65@HOTMAIL.COM', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (184, 'CIRILO', 'PEREZ', 'CAMARGO', 1, '25474849', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (185, 'MARIBEL ROSARIO', 'PEREZ', 'LLANGE', 1, '06083761', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (186, 'AVILIA', 'PICOY', 'RIVERA', 1, '04071822', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (187, 'GLORIA ANTONIA', 'PICOY', 'RIVERA', 1, '04081660', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (188, 'LUCIA MEZARINA', 'PINEDO', 'DE COLLANTES', 1, '25513210', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (189, 'FELICITA', 'PRETEL', 'GUTIÉRREZ', 1, '06149925', 1, 'P. JOVEN 1ERO SETIEMBRE MZ S LOTE 12 - LIMA', '', '988320001', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (190, 'VENIGNA', 'PRETEL', 'GUTIERREZ', 1, '08936257', 1, 'DEADELFA 1 ETAPA MZ C LOTE 24 - SMP', '5747339', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (191, 'RICHARD', 'PUJADAS', 'SARMIENTO', 1, '25717974', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (192, 'JORGE LUIS', 'PUMACAYO', 'PACHECO', 1, '45798170', 1, '', '6558267', '940464724', 'JORGELPP6@GMAIL.COM', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (193, 'SILVIA BEATRIZ', 'QUIQUE', 'TANTA DE MORENO', 1, '06085298', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (194, 'OLGA', 'QUISPE', 'VERASTEGUI', 1, '09442848', 1, 'CALLE EL OLIVAR URB LA QUILLA MZ C LOTE 16 - CALLAO', '6571670', '963316561', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (195, 'WILSON', 'QUISPE', 'QUISPE', 1, '46420779', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (196, 'MANUEL', 'QUISPE', 'CALLATA', 1, '08450662', 1, 'LOS LIRIOS MZ E LOTE 4 - SMP', '7753801', '970808435', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (197, 'ALEXANDER ARMANDO', 'QUISPE', 'CACERES', 1, '25717754', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (198, 'PRIMITIVO', 'QUISPE', 'GUTIERREZ', 1, '10457521', 1, ' MZ M LOTE 28 ASOC. VIV. CHICMABAMBA - SMP', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (199, 'PAUL MILTON', 'QUISPE', 'HUAMANI', 1, '10537321', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (200, 'CELSA', 'QUISPE', 'PINELO', 1, '09896246', 1, '', '', '997923510', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (201, 'FRANCISCO', 'QUISPE', 'MAMANI', 1, '25813991', 1, 'MZ H LOTE 16 III ETAPA - LOS JAZMINES - CALLAO', '', '934667054', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (202, 'PRIMITIVO', 'QUISPE', 'GUTIERREZ', 1, '10457521', 1, ' MZ M LOTE 28 ASOC. VIV. CHICMABAMBA - SMP', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (203, 'RODRIGO', 'QUISPE', 'GUTIERREZ', 1, '25479237', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (204, 'ALFREDO', 'QUISPE', 'SAYAS', 1, '25783394', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (205, 'LIDIA TEOFANES', 'QUISPE', 'CHIPANA', 1, '25562398', 1, 'URB LAS FRESAS MZ K LOTE 16 - CALLAO', '5023581', '995677843', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (206, 'RODRIGO', 'QUISPE', 'GUTIERREZ', 1, '10494931', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (207, 'PRIMITIVO', 'QUISPE', 'GUTIERREZ', 1, '10457521', 1, ' MZ M LOTE 28 ASOC. VIV. CHICMABAMBA - SMP', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (208, 'ALEJANDRO', 'QUITO', 'APARICIO', 1, '32026762', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (209, 'JULIA ISABEL', 'RAMOS', 'ROJAS', 1, '41635386', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (210, 'DIONICIA LEONARDA', 'RAMOS', 'LOPEZ', 1, '15344309', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (211, 'SANDRA GISELA', 'REYES', 'SOTO', 1, '1111115', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (212, 'FELIPE MAXIMO', 'RIOS', 'MAYTA', 1, '06036267', 1, 'P. JOVEN 1ERO SETIEMBRE MZ S LOTE 12 - LIMA', '', '958669998', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (213, 'MARGARITA', 'RIOS', 'MAYTA', 1, '08056236', 1, 'PSJE 3 MZ S LOTE 12 P. JOVEN 1ERO DE SETIEMBRE', '4844296', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (214, 'ANA KARINA', 'RIOS', 'PRETEL', 1, '42805248', 1, 'P. JOVEN 1ERO SETIEMBRE MZ S LOTE 12 - LIMA', '', '999006879', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (215, 'ROCIO', 'RIOS', 'MANTARI', 1, '40654009', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (216, 'REYNA ISABEL', 'RIOS', 'DE CASTELLANO', 1, '09642529', 1, '', '', '942441213', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (217, 'ELEODORO HUMBERTO', 'RIVERA', 'ZACARIAS', 1, '43310669', 1, 'ASOC. VIVIENDA CHICMABAMBA III ETAPA MZ L LOTE 2 - SMP', '4840375', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (218, 'CARMEN ROSA', 'RIVERA', 'DE VILLON', 1, '25516735', 1, '', '', '992634302', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (219, 'CLEVER', 'RIVERA', 'PRETEL', 1, '10443159', 1, 'DEADELFA 1 ETAPA MZ C LOTE 24 - SMP', '', '921070297', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (220, 'LUCILIA CECILIA', 'ROJAS', 'LEON', 1, '07580847', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (221, 'CARMELITA', 'ROSALES', 'DAZA', 1, '22880250', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (222, 'RODOLFO', 'ROSALES', 'ROQUE', 1, '1111116', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (223, 'JUANA PERSEVERANDA', 'RUIZ', 'BLAS', 1, '10185466', 1, 'URB. PROVIV. LOS ALISOS DE OQUENDO ETAPA II MZ A LOTE 23', '', '945302320', 'JOSE_292008@HOTMAIL.COM', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (224, 'BALVINA', 'RUIZ', 'RIOS', 1, '07169864', 1, 'AV. PRINCIPAL MZ I LOTE 21 URB. EL ALAMO - CALLAO', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (225, 'ELIZABETH MARICELA', 'SALAZAR', 'CAJACHAGUA', 1, '21263715', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (226, 'CATY', 'SALAZAR', 'LAZO', 1, '09855585', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (227, 'DIANA MARILYN', 'SALDAÑA', 'CONDORI', 1, '41412755', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (228, 'KARIN VANESSA', 'SALINAS', 'SOLIS', 1, '41316275', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (229, 'ARTURO', 'SALVADOR', 'PABLO', 1, '09457391', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (230, 'FANNY', 'SANCHEZ', 'TIMANA', 1, '1111117', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (231, 'JULIA MARTHA', 'SANCHEZ', 'ROQUE', 1, '40017830', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (232, 'MARIA NELIDA', 'SANCHEZ', 'BURGA', 1, '41302727', 1, 'URB VENECIA MZ F LOTE 26 - SMP', '', '949229861', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (233, 'FLOR GIECELA', 'SANCHEZ', 'POMA', 1, '1111118', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (234, 'SIMEONA', 'SARMIENTO', 'DE PUJADAS', 1, '06695761', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (235, 'MARIA ELENA', 'SAYAS', 'ESPINOZA DE QUISPE', 1, '25517461', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (236, 'VALENTINA', 'SILVA', 'DE CUARESMA', 1, '25432987', 1, 'URB LOS OLIVOS DE SAN VICENTE MZ A LOTE 19 - SMP', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (237, 'BELISARIO', 'SOCA', 'ARANGO', 1, '25802023', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (238, 'HERLINDA', 'SOTO', 'TAIPE', 1, '25848299', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (239, 'BARBARITA SANTOS', 'SOTO', 'MENDOZA', 1, '10457682', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (240, 'JOSEFINA', 'TACUSI', 'CCORIMANYA', 1, '09275885', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (241, 'MIRNA', 'TAFUR', 'TAFUR', 1, '33949997', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (242, 'NILDA', 'TAFUR', 'TAFUR', 1, '10451512', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (243, 'ROSA LUZ', 'TARAZONA', 'MANRIQUE', 1, '40423222', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (244, 'MAURA', 'TENORIO', 'OLANYA', 1, '25743636', 1, 'URB MI TERRUÑO MZ L LOTE 14 - SMP', '', '946850346', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (245, 'FRANCISCO', 'TICONA', 'ALVAREZ', 1, '25482090', 1, 'URB RAMON CASTILLA MZ I LOTE 8 - CALLAO', '7241583', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (246, 'ARCELA DOMINGA', 'TITO', 'UCHPAS DE LA CRUZ', 1, '10458320', 1, 'AAHH SANTA ROSA DE PIEROLA MZ A LOTE 3 - CALLAO', '', '934324810', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (247, 'ALFREDO', 'TOLEDANO', 'CUIBA', 1, '25845169', 1, 'EX FUNDO OQUENDO KM8 CARRETERA VENTANILLA - CALLAO', '', '991203137', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (248, 'REYNA ISABEL', 'TORO', 'CORONEL', 1, '25407349', 1, 'BENJAMIN DOY 2777 EL PACIFICO SAN MARTIN DE PORRES', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (249, 'LUCILA ENGELICA', 'TORPOCO', 'BARRANTES', 1, '07154854', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (250, 'CEFERINA MARCELINA', 'TORRES', 'MONTAÑEZ', 1, '25555705', 1, 'ASOC PROP OLIVOS DE SANTA ROSA MZ H LOTE 20 - CALLAO', '5756311', '913650954', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (251, 'DOMINGA SUSANA', 'TORRES', 'MONTAÑÉZ', 1, '25455307', 1, 'URB LOS ALAMOS MZ A LOTE 14 - CALLAO', '5756311', '970507173', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (252, 'JASSON ADRIAN', 'TRAUCO', 'PAREDES', 1, '45834682', 1, 'CALLE SANTA ROSA URB. LOS CIPRESES MZ W LOTE 15 - SMP', '', '958408694', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (253, 'TEOFILO', 'TRUJILLO', 'LEON', 1, '22880080', 1, 'ASOC SANTA ISABELL MZ A LT 30 SMP', '', '974678677', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (254, 'ROSA ELVIRA', 'TUNGA', 'SANTISTEBAN', 1, '17543276', 1, 'LOS OLIVOS DE SAN VICENTE MZ C LOTE 7 - SMP', '', '999152527', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (255, 'MARIA', 'UNTUL', 'BURGOS', 1, '07333973', 1, '', '', '931198676', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (256, 'JUANA', 'VARGAS', 'VARGAS', 1, '25330732', 1, 'URB. LAS FRESAS MZ N LOTE 06', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (257, 'JOSE LUIS', 'VASQUEZ', 'GUTTI', 1, '41039584', 1, 'MZ D LOTE 08 LLOS ROSALES DE SANTA ROSA- SMP', '', '980646930', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (258, 'JORGE LUIS', 'VELASQUEZ', 'PARDO', 1, '15425017', 1, 'URB STA APOLONIA MZ L LOTE 11 - SMP', '5720825', '920828333', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (259, 'JESUS ORLANDO', 'VELASQUEZ', 'RAMIREZ', 1, '25561137', 1, 'AV. JOSE OLAYA MZ C LOTE 06 AA.HH PIEDRA LIZA TABOADA - CALLAO', '', '993768984', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (260, 'SABINA', 'VELASQUEZ', 'ROMERO', 1, '06572873', 1, '', '', '977376150', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (261, 'MANUELA', 'VILCA', 'YANAPA', 1, '09904563', 1, '', '', '', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (262, 'MARTA ANTONIA', 'YAURI', 'GARCIA', 1, '10453205', 1, 'CALLE 4 MZ.A LT. 20 ASOC.LOS GIRASOLES DE SANTA ROSA 2DA ETAP SAN MARTIN DE PORRES.', '5747277', '972392461', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (263, 'EMMA DORA', 'YAURICASA', 'CHOQUE', 1, '25416356', 1, 'AV PASEO LOS FRANCISCANOS 402 DPTO 402 - CIUDAD SATELITE STA ROSA - CALLAO', '4840192', '942187765', 'EDYCH61@HOTMAIL.COM', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (264, 'MARIA RICARDINA', 'ZAQUINAULA', 'GOMEZ', 1, '06842325', 1, 'URB SAN REMO MZ A LOTE 15 - SMP', '', '992915865', '', 2, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (265, 'PROPIEDAD ASOCIACION', ' ', ' ', 1, '9999992', 1, '', '', '', '', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (266, 'LUIS PAULO', 'JAVE', 'CARITAS', 1, '70498682', 1, 'CALLE LOS LIRIOS MZ A LT 23 URB. FILADELFIA 2DA ETAPA - SMP', '4304588', '987814050', 'PAULO.JAVE.CARITAS@GMAIL.COM', 1, 1);
insert into persona (id, nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values (267, 'JESSICA LIZBETH', 'JAVE', 'CARITAS', 1, '42911900', 1, 'CALLE LOS LIRIOS MZ A LT 23 URB. FILADELFIA 2DA ETAPA - SMP', '', '982402819', 'PAULO.JAVE.CARITAS@GMAIL.COM', 2, 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('persona', 'id'), (SELECT MAX(id) FROM persona)+1);
*/







insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (1, 267, 3, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (2, 217, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (3, 86, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (4, 225, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (5, 162, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (6, 231, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (6, 65, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (7, 116, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (8, 209, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (9, 20, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (9, 160, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (10, 194, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (11, 245, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (12, 69, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (12, 195, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (13, 258, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (14, 258, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (15, 166, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (16, 12, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (17, 153, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (18, 104, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (19, 245, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (20, 196, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (21, 116, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (22, 252, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (23, 176, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (24, 137, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (25, 186, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (26, 33, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (26, 94, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (27, 77, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (28, 105, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (29, 57, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (29, 70, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (30, 84, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (31, 102, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (32, 163, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (33, 163, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (34, 163, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (35, 244, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (36, 129, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (37, 72, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (38, 15, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (39, 259, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (40, 180, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (41, 167, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (42, 126, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (43, 126, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (44, 125, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (45, 226, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (46, 38, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (47, 98, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (48, 51, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (49, 54, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (50, 154, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (51, 66, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (52, 5, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (53, 25, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (54, 223, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (55, 148, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (56, 46, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (57, 123, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (58, 16, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (58, 228, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (59, 136, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (60, 136, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (61, 10, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (61, 149, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (62, 208, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (62, 122, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (63, 208, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (64, 131, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (65, 131, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (66, 106, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (67, 171, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (68, 78, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (69, 37, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (70, 62, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (71, 108, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (72, 220, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (73, 210, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (74, 43, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (75, 265, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (76, 265, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (77, 183, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (78, 22, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (79, 247, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (80, 124, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (81, 103, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (82, 245, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (83, 197, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (84, 2, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (85, 156, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (86, 145, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (87, 236, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (88, 35, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (89, 47, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (90, 238, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (91, 140, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (92, 241, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (92, 265, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (93, 260, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (94, 9, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (95, 138, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (96, 18, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (97, 58, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (97, 59, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (98, 265, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (99, 265, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (100, 181, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (101, 115, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (102, 28, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (103, 150, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (104, 36, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (105, 212, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (106, 213, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (107, 107, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (108, 83, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (109, 157, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (110, 255, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (111, 133, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (112, 189, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (113, 87, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (114, 87, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (115, 34, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (116, 164, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (117, 21, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (118, 265, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (119, 147, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (120, 13, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (121, 13, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (122, 91, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (122, 95, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (123, 155, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (123, 91, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (124, 242, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (125, 135, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (126, 6, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (126, 184, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (127, 134, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (128, 109, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (129, 109, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (130, 265, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (131, 48, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (132, 48, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (133, 96, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (133, 172, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (134, 96, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (135, 198, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (135, 239, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (136, 121, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (137, 235, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (138, 235, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (139, 214, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (140, 79, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (141, 23, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (142, 110, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (143, 81, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (144, 63, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (145, 165, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (145, 45, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (146, 3, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (146, 232, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (147, 100, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (148, 118, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (148, 130, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (149, 139, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (150, 139, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (151, 42, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (152, 141, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (153, 68, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (154, 173, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (155, 49, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (155, 237, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (156, 85, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (157, 80, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (158, 127, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (159, 250, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (160, 31, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (161, 29, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (162, 142, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (163, 199, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (164, 113, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (164, 118, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (165, 200, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (166, 30, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (166, 233, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (167, 169, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (168, 132, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (169, 246, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (170, 218, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (171, 92, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (172, 92, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (173, 143, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (174, 177, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (174, 201, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (175, 234, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (176, 73, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (177, 221, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (177, 253, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (178, 128, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (179, 128, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (180, 61, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (181, 198, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (181, 239, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (181, 69, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (182, 27, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (183, 50, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (184, 227, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (185, 76, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (186, 74, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (186, 101, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (187, 198, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (187, 239, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (188, 111, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (189, 11, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (190, 229, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (191, 256, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (192, 170, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (193, 151, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (194, 55, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (195, 60, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (196, 14, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (197, 88, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (198, 26, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (199, 71, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (200, 4, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (201, 203, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (201, 243, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (201, 211, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (202, 53, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (203, 93, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (204, 251, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (205, 41, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (205, 230, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (206, 168, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (207, 179, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (208, 121, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (209, 119, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (210, 187, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (211, 174, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (212, 249, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (213, 248, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (214, 102, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (215, 158, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (216, 204, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (217, 175, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (218, 265, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (219, 135, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (220, 205, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (221, 99, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (222, 262, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (223, 263, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (224, 159, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (225, 146, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (226, 182, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (227, 75, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (228, 97, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (229, 203, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (230, 1, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (231, 188, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (232, 7, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (232, 89, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (233, 7, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (233, 89, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (234, 56, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (235, 39, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (236, 190, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (237, 224, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (238, 261, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (239, 117, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (240, 215, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (241, 64, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (242, 192, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (243, 193, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (244, 120, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (245, 198, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (245, 239, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (246, 67, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (246, 240, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (247, 144, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (248, 32, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (249, 185, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (250, 114, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (250, 178, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (251, 17, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (252, 216, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (253, 40, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (253, 82, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (254, 112, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (255, 191, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (256, 152, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (257, 161, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (257, 257, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (257, 171, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (258, 19, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (258, 90, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (259, 264, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (260, 24, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (261, 44, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (261, 222, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (262, 254, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (263, 219, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (264, 51, 1, 1);
insert into puesto_persona_cargo(id_puesto, id_persona, id_cargo, estado) values (265, 52, 1, 1);

/*
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (1, 1, 267, 3, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (2, 2, 217, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (3, 3, 86, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (4, 4, 225, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (5, 5, 162, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (6, 6, 231, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (7, 6, 65, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (8, 7, 116, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (9, 8, 209, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (10, 9, 20, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (11, 9, 160, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (12, 10, 194, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (13, 11, 245, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (14, 12, 69, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (15, 12, 195, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (16, 13, 258, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (17, 14, 258, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (18, 15, 166, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (19, 16, 12, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (20, 17, 153, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (21, 18, 104, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (22, 19, 245, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (23, 20, 196, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (24, 21, 116, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (25, 22, 252, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (26, 23, 176, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (27, 24, 137, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (28, 25, 186, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (29, 26, 33, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (30, 26, 94, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (31, 27, 77, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (32, 28, 105, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (33, 29, 57, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (34, 29, 70, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (35, 30, 84, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (36, 31, 102, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (37, 32, 163, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (38, 33, 163, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (39, 34, 163, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (40, 35, 244, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (41, 36, 129, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (42, 37, 72, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (43, 38, 15, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (44, 39, 259, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (45, 40, 180, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (46, 41, 167, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (47, 42, 126, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (48, 43, 126, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (49, 44, 125, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (50, 45, 226, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (51, 46, 38, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (52, 47, 98, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (53, 48, 51, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (54, 49, 54, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (55, 50, 154, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (56, 51, 66, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (57, 52, 5, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (58, 53, 25, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (59, 54, 223, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (60, 55, 148, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (61, 56, 46, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (62, 57, 123, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (63, 58, 16, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (64, 58, 228, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (65, 59, 136, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (66, 60, 136, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (67, 61, 10, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (68, 61, 149, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (69, 62, 208, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (70, 62, 122, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (71, 63, 208, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (72, 64, 131, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (73, 65, 131, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (74, 66, 106, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (75, 67, 171, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (76, 68, 78, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (77, 69, 37, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (78, 70, 62, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (79, 71, 108, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (80, 72, 220, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (81, 73, 210, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (82, 74, 43, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (83, 75, 265, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (84, 76, 265, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (85, 77, 183, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (86, 78, 22, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (87, 79, 247, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (88, 80, 124, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (89, 81, 103, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (90, 82, 245, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (91, 83, 197, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (92, 84, 2, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (93, 85, 156, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (94, 86, 145, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (95, 87, 236, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (96, 88, 35, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (97, 89, 47, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (98, 90, 238, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (99, 91, 140, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (100, 92, 241, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (101, 92, 265, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (102, 93, 260, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (103, 94, 9, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (104, 95, 138, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (105, 96, 18, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (106, 97, 58, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (107, 97, 59, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (108, 98, 265, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (109, 99, 265, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (110, 100, 181, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (111, 101, 115, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (112, 102, 28, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (113, 103, 150, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (114, 104, 36, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (115, 105, 212, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (116, 106, 213, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (117, 107, 107, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (118, 108, 83, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (119, 109, 157, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (120, 110, 255, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (121, 111, 133, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (122, 112, 189, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (123, 113, 87, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (124, 114, 87, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (125, 115, 34, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (126, 116, 164, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (127, 117, 21, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (128, 118, 265, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (129, 119, 147, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (130, 120, 13, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (131, 121, 13, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (132, 122, 91, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (133, 122, 95, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (134, 123, 155, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (135, 123, 91, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (136, 124, 242, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (137, 125, 135, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (138, 126, 6, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (139, 126, 184, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (140, 127, 134, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (141, 128, 109, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (142, 129, 109, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (143, 130, 265, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (144, 131, 48, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (145, 132, 48, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (146, 133, 96, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (147, 133, 172, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (148, 134, 96, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (149, 135, 198, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (150, 135, 239, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (151, 136, 121, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (152, 137, 235, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (153, 138, 235, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (154, 139, 214, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (155, 140, 79, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (156, 141, 23, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (157, 142, 110, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (158, 143, 81, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (159, 144, 63, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (160, 145, 165, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (161, 145, 45, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (162, 146, 3, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (163, 146, 232, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (164, 147, 100, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (165, 148, 118, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (166, 148, 130, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (167, 149, 139, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (168, 150, 139, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (169, 151, 42, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (170, 152, 141, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (171, 153, 68, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (172, 154, 173, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (173, 155, 49, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (174, 155, 237, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (175, 156, 85, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (176, 157, 80, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (177, 158, 127, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (178, 159, 250, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (179, 160, 31, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (180, 161, 29, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (181, 162, 142, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (182, 163, 199, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (183, 164, 113, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (184, 164, 118, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (185, 165, 200, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (186, 166, 30, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (187, 166, 233, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (188, 167, 169, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (189, 168, 132, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (190, 169, 246, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (191, 170, 218, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (192, 171, 92, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (193, 172, 92, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (194, 173, 143, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (195, 174, 177, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (196, 174, 201, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (197, 175, 234, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (198, 176, 73, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (199, 177, 221, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (200, 177, 253, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (201, 178, 128, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (202, 179, 128, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (203, 180, 61, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (204, 181, 198, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (205, 181, 239, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (206, 181, 69, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (207, 182, 27, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (208, 183, 50, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (209, 184, 227, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (210, 185, 76, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (211, 186, 74, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (212, 186, 101, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (213, 187, 198, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (214, 187, 239, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (215, 188, 111, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (216, 189, 11, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (217, 190, 229, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (218, 191, 256, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (219, 192, 170, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (220, 193, 151, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (221, 194, 55, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (222, 195, 60, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (223, 196, 14, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (224, 197, 88, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (225, 198, 26, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (226, 199, 71, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (227, 200, 4, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (228, 201, 203, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (229, 201, 243, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (230, 201, 211, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (231, 202, 53, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (232, 203, 93, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (233, 204, 251, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (234, 205, 41, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (235, 205, 230, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (236, 206, 168, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (237, 207, 179, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (238, 208, 121, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (239, 209, 119, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (240, 210, 187, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (241, 211, 174, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (242, 212, 249, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (243, 213, 248, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (244, 214, 102, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (245, 215, 158, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (246, 216, 204, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (247, 217, 175, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (248, 218, 265, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (249, 219, 135, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (250, 220, 205, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (251, 221, 99, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (252, 222, 262, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (253, 223, 263, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (254, 224, 159, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (255, 225, 146, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (256, 226, 182, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (257, 227, 75, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (258, 228, 97, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (259, 229, 203, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (260, 230, 1, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (261, 231, 188, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (262, 232, 7, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (263, 232, 89, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (264, 233, 7, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (265, 233, 89, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (266, 234, 56, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (267, 235, 39, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (268, 236, 190, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (269, 237, 224, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (270, 238, 261, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (271, 239, 117, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (272, 240, 215, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (273, 241, 64, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (274, 242, 192, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (275, 243, 193, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (276, 244, 120, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (277, 245, 198, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (278, 245, 239, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (279, 246, 67, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (280, 246, 240, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (281, 247, 144, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (282, 248, 32, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (283, 249, 185, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (284, 250, 114, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (285, 250, 178, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (286, 251, 17, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (287, 252, 216, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (288, 253, 40, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (289, 253, 82, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (290, 254, 112, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (291, 255, 191, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (292, 256, 152, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (293, 257, 161, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (294, 257, 257, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (295, 257, 171, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (296, 258, 19, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (297, 258, 90, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (298, 259, 264, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (299, 260, 24, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (300, 261, 44, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (301, 261, 222, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (302, 262, 254, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (303, 263, 219, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (304, 264, 51, 1, 1);
insert into puesto_persona_cargo(id, id_puesto, id_persona, id_cargo, estado) values (305, 265, 52, 1, 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('puesto_persona_cargo', 'id'), (SELECT MAX(id) FROM puesto_persona_cargo)+1);

*/


insert into usuario (id_persona, usuario, clave, estado, rol) values (266, '70498682', '123456', 1, 'ROLE_ADMIN'); 
insert into usuario (id_persona, usuario, clave, estado, rol) values (267, '42911900', '123456', 1, 'ROLE_ADMIN'); 

--Prueba insert into usuario (id_persona, usuario, clave, estado, rol) values (1, '70498682', '123456', 1, 'ROLE_ADMIN'); 


-- ID = 1
insert into variable (id, descripcion, monto, estado) values (1, 'COSTO LUZ', 0.70, 1);
-- ID = 2
insert into variable (id, descripcion, monto, estado) values (2, 'ALUMBRADO PUBLICO', 3.70, 1);
-- ID = 3
insert into variable (id, descripcion, monto, estado) values (3, 'COSTO AGUA', 12.00, 1);
-- ID = 4
insert into variable (id, descripcion, monto, estado) values (4, 'ADMINISTRACION', 2.00, 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('variable', 'id'), (SELECT MAX(id) FROM variable)+1);



-- ID = 1
insert into tipo_servicio (id, descripcion, estado) values (1, 'DISTRIBUCION ELECTRICA', 1);
-- ID = 2
insert into tipo_servicio (id, descripcion, estado) values (2, 'AGUA POTABLE Y ALCANTARILLADO', 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('tipo_servicio', 'id'), (SELECT MAX(id) FROM tipo_servicio)+1);


-- ID = 1
insert into categoria_operacion (id, descripcion, id_tipo_operacion, estado) values (1, 'COBRO SERVICIO ELECTRICO', 1, 1);
-- ID = 2
insert into categoria_operacion (id, descripcion, id_tipo_operacion, estado) values (2, 'COBRO SERVICIO AGUA', 1, 1);
-- ID = 3
insert into categoria_operacion (id, descripcion, id_tipo_operacion, estado) values (3, 'COBRO ADMINISTRATIVO', 1, 1);
SELECT pg_catalog.setval(pg_get_serial_sequence('categoria_operacion', 'id'), (SELECT MAX(id) FROM categoria_operacion)+1);

--TODOS LOS DEMAS LEVANTADOS POR DATA HISTORICA
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('S.F.L. 2005', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ANT. PROY. 09', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('TARJETA DE ANIVERSARIO 2009', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('PROYECTO DE CONSTRUCCION 2008', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ARBITRIOS', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('IMPUESTO PREDIAL 2004-2005', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('IMPUESTO PREDIAL 2008', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('IMPUESTO PREDIAL 2009', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('CUOTA EXTRAORDINARIA', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('IMPUESTO PREDIAL 2011', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('IMPUESTO PREDIAL 2012', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ARBTR. 2011', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('PISO 2013', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ESTRUCTURA METALICA 2013', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ELIM. DESMONTE PSTOS. PROV.', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('DERECHO INSTALACION LUZ PROV.', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('CONV. SEDAPAL-CAJAS Y TRUNC. A-D', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('PORTONES Y ESTRUC MET PTOS ASOC', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('PROY DE PLANO DE ELECTRIFICACI', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('PINTADO PUERTA PSTOS', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('AGUA DICIEMBRE 2013 P/PISO', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('INSTALACIONES ELECTRICAS 2014', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ESTUDIO D PERFIL (PYV) 2014', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ADIC INST ELECTRICA 2014', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ADI. INSTAL. ELECTRICA INTER', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('OBSERV. MUNICIPAL / FUMIGACION 17', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('AUMTO CARGA A SUMINISTROS 2015', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('TBLRO. ELECTRICO LLAVES TERMO 2016', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('MULTA ANIVERSARIO 2017', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('TARJETA ANIVERSARIO 2017', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('MULTA DE ELECCIONES 2017', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('MULTA DE ASAMBLEAS', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('REUNION/FAENA DE TRABAJADORES', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('MULTA DEFENSA CIVIL 2017', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('INTERES / ACUERDO ASAMB. AGO. 2015', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('INTERES MORATORIO LUZ Y AGUA', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('PANETONCITOS MUNI. 2017', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('CUOTA AUDITORIA', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('LIQUIDACION BENEFICIOS 2017', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('GESTIONES INDECI / SUBSANACIONES', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('FUMIGACION', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('CANALETAS 2017', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('SUMIDEROS 2017', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ACOPIO-02CONTDORES Y ESTRACTOR', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('IMPLEM TECHO PASADIZOS 2014 (CALAMINAS)', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('LUZ PEND 2008', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ADM PEND 2006-2017', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('LUZ PEND HASTA EL 2016', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('AGUA PEND. HASTA EL 2016', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ADM PEND 2018', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ADM PEND 2019', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('INTERESES EST. MET.', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('INT INST ELECT 2014', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('ADM PEND 2020 (ENE-JUL)', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('PUESTOS DE MADERA', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('TRAMITE CERT. DEFENSA CIVIL 2016', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('IMPUESTO PREDIAL 2018', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('MULTA DISCIPLINARIA', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('2DA IMPLEMENTACION COVID19', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('PAGO CUOTA MANTENM. DESAGUES', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('MULTA DISCIPLINARIA MAYO 2020', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('MULTA LIMPIEZA SEMANAL MES SETIEMBRE', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('MULTA LIMPIEZA SEMANAL MES OCTUBRE', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('MULTA LIMPIEZA SEMANAL MES NOVIEMBRE', 1, 1);
insert into categoria_operacion (descripcion, id_tipo_operacion, estado) values ('TITULO DE PUESTOS', 1, 1);







