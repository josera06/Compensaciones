package mx.com.jrc.Compensaciones.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.jrc.Compensaciones.domain.Solicitud;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import mx.com.jrc.Compensaciones.service.SolicitudService;
import mx.com.jrc.Compensaciones.service.TrabajadorService;
import mx.com.jrc.Compensaciones.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
@Slf4j
public class ControladorSolicitud {
    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/solicitud")
    public String inicioSolicitud(Model model) {
        var solicitudes = solicitudService.listarSolicitud();
        model.addAttribute("solicitudes", solicitudes);
        return "solicitud";
    }

    @PostMapping("/guardarSolicitud")
    public String guardaSolicitud(@Valid Solicitud solicitud,@AuthenticationPrincipal User user){
        var usuario = usuarioService.getTrabajadorByUsurario(user.getUsername());
        solicitud.setFecha(new java.sql.Timestamp(new Date().getTime()));
        //solicitud.setIdTrabajador(usuario.getIdTrabajador());
        solicitudService.guardar(solicitud);
        return "redirect:/solicitud";
    }
}
