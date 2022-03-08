package mx.com.jrc.Compensaciones.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "tb_dictamen",schema = "compensaciones")
public class Dictamen {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDictamen;
    private BigDecimal importeQuincenal;
    private Date fecIni;
    private String observaciones;
    private String matricula;
    private String concepto;
    private String incUnidades;
    private BigDecimal incImporte;
    private String incQuincenaInicial;
    private String incQuincenaFinal;
    private String incNoControl;
    private String incCifraControl;
    private String incResponsableReporte;
    private String retroUnidades;
    private BigDecimal retroImporte;
    private String retroQuincenaInicial;
    private String retroQuincenaFinal;
    private String retroNoControl;
    private String retroCifraControl;
    private String retroResponsableReporte;
    private Long idTrabajador;
}
