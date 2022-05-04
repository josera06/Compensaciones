package mx.com.jrc.Compensaciones.exceptions;

import mx.com.jrc.Compensaciones.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    @Autowired
    UsuarioService usuarioService;

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(MaxUploadSizeExceededException exc, RedirectAttributes redirectAttributes, @AuthenticationPrincipal User user) {
        var usuario = usuarioService.getTrabajadorByUsurario(user.getUsername());
        redirectAttributes.addFlashAttribute("messageError","El archivo excede el tamaño permitido (1M)");
        return "redirect:/informacion/"+usuario.getTrabajador().getIdTrabajador();
    }
}
