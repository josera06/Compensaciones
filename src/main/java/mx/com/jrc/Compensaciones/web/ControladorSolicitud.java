package mx.com.jrc.Compensaciones.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.jrc.Compensaciones.domain.Solicitud;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import mx.com.jrc.Compensaciones.service.SolicitudService;
import mx.com.jrc.Compensaciones.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class ControladorSolicitud {
    @Autowired
    private SolicitudService solicitudService;

    @GetMapping("/solicitud")
    public String inicioSolicitud(Model model) {
        var solicitudes = solicitudService.listarSolicitud();
        model.addAttribute("solicitudes", solicitudes);
        return "solicitud";
    }

    @PostMapping("/guardarSolicitud")
    public String guardaSolicitud(@Valid Solicitud solicitud){
        solicitudService.guardar(solicitud);
        return "redirect:/";
    }
}
