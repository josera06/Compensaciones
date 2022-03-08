package mx.com.jrc.Compensaciones.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.jrc.Compensaciones.dao.AdscriprionDAO;
import mx.com.jrc.Compensaciones.dao.TrabajadorDAO;
import mx.com.jrc.Compensaciones.domain.Adscripcion;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import mx.com.jrc.Compensaciones.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private TrabajadorService trabajadorService;

    @GetMapping("/registroTrabajador")
    public String registroInicial(Trabajador trabajador) {
        log.info("Página para registro de nuevo trabajador");
        return "registroTrabajador";
    }

    @GetMapping("/trabajador")
    public String inicioTrabajador(Model model) {
        var trabajadores = trabajadorService.listarPendientesConfirmar();
        log.info("Ejecutando el controlador Spring MVC");
        model.addAttribute("trabajadores", trabajadores);
        return "trabajador";
    }

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        log.info("usuario que hizo login: " + user);
        return "index";
    }

    @PostMapping("/guardarRegistroTrabajador")
    public String guardaRegistroTrabajador(@Valid Trabajador trabajador, Errors errors){
        if(errors.hasErrors()){
            return "registroTrabajador";
        }
        trabajadorService.guardar(trabajador);
        return "redirect:/login";
    }

    @PostMapping("/guardarTrabajador")
    public String guardaTrabajador(@Valid Trabajador trabajador, Errors errors){
        if(errors.hasErrors()){
            return "modificarTrabajador";
        }
        trabajadorService.guardar(trabajador);
        return "redirect:/";
    }

    @GetMapping("/editarTrabajador/{idTrabajador}")
    public String editarTrabajador(Trabajador trabajador,Model model){
        trabajador = trabajadorService.encontrar(trabajador);
        model.addAttribute("trabajador",trabajador);
        return "modificarTrabajador";
    }

    @GetMapping("/eliminarTrabajador/{idTrabajador}")
    public String eliminarTrabajador(Trabajador trabajador){
        trabajadorService.eliminar(trabajador);
        return "redirect:/";
    }

    @GetMapping("/agregarTrabajador")
    public String agregarTrabajador(Trabajador trabajador){
        return "modificarTrabajador";
    }

    @GetMapping("/confirmarTrabajador/{idTrabajador}")
    public String confirmarTrabajador(Trabajador trabajador){
        trabajador = trabajadorService.encontrar(trabajador);
        trabajador.setConfirmado(true);
        log.info("Confirmando trabajador: " + trabajador.toString());
        trabajadorService.confirmarTrabajador(trabajador);
        return "redirect:/";
    }
}
