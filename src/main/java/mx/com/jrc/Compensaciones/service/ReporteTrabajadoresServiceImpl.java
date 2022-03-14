package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.domain.ReporteTrabajadoresDTO;
import mx.com.jrc.Compensaciones.util.JasperReportManager;
import mx.com.jrc.Compensaciones.util.TipoReporteEnum;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Service
public class ReporteTrabajadoresServiceImpl implements ReporteTrabajadoresService{

    @Autowired
    private JasperReportManager jasperReportManager;

    @Autowired
    private DataSource dataSource;

    @Override
    public ReporteTrabajadoresDTO obtenerReporte(Map<String, Object> params) throws SQLException, JRException, IOException {
        //String filename="listaTrabajadores";
        var filename = params.get("filename").toString();
        String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx" : ".pdf";
        var dto = new ReporteTrabajadoresDTO();
        dto.setFileName(filename+extension);
        ByteArrayOutputStream stream = jasperReportManager.export(filename,params.get("tipo").toString(),params,dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);
        return dto;
    }
}
