package mx.com.jrc.Compensaciones.dao;

import mx.com.jrc.Compensaciones.domain.Solicitud;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitudDAO extends CrudRepository<Solicitud,Long> {
    @Query("SELECT s FROM Solicitud s WHERE s.trabajador = :#{#trabajador}")
    List<Solicitud> getSolicitudesPorTrabajador(@Param("trabajador") Trabajador trabajador);
}
