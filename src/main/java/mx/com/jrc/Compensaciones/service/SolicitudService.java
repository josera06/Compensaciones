package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.domain.Solicitud;
import mx.com.jrc.Compensaciones.domain.Trabajador;

import java.util.List;

public interface SolicitudService {
    public List<Solicitud> listarSolicitud();

    public List<Solicitud> listarSolicitudPorTrabajador(Trabajador trabajador);

    public void guardar(Solicitud solicitud);

    public void eliminar(Solicitud solicitud);

    public Solicitud encontrar(Solicitud solicitud);
}
