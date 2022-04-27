package mx.com.jrc.Compensaciones.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.jrc.Compensaciones.domain.Informacion;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import mx.com.jrc.Compensaciones.service.InformacionService;
import mx.com.jrc.Compensaciones.service.TrabajadorService;
import mx.com.jrc.Compensaciones.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.io.IOException;
import java.util.Date;

@Controller
@Slf4j
public class ControladorInformacion {

    @Autowired
    TrabajadorService trabajadorService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    InformacionService informacionService;

    @GetMapping("/informacion/{idTrabajador}")
    public String mainInformacion(Trabajador trabajador, Model model) {
        trabajador = trabajadorService.encontrar(trabajador);
        model.addAttribute("informacion",informacionService.listarInformacionByUser(trabajador));
        return "informacion";
    }

    @PostMapping("/guardarInformacion")
    public String addFile(Informacion informacion, @RequestParam("fileUpload") MultipartFile fileUpload, @AuthenticationPrincipal User user, RedirectAttributes ra) throws IOException {
        var usuario = usuarioService.getTrabajadorByUsurario(user.getUsername());

        String message = "";
        if (fileUpload.getSize() > 1000000)  // 1MB approx (actually less though)
        {
            message = "File is too big";
        } else if (informacionService.existFileName(usuario.getTrabajador(),fileUpload.getOriginalFilename())) {
            message = "The file already exists";
        } else {
            log.info("Entrando");
            String fileName = StringUtils.cleanPath(fileUpload.getOriginalFilename());
            informacion.setFecha(new java.sql.Timestamp(new Date().getTime()));
            informacion.setNombreArchivo(fileName);
            informacion.setPdfFile(fileUpload.getBytes());
            informacion.setFileSize(fileUpload.getSize() + "");
            informacion.setContentType(fileUpload.getContentType());
            informacion.setTrabajador(usuario.getTrabajador());

            log.info("Información que será guardada: " + informacion);
            informacionService.guardar(informacion);
            message = "The file was successfully loaded.";
        }
        log.info("message: --------------------------------" + message);
        ra.addFlashAttribute("message", message);
        ra.addFlashAttribute("informacion",informacionService.listarInformacionByUser(usuario.getTrabajador()));
        return "redirect:/informacion/"+usuario.getTrabajador().getIdTrabajador();
    }
}
