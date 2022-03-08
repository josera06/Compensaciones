package mx.com.jrc.Compensaciones.dao;

import mx.com.jrc.Compensaciones.domain.Solicitud;
import org.springframework.data.repository.CrudRepository;

public interface SolicitudDAO extends CrudRepository<Solicitud,Long> {
}
