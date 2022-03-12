package mx.com.jrc.Compensaciones.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "tb_trabajador", schema = "compensaciones")
public class Trabajador {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabajador")
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

    //@JoinColumn(name="id_solicitud")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Solicitud> solicitudes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
}
