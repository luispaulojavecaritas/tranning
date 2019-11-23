1) DESBLOQUEAR USUARIO HR:	

	--Desbloquea usuario HR 
	ALTER USER HR ACCOUNT UNLOCK;
	--Cambia clave para usuario HR
	ALTER USER HR IDENTIFIED BY HR;

	
2) BLOQUE ANONIMO PARA PLSQL

	--Este bloque necesita minimo una linea
	BEGIN
	//minimo una linea
	END;
	

3) VISUALIZAR SALIDA POR PANTALLA 
	
	--Esta linea va encima de cualquier bloque SQL
	SET SERVEROUTPUT ON
	BEGIN
		dbms_output.put_line(100);
		dbms_output.put_line('Es una cadena');
	END;
	

4) VARIABLES

	--Se puede definir, inicializar en el DECLARE y/o reescribir en BEGIN
	SET SERVEROUTPUT ON
	DECLARE
		name_person VARCHAR2(50);
		lastname_person VARCHAR2(50);
	BEGIN
		NAME_PERSON := 'PAULO';
		lastname_person := 'JAVE';
		dbms_output.put_line('Valores Ingresados, Nombre' || name_person || ' Apellido' || lastname_person);
	END;
    
    
    --Para ver mas tipos de datos
    https://docs.oracle.com/cd/E11882_01/server.112/e41084/sql_elements001.htm#SQLRF0021
	
	
5) CONSTANTES

	--Se define e inicializa en el DECLARE y no se puede sobre escribir en BEGIN
	DECLARE
		name_person VARCHAR2(50);
		x CONSTANT NUMBER := 10;
	BEGIN
		name_person := 'PAULO';
		dbms_output.put_line('Valores Ingresados, Nombre: ' || name_person);
		dbms_output.put_line('Constante: ' || x);
	END;	
    
6) Variables NOT NULL


	--Una variable con NOT NULL se debe inicializar en el DECLARE
	DECLARE
		x CONSTANT NUMBER := 10;
        z NUMBER NOT NULL := 20;
	BEGIN
        z := 50;
		dbms_output.put_line('Constante: ' || x);
        dbms_output.put_line('Variable not null: ' || z);
	END;
    
    
7) Variables BOOLEAN

    --Estas normalmente no estan disponibles, en bd no existen pero si en plsql, puede ser. TRUE, FALSE o NULL

    DECLARE
		x BOOLEAN;
	BEGIN
		x := TRUE;
        x := FALSE;
        x := NULL;
	END;
    
    
8) Atributo %TYPE AND %ROWTYPE

    --%TYPE=  crear una variable del mismo tipo que otro (un campo de una tabla por ejemplo)
    --%ROWTYPE = para declarar un registro con los campos de otro

    DECLARE
        id_employee_var employees.employee_id%TYPE;
        salary_var employees.salary%TYPE;
        
	BEGIN
        salary_var := 9000;       
        id_employee_var := 999999;
        dbms_output.put_line('El ID de empleado es : ' || id_employee_var || ' y su salio es: ' || salary_var);
	END;
    
    
    
    
    DECLARE
        v_emp employees%ROWTYPE;
    BEGIN
        v_emp.id_employee_var :=10;
        v_emp.salary :=9000;
    END; 
    
9) Operadores en PLSQL %TYPE

    --Operadores mas Utilizados en PLSQL

    Operador de asignaci�n	    
        :=      (dos puntos + igual)
    Operadores aritm�ticos	    
        +       (suma)
        -       (resta)
        *       (multiplicaci�n)
        /       (divisi�n)
        **      (exponente)
    Operadores relacionales o de comparaci�n	
        =       (igual a)
        <> o != (distinto de)
        <       (menor que)
        >       (mayor que)
        >=      (mayor o igual a)
        <=      (menor o igual a)
    Operadores l�gicos	
        AND     (y l�gico)
        NOT     (negacion)
        OR      (o l�gico)
        IN      (contenido en un conjunto)
    Operador de concatenaci�n	
        ||

    DECLARE
        a NUMBER := 5;
        b NUMBER := 10;
        c NUMBER := 2;
        d DATE := '10-11-1991';
	BEGIN
        dbms_output.put_line('Suma : ' || (a+b));
        dbms_output.put_line('Resta : ' || (b-a));
        dbms_output.put_line('Multiplicacion : ' || (a*b));
        dbms_output.put_line('Division : ' || (b/a));
        dbms_output.put_line('Exponente : ' || (a**2));     
        dbms_output.put_line('Sumar un dia : ' || (d+30));  
	END;
    
    --Mas operadores en:
    https://docs.oracle.com/cd/E19253-01/819-6957/chp-typeopexpr/index.html
    
    
