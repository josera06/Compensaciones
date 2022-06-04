<?
	# El parámetro f=1 indica que se va a forzar a bajar el archivo
	$f=isset($_REQUEST['f'])?$_REQUEST['f']:0;
	# Recupera el id pasado como parámetro
	$id=isset($_REQUEST['id'])?$_REQUEST['id']:0;
	include_once "sitedefs.php";
	# Conexión a la base de datos
	$conn_string = "host=192.168.1.104 port=5432 dbname=dahipro user=postgres password=1234";
	$link = pg_connect($conn_string) or die(pg_last_error($link));
	# BUSCAR EL GRUPO DE PRODUCTOS
	$sql = "SELECT a.alias,b.nombre FROM tb_grupo a,tb_producto b where a.id_grupo = b.id_grupo  and id_grupo='$id'";
	echo $sql;
	$result=pg_query($link, $sql);
	$grupo = "";
	$nombre = "";
	if($result){
		while ($row=pg_fetch_array($result)){
			$grupo .= "$row[alias]";
			$nombre .= "$row[nombre]";
		}
	}	

	
	pg_free_result($result);
	/*# Recupera los atributos del archivo
	$row=pg_fetch_array($result,0);
	pg_free_result($result);
	# Para determinar si archivo a bajar fue ingresado al campo archivo_oid (es de tipo "oid")
	$isoid=false;
	if($row['archivo_bytea']==-1) $isoid=true;
	if($row['archivo_oid']==-1) $isoid=true;
	if($row['archivo_bytea']==-1 && $row['archivo_oid']==-1) die('No existe el archivo para mostrar o bajar');
	if($isoid){
		# Inicia la transacción
		pg_query($link, "begin");
		# Abre el objeto blob
		$file=pg_lo_open($link, $row['archivo_oid'], "r");
	}
	else{
		# Hace el proceso inverso a pg_escape_bytea, para que el archivo esté en su estado original
		$file=pg_unescape_bytea($row['archivo_bytea']);
	}
	# Envío de cabeceras
	header("Cache-control: private");
	header("Content-type: $row[mime]");
	if($f==1)
		header("Content-Disposition: attachment; filename=\"$row[nombre]\"");
	header("Content-length: $row[size]");
	header("Expires: ".gmdate("D, d M Y H:i:s", mktime(date("H")+2, date("i"), date("s"), date("m"), date("d"), date("Y")))." GMT");
	header("Last-Modified: ".gmdate("D, d M Y H:i:s")." GMT");
	header("Cache-Control: no-cache, must-revalidate");
	header("Pragma: no-cache");
	
	if($isoid){
		# Imprime el contenido del objeto blob
		pg_lo_read_all($file);
		# Cierra el objeto
		pg_lo_close($file);
		# Compromete la transacción
		pg_query($link, "commit");
	}
	else{
		# Imprime el contenido del archivo
		print $file;
	}*/
	pg_close($link);	
?>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>DAHIPRO - MEGAMAX</title>
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/style1.css" />
<link rel="stylesheet" type="text/css" href="pro_dropdown_3/pro_dropdown_3.css" />
<link rel="stylesheet" type="text/css" href="css/menuVertical.css" />
<link rel="stylesheet" type="text/css" href="css/productos.css" />
<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.easing.js"></script>
<script language="javascript" type="text/javascript" src="js/script.js"></script>


</head>
<body>
	<div id="container">
	  <h1><img src="img/logodahiproExtendido.png" width="980" height="128" alt="DAHIPRO" /></h1>
      <SCRIPT LANGUAGE="JavaScript" src="js/menuH.js"></SCRIPT>      
      <img src="img/encabezadoProductos01.png" width="980" height="340" alt="DAHIPRO" />
<!-------------------------------- THE CONTENT------------------------------------------------->
<img src="img/barraHorizontal.png" width="980" height="20" alt="barra" />
<div class="TituloAreas"><?=$grupo?></div>
<table width="978" border="0">
	<tr height="340">
    	<td><SCRIPT LANGUAGE="JavaScript" src="js/menu.js"></SCRIPT></td>
<td>
<!----------------------------------tabla anidada-------------------------------------------->
<table border="0">
  <tr height="50">
    <td width="590" class="descripcionProducto" valign="top">
    	<div class="nombreProductoGeneral"><?=$nombre?></div>
        <p class="descripcionProducto">
        Megamax fue creado para proporcionar una limpieza profunda en superficies con manchas difíciles y severas además de eliminar malos olores. Permite obtener un agradable aroma y neutralizar por un tiempo determinado los malos olores que se pudieran generar en el área. Este producto se puede utilizar en interiores y exteriores.     
        </p>
    </td>
    <td><a href="ProdMegaMax01.html"><img src="img/Megamax/HH-Megamax_kit_ok.png" width="152" height="123" alt="MEGAMAX" /></a></td>
  </tr>
  <tr height="50">
    <td width="590" class="descripcionProducto" valign="top">
    	<div class="nombreProductoGeneral">SUCCOMBER</div>
        <p class="descripcionProducto">
        Succomber es un limpiador, desincrustante y sarricida para sanitarios, mingitorios y lavabos. Elaborado con ácidos y detergentes que remueven y limpian los depósitos de sarro, moho, algas, sales, manchas y natas que los limpiadores comunes no eliminan, clasificado como un producto para mantenimiento correctivo.
        </p>
    </td>
    <td><a href="ProdSuccomber01.html"><img src="img/Succomber/HH-Succomber_kit_ok.png" width="152" height="123" alt="MEGAMAX" /></a></td>
  </tr>
    <tr height="50">
    <td width="590" height="125" valign="top" class="descripcionProducto">
    	<div class="nombreProductoGeneral">SEPER</div>
    	Hola esta es la descripción del producto denominado SEPER AKLJHDF KLASJDFH LSAKJDFH ASJDFHIOUYJHLKjshdlkjhsdlkASHD LKAsdhl kjhlkjhlkajsdfhlkasjdhflksajdhf jhflaksjdfh laskdjfhlksdjfh salkjdfh
    <td><a href="ProdCeper01.html"><img src="img/Seper/HH-Seper_kit_ok.png" width="152" height="123" alt="MEGAMAX" /></a></td>
  </tr>
</table> 
<!------------------------------------------------------------------------------>
</td>
    </tr>
</table>
 <br/>
 <img src="img/piePagina01.png" width="980" height="100" alt="DAHIPRO" />
 <!--------------------------- END OF THE CONTENT --------------------------------------->
 <p> 
    </div>
</body>
</html>
