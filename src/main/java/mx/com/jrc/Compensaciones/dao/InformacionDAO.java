package mx.com.jrc.Compensaciones.dao;

import mx.com.jrc.Compensaciones.domain.Informacion;
import mx.com.jrc.Compensaciones.domain.Solicitud;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InformacionDAO extends CrudRepository<Informacion,Long> {
    @Query("SELECT i FROM Informacion i WHERE i.trabajador = :#{#trabajador}")
    List<Informacion> getFileNames(@Param("trabajador") Trabajador trabajador);
}