9) Bloques anidados

    --Es otro estructura de plsql dentro de un bloque, estos bloques hijos osn independientes de los bloques padres
    --Los bloques hijos heredan cosas del padre y se puede ejecutar distintos bloques hjos en funcion de condiciones 
    --como los if, else entro otros.
    
    BEGIN
        dbms_output.put_line('INICIO DEL BLOQUE PADRE');  
        DECLARE
            x NUMBER := 20;
        BEGIN
            dbms_output.put_line('Numero del Hijo: '|| x);  
        END;
    END;
    

9) Ambito de variables de bloques anidados

    --Variables Globales: en el padre
    --Variables loacales: en el hijo
    --En el hijo primero se busca las variables sino encuentra las busca en el global
    
    DECLARE
        x NUMBER := 20;  --Variable global
    BEGIN
        dbms_output.put_line('Numero del padre: '|| x); 
        DECLARE
            x NUMBER := 10;
        BEGIN
            dbms_output.put_line('Numero del Hijo: '|| x);  
        END;
    END;
    
10) Funciones SQL
    
    --En Oracle PLSQL se puede incorporar todas las funciones conocidas en SQL
    --Las funcniones SQL se ejecutan en el motor SQL y las funciones PLSQL en el motor PLSQL
    --En psql la sfunciones de fila  no coincide con las de grupo por ejemplo grupo con avg, tampoco DECODE (CASE)
    
    https://docs.oracle.com/cd/E11882_01/server.112/e41084/functions.htm#SQLRF006
    
    DECLARE
        nombre VARCHAR2(50) := 'Luis Paulo Jave';
        cadena VARCHAR2(50);
        nombremayus VARCHAR2(50);
    BEGIN
        cadena := SUBSTR(nombre, 1, 4);
        nombremayus := upper(cadena);
        DBMS_OUTPUT.PUT_LINE('Funcion substr: ' || cadena);
        DBMS_OUTPUT.PUT_LINE('Funcion upper: ' || nombremayus);
    END;
    
    
    DECLARE
        FEC_NAC DATE;
        DIA_SEMANA VARCHAR2(100);
    BEGIN
        FEC_NAC:=TO_DATE('26/03/1991');
        DIA_SEMANA:=TO_CHAR(FEC_NAC,'DAY');
        DBMS_OUTPUT.PUT_LINE(DIA_SEMANA);
    END;
    
11) Flujo de Control alternativo IF, Else, Elseif, end if

    -- Nivel 01
	IF <condici�n> THEN
        Instrucciones;
        ...;
    END IF;
    
    -- Nivel 02
    IF <condici�n> THEN
        Instrucciones;
        ...;
    ELSE
        Instrucciones;
        ...;
    END IF;
    
    -- Nivel 03
    IF <condici�n> THEN
        Instrucciones;
        ...;
    ELSIF <condici�n2> THEN
        Instrucciones;
        ...;
    ELSIF <condici�n3> THEN
        Instrucciones;
        ...;
    ELSE
        Instrucciones;
        ...;
    END IF;
    
    
    
    DECLARE
        X NUMBER := 6;
    BEGIN
        IF X <= 5 THEN
            DBMS_OUTPUT.PUT_LINE('El X ES MENOR O IGUAL A 5');
        ELSE
            DBMS_OUTPUT.PUT_LINE('El X TIENE COMO VALOR EL ' || X);
        END IF;
    END;
    
    
        
    -- PRACTICA PAR IMPAR
    DECLARE
        VALOR NUMBER;
        RESULTADO NUMBER;
    BEGIN
        VALOR :=10;
        RESULTADO := MOD(VALOR, 2);
        IF RESULTADO = 0 THEN
            DBMS_OUTPUT.PUT_LINE('PAR');
        ELSE
            DBMS_OUTPUT.PUT_LINE('IMPAR');
        END IF;
    END;
    /

    --PR�CTICA TIPO PRODUCTO
    SET SERVEROUTPUT ON
    DECLARE
      TIPO_PRODUCTO CHAR(1);
    BEGIN
      TIPO_PRODUCTO:=UPPER('A');
    IF TIPO_PRODUCTO='A' THEN 
        DBMS_OUTPUT.PUT_LINE('ELECTR�NICA');
    ELSIF TIPO_PRODUCTO='B' THEN 
        DBMS_OUTPUT.PUT_LINE('INFORM�TICA');
    ELSIF TIPO_PRODUCTO='C' THEN 
        DBMS_OUTPUT.PUT_LINE('ROPA');
    ELSIF TIPO_PRODUCTO='D' THEN 
        DBMS_OUTPUT.PUT_LINE('M�SICA');
    ELSIF TIPO_PRODUCTO='E' THEN 
        DBMS_OUTPUT.PUT_LINE('LIBRO');
    ELSE
        DBMS_OUTPUT.PUT_LINE('TIPO DE PRODUCTO ');
    END IF;
    END;
    

