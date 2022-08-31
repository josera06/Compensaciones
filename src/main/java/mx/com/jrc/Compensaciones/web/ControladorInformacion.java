package mx.com.jrc.Compensaciones.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.jrc.Compensaciones.domain.Informacion;
import mx.com.jrc.Compensaciones.domain.Solicitud;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import mx.com.jrc.Compensaciones.service.InformacionService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        model.addAttribute("informacion",informacionService.listarInformacionSinArchivo());
        return "informacion";
    }

    @PostMapping("/guardarInformacion")
    public String addFile(Informacion informacion, @RequestParam("fileUpload") MultipartFile fileUpload, @AuthenticationPrincipal User user, RedirectAttributes ra) throws IOException {
        var usuario = usuarioService.getTrabajadorByUsurario(user.getUsername());
        String message = "";

        if (informacionService.existFileName(fileUpload.getOriginalFilename())) {
            message = "Este archivo ya existe";
        } else if (!fileUpload.getContentType().equalsIgnoreCase("application/pdf")){
            message = "Error al guardar: Solo se permiten archivos con formato PDF";
        }else {
            log.info("Entrando");
            String fileName = StringUtils.cleanPath(fileUpload.getOriginalFilename());
            informacion.setFecha(new java.sql.Timestamp(new Date().getTime()));
            informacion.setNombreArchivo(fileName);
            informacion.setPdfFile(fileUpload.getBytes());
            informacion.setFileSize(fileUpload.getSize() + "");
            informacion.setContentType(fileUpload.getContentType());
            informacion.setTrabajador(usuario.getTrabajador());

            log.info("Información a guardada: " + informacion);
            informacionService.guardar(informacion);
            message = "El archivo se ha guardado correctamente.";
        }
        if(message.equals("Error al guardar: Solo se permiten archivos con formato PDF")){
            ra.addFlashAttribute("messageError", message);
        } else {
            ra.addFlashAttribute("message", message);
        }

        ra.addFlashAttribute("informacion",informacionService.listarInformacionSinArchivo());
        return "redirect:/informacion/"+usuario.getTrabajador().getIdTrabajador();
    }

    @GetMapping("/descargaInformacion/{idInformacion}")
    public void download(Informacion informacion, HttpServletResponse response) throws Exception {
        informacion = informacionService.encontrar(informacion);

        if (informacion == null) {
            throw new Exception("Could not find document with Id: " + informacion.getIdInformacion());
        }

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValues = "attachment; filename=" + informacion.getNombreArchivo();

        response.setHeader(headerKey, headerValues);

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(informacion.getPdfFile());
        outputStream.close();
    }

    @GetMapping("/eliminarInformacion/{idInformacion}")
    public String eliminarSolicitud(@PathVariable("idInformacion") long idInformacion, @AuthenticationPrincipal User user, RedirectAttributes ra){
        var usuario = usuarioService.getTrabajadorByUsurario(user.getUsername());
        informacionService.eliminar(idInformacion);
        ra.addFlashAttribute("informacion",informacionService.listarInformacionSinArchivo());
        return "redirect:/informacion/"+usuario.getTrabajador().getIdTrabajador();
    }
}
