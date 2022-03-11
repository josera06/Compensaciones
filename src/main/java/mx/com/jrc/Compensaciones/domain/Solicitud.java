package mx.com.jrc.Compensaciones.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tb_solicitud",schema = "compensaciones")
public class Solicitud {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;
    private Long idTrabajador;
    private Timestamp fecha;
    private String numeroControl;
    private BigDecimal importeQuincenal;
    private Date fecIni;
    private String observaciones;
    private String matricula;
    private String concepto;
    private String incUnidades;
    private double incImporte;
    private String incQuincenaInicial;
    private String incQuincenaFinal;
    private String incNoControl;
    private String incCifraControl;
    private String incResponsableReporte;
    private String retroUnidades;
    private double retroImporte;
    private String retroQuincenaInicial;
    private String retroQuincenaFinal;
    private String retroNoControl;
    private String retroCifraControl;
    private String retroResponsableReporte;
}
