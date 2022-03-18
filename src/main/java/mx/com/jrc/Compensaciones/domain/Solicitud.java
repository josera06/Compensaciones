package mx.com.jrc.Compensaciones.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tb_solicitud",schema = "compensaciones")
public class Solicitud {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_trabajador")
    private Trabajador trabajador;

    private Timestamp fecha;
    private String numeroControl;
    private BigDecimal importeQuincenal;
    private Date fecIni;
    private String observaciones;
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
