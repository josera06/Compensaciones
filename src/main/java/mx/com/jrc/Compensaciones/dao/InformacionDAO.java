package mx.com.jrc.Compensaciones.dao;

import mx.com.jrc.Compensaciones.domain.Informacion;
import mx.com.jrc.Compensaciones.domain.Solicitud;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InformacionDAO extends CrudRepository<Informacion,Long> {
    @Query("SELECT i FROM Informacion i WHERE i.trabajador = :#{#trabajador}")
    List<Informacion> getFileNames(@Param("trabajador") Trabajador trabajador);

    public static final String FIND_PROJECTS = "SELECT id_informacion, id_trabajador, fecha, titulo,null as pdf_file,nombre_archivo,content_type,file_size FROM compensaciones.tb_informacion";

    @Query(value = FIND_PROJECTS, nativeQuery = true)
    List<Informacion> getInformationListWithOutFile();

    @Modifying
    @Query("DELETE FROM Informacion i WHERE i.idInformacion = :id_informacion")
    void deleteById(@Param("id_informacion") long id_informacion);

}
