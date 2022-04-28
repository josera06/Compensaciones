package mx.com.jrc.Compensaciones.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tb_informacion",schema = "compensaciones")
public class Informacion {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informacion")
    private Long idInformacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_trabajador")
    private Trabajador trabajador;

    private Timestamp fecha;
    private String titulo;
    private byte[] pdfFile;
    private String nombreArchivo;
    private String contentType;
    private String fileSize;

    public Informacion(Long idInformacion, Trabajador trabajador, Timestamp fecha, String titulo, String nombreArchivo, String contentType, String fileSize) {
        this.idInformacion = idInformacion;
        this.trabajador = trabajador;
        this.fecha = fecha;
        this.titulo = titulo;
        this.nombreArchivo = nombreArchivo;
        this.contentType = contentType;
        this.fileSize = fileSize;
    }

    public Informacion(Long idInformacion) {
        this.idInformacion = idInformacion;
    }

    public Informacion() {
    }

    public Informacion(Long idInformacion, Trabajador trabajador, Timestamp fecha, String titulo, byte[] pdfFile, String nombreArchivo, String contentType, String fileSize) {
        this.idInformacion = idInformacion;
        this.trabajador = trabajador;
        this.fecha = fecha;
        this.titulo = titulo;
        this.pdfFile = pdfFile;
        this.nombreArchivo = nombreArchivo;
        this.contentType = contentType;
        this.fileSize = fileSize;
    }
}
