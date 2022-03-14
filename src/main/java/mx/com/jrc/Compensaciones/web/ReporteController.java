package mx.com.jrc.Compensaciones.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.jrc.Compensaciones.service.ReporteTrabajadoresService;
import mx.com.jrc.Compensaciones.util.TipoReporteEnum;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReporteController {
    //http://localhost:8080/report/ventas/download?confirmado=true&tipo=PDF
    @Autowired
    private ReporteTrabajadoresService reporteTrabajadoresService;

    @GetMapping(path="/ventas/download")
    public ResponseEntity<Resource> download(@RequestParam Map<String,Object> params) throws JRException, SQLException, IOException {
        var parametro = params.get("confirmado");
        Boolean confirmado = false;
        log.info("Parametro: " + parametro.toString());
        if(parametro.equals("true"))
            confirmado = true;
        params.put("confirmado",confirmado);
        log.info("Confirmado class: " + confirmado.getClass());
        log.info("Confirmado parametro class: " + params.get("confirmado").getClass());
        log.info("Mapa de parametros: " + params.toString());

        var dto = reporteTrabajadoresService.obtenerReporte(params);
        var streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if(params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())){
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }else{
            mediaType = MediaType.APPLICATION_PDF;
        }
        return ResponseEntity.ok().header("Content-Disposition","inline: filename=\"" + dto.getFileName()+"\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);

    }
}
