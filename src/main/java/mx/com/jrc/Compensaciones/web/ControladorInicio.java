package mx.com.jrc.Compensaciones.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.jrc.Compensaciones.domain.Rol;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import mx.com.jrc.Compensaciones.domain.Usuario;
import mx.com.jrc.Compensaciones.service.RolService;
import mx.com.jrc.Compensaciones.service.TrabajadorService;
import mx.com.jrc.Compensaciones.service.UsuarioService;
import mx.com.jrc.Compensaciones.util.EncriptarPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private TrabajadorService trabajadorService;

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/guardarTrabajador")
    public String guardaTrabajador(@Valid Trabajador trabajador, Errors errors){
        if(errors.hasErrors()){
            return "modificarTrabajador";
        }
        List<Rol> roles = new ArrayList<>();
        Rol rol = new Rol();
        rol.setNombre("ROLE_USER");
        roles.add(rol);

        var password = "123";//EncriptarPassword.generaPassword(6);
        Usuario usuario = new Usuario();
        usuario.setUsername(trabajador.getEmail());
        usuario.setPassword(EncriptarPassword.encriptarPassword(password));
        usuario.setRoles(roles);
        usuario.setTrabajador(trabajador);
        try{
            usuarioService.guardaUsuario(usuario);
        }catch (Exception e){
            log.info("Exception: " + e.getMessage());
        }

        return "redirect:/";
    }

    @PostMapping("/guardarModificacionTrabajador")
    public String guardaModificacionTrabajador(@Valid Trabajador trabajador, Errors errors){
        trabajadorService.guardar(trabajador);
        return "redirect:/";
    }

    @GetMapping("/registroTrabajador")
    public String registroInicial(Trabajador trabajador) {
        log.info("Página para registro de nuevo trabajador");
        return "registroTrabajador";
    }

    @GetMapping("/trabajador")
    public String inicioTrabajador(Model model) {
        var trabajadores = trabajadorService.listarPendientesConfirmar();

        var saldoTotal = 0D;
        for(var p:trabajadores){
            saldoTotal += p.getSueldoQuincenal();
        }
        model.addAttribute("trabajadores", trabajadores);
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalTrabajadores", trabajadores.size());
        return "trabajador";
    }

    @GetMapping("/")
    public String inicio(Trabajador trabajador,Model model, @AuthenticationPrincipal User user) {
        Usuario usuario = usuarioService.getTrabajadorByUsurario(user.getUsername());
        model.addAttribute("trabajador",usuario.getTrabajador());
        return "index";

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