12) Flujo de Control alternativo Case 
    --Es un comando similar al switch de java

    CASE [valor_a_comparar]    
        WHEN [valor1 | condicion1] THEN {sentencias;}
        WHEN [valor2 | condicion2] THEN {sentencias;}:
        ELSE {sentencias};    
    END CASE;
    
    
    DECLARE
        nota CHAR(1);
    BEGIN
        nota := 'C';
        
        CASE nota
            WHEN 'A' THEN DBMS_OUTPUT.PUT_LINE('EXCELENTE');
            WHEN 'B' THEN DBMS_OUTPUT.PUT_LINE('REGULAR');
            WHEN 'C' THEN DBMS_OUTPUT.PUT_LINE('MALO');
            ELSE DBMS_OUTPUT.PUT_LINE('N.A');
        END CASE;       
    END;
    
    
13) Comando SEARCHED CASE    
    --Permite hacer mejores comparaciones
    
    DECLARE
        cantidad NUMBER;
    BEGIN
        cantidad := 5;
        
        CASE 
            WHEN cantidad < 10 THEN DBMS_OUTPUT.PUT_LINE('Bajo');
            WHEN cantidad >= 10 and  cantidad <= 20 THEN DBMS_OUTPUT.PUT_LINE('Medio');
            WHEN cantidad > 20 THEN DBMS_OUTPUT.PUT_LINE('Alto');
            ELSE DBMS_OUTPUT.PUT_LINE('N.A');
        END CASE;       
    END;
    
    
        
    SET SERVEROUTPUT ON
    DECLARE
      USUARIO VARCHAR2(30);
    BEGIN
      USUARIO:=USER;  
    CASE USUARIO
      WHEN 'SYS' THEN DBMS_OUTPUT.PUT_LINE('ERES SUPERADMINISTRADOR');
      WHEN 'SYSTEM' THEN DBMS_OUTPUT.PUT_LINE('ERES ADMINISTRADOR NORMAL');
      WHEN 'HR' THEN DBMS_OUTPUT.PUT_LINE('ERES DE RECURSOS HUMANOS');
      ELSE DBMS_OUTPUT.PUT_LINE('USUARIO NO AUTORIZADO');
    END CASE;
    END;    
    
    

14) Bucle LOOP
    --Utilice el LOOP para realizar procesamiento interactivo basado en escogencias l�gicas. La sintaxis com�n es
    --Exit = permite terminar el bluque del loop
    --Aplica: EXIT WHEN..... (RECOMENDADO)
    
    LOOP
      Instrucciones;
      ...;
      EXIT WHEN <condici�n>;
      Instrucciones;
      ...;
    END LOOP;
    
    
    DECLARE
        X NUMBER:= 1;
    BEGIN
        LOOP
        DBMS_OUTPUT.PUT_LINE('VALOR DE X: ' || X);
        X:=X+1;
        IF X = 21 THEN
            EXIT;
        END IF;
        END LOOP;
    END;
    
    
    
    DECLARE
        X NUMBER:= 1;
    BEGIN
        LOOP
        DBMS_OUTPUT.PUT_LINE('VALOR DE X: ' || X);
        X:=X+1;
        EXIT WHEN X = 11 ;
        END LOOP;
    END;
        
        

    


    
    




