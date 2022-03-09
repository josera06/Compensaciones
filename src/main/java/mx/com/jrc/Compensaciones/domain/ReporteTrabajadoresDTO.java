package mx.com.jrc.Compensaciones.domain;

import lombok.Data;

import java.io.ByteArrayInputStream;

@Data
public class ReporteTrabajadoresDTO {
    private String fileName;
    private ByteArrayInputStream stream;
    private int length;
}
