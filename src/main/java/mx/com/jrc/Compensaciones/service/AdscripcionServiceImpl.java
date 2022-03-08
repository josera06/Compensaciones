package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.dao.AdscriprionDAO;
import mx.com.jrc.Compensaciones.domain.Adscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdscripcionServiceImpl implements AdscripcionService {

    @Autowired
    private AdscriprionDAO adscriprionDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Adscripcion> listarAdscripiones() {
        return (List<Adscripcion>) adscriprionDAO.findAll();
    }

    @Override
    @Transactional
    public void guardar(Adscripcion adscripcion) {
        adscriprionDAO.save(adscripcion);
    }

    @Override
    @Transactional
    public void eliminar(Adscripcion adscripcion) {
        adscriprionDAO.delete(adscripcion);
    }

    @Override
    @Transactional(readOnly = true)
    public Adscripcion encontrar(Adscripcion adscripcion) {
        return adscriprionDAO.findById(adscripcion.getIdAdscripcion()).orElse(null);
    }
}
