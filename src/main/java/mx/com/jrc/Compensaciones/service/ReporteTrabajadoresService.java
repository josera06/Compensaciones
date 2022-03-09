package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.domain.ReporteTrabajadoresDTO;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface ReporteTrabajadoresService {
    ReporteTrabajadoresDTO obtenerReporte(Map<String,Object> params) throws SQLException, JRException, IOException;
}
