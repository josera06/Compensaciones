package mx.com.jrc.Compensaciones.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb_trabajador", schema = "compensaciones")
public class Trabajador {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrabajador;

    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apPaterno;
    @NotEmpty
    private String apMaterno;
    private String matricula;
    private String categoria;
    private double sueldoQuincenal;
    private boolean horarioDiscontinuo;
    private String calle;
    private String colonia;
    private String codigoPostal;
    private String municipio;
    private String estado;
    @Email
    private String email;
    private String telefono;
    private boolean confirmado;
}
