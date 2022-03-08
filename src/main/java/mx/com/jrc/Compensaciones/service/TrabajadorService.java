package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.domain.Solicitud;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrabajadorService {
    public List<Trabajador> listarTrabajador();

    public List<Trabajador> listarPendientesConfirmar();

    public void confirmarTrabajador(Trabajador trabajador);

    public void guardar(Trabajador trabajador);

    public void eliminar(Trabajador trabajador);

    public Trabajador encontrar(Trabajador trabajador);

}
