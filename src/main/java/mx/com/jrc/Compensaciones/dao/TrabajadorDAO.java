package mx.com.jrc.Compensaciones.dao;

import mx.com.jrc.Compensaciones.domain.Trabajador;
import mx.com.jrc.Compensaciones.domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrabajadorDAO extends CrudRepository<Trabajador,Long> {

    @Query("SELECT t FROM Trabajador t WHERE t.confirmado = false")
    List<Trabajador> getPendientesConfirmar();

    @Query("SELECT t FROM Trabajador t WHERE t.idTrabajador = :#{#usuario.idTrabajador}")
    Trabajador getTrabajadorByUsuario(@Param("usuario") Usuario usuario);

    @Query("SELECT t FROM Trabajador t WHERE t.email = :#{#trabajador.email}")
    Trabajador getTrabajadorByEmail(@Param("trabajador") Trabajador trabajador);
}
