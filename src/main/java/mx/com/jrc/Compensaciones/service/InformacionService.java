package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.domain.Informacion;
import mx.com.jrc.Compensaciones.domain.Solicitud;
import mx.com.jrc.Compensaciones.domain.Trabajador;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface InformacionService {
    public List<Informacion> listarInformacion();

    public List<Informacion> listarInformacionByUser(Trabajador trabajador);

    public void guardar(Informacion informacion);

    public void eliminar(Informacion informacion);

    public Informacion encontrar(Informacion informacion);

    public boolean existFileName(Trabajador trabajador,String newName);
}
