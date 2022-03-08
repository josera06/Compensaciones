package mx.com.jrc.Compensaciones.dao;

import mx.com.jrc.Compensaciones.domain.Trabajador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrabajadorDAO extends CrudRepository<Trabajador,Long> {

    @Query("SELECT t FROM Trabajador t WHERE t.confirmado = false")
    List<Trabajador> getPendientesConfirmar();
}
