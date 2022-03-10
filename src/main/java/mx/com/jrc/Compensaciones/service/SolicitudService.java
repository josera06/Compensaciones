package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.domain.Solicitud;

import java.util.List;

public interface SolicitudService {
    public List<Solicitud> listarSolicitud();

    public void guardar(Solicitud solicitud);

    public void eliminar(Solicitud solicitud);

    public Solicitud encontrar(Solicitud solicitud);
}
