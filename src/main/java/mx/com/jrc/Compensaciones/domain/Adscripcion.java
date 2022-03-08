package mx.com.jrc.Compensaciones.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="tb_adscripcion",schema = "compensaciones")
public class Adscripcion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdscripcion;

    private String calle;
    private String colonia;
    private String codigoPostal;
    private String municipio;
    private String estado;
    private String telefono;
}
