package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.dao.SolicitudDAO;
import mx.com.jrc.Compensaciones.domain.Solicitud;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService{

    @Autowired
    private SolicitudDAO solicitudDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Solicitud> listarSolicitudPorTrabajador(Trabajador trabajador) {
        return (List<Solicitud>) solicitudDAO.getSolicitudesPorTrabajador(trabajador);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Solicitud> listarSolicitud() {
        return (List<Solicitud>) solicitudDAO.findAll();
    }

    @Override
    @Transactional
    public void guardar(Solicitud solicitud) {
        solicitudDAO.save(solicitud);
    }

    @Override
    @Transactional
    public void eliminar(Solicitud solicitud) {
        solicitudDAO.delete(solicitud);
    }

    @Override
    @Transactional(readOnly = true)
    public Solicitud encontrar(Solicitud solicitud) {
        return solicitudDAO.findById(solicitud.getIdSolicitud()).orElse(null);
    }
}
