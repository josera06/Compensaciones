package mx.com.jrc.Compensaciones.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tb_solicitud",schema = "compensaciones")
public class Solicitud {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;
    private Timestamp fecha;
    private String numeroControl;
    private Long idTrabajador;
}
