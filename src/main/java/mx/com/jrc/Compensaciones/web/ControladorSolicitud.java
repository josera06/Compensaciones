package mx.com.jrc.Compensaciones.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.jrc.Compensaciones.domain.Solicitud;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import mx.com.jrc.Compensaciones.service.ReporteTrabajadoresService;
import mx.com.jrc.Compensaciones.service.SolicitudService;
import mx.com.jrc.Compensaciones.service.TrabajadorService;
import mx.com.jrc.Compensaciones.service.UsuarioService;
import mx.com.jrc.Compensaciones.util.TipoReporteEnum;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sun.activation.registries.LogSupport.log;

@Controller
@Slf4j
public class ControladorSolicitud {
    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TrabajadorService trabajadorService;

    @Autowired
    private ReporteTrabajadoresService reporteTrabajadoresService;

    @GetMapping("/solicitud/{idTrabajador}")
    public String inicioSolicitud(Trabajador trabajador, Model model) {
        trabajador = trabajadorService.encontrar(trabajador);
        model.addAttribute("solicitudes",solicitudService.listarSolicitudPorTrabajador(trabajador));
        return "solicitud";
    }

    @PostMapping("/guardarSolicitud")
    public String guardaSolicitud(@Valid Solicitud solicitud,@AuthenticationPrincipal User user){
        var usuario = usuarioService.getTrabajadorByUsurario(user.getUsername());
        solicitud.setFecha(new java.sql.Timestamp(new Date().getTime()));
        solicitud.setTrabajador(usuario.getTrabajador());
        ControladorSolicitud.log.info("Solicitud a guardar: " + solicitud);
        solicitudService.guardar(solicitud);
        return "redirect:/solicitud/"+usuario.getTrabajador().getIdTrabajador();
    }

    @GetMapping("/eliminarSolicitud/{idSolicitud}")
    public String eliminarSolicitud(Solicitud solicitud,@AuthenticationPrincipal User user){
        var usuario = usuarioService.getTrabajadorByUsurario(user.getUsername());
        solicitudService.eliminar(solicitud);
        return "redirect:/solicitud/"+usuario.getTrabajador().getIdTrabajador();
    }

    @GetMapping("/editarSolicitud/{idSolicitud}")
    public String editarTrabajador(Solicitud solicitud,Model model){
        solicitud = solicitudService.encontrar(solicitud);
        model.addAttribute("solicitud",solicitud);
        return "modificarSolicitud";
    }

    @GetMapping("/reporteSolicitud/{idSolicitud}")
    public ResponseEntity<Resource> download(Solicitud solicitud) throws JRException, SQLException, IOException {
        solicitud = solicitudService.encontrar(solicitud);
        ControladorSolicitud.log.info("Solicitud: " + solicitud.toString());

        Map<String,Object> params= new HashMap<String, Object>();
        params.put("confirmado",true);
        params.put("tipo","PDF");
        params.put("filename","listaTrabajadores");

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
        //return null;
    }

}
