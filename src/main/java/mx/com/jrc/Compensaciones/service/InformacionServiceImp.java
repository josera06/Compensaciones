package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.dao.InformacionDAO;
import mx.com.jrc.Compensaciones.domain.Informacion;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InformacionServiceImp implements InformacionService{
    @Autowired
    private InformacionDAO informacionDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Informacion> listarInformacion() {
        return (List<Informacion>) informacionDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Informacion> listarInformacionSinArchivo() {
        return (List<Informacion>) informacionDAO.getInformationListWithOutFile();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Informacion> listarInformacionByUser(Trabajador trabajador) {
        return (List<Informacion>) informacionDAO.getFileNames(trabajador);
    }

    @Override
    @Transactional
    public void guardar(Informacion informacion) {
        informacionDAO.save(informacion);
    }

    @Override
    @Transactional
    public void eliminar(Informacion informacion) {
        informacionDAO.delete(informacion);
    }

    @Override
    @Transactional
    public void eliminar(long idInformacion) {
        informacionDAO.deleteById(idInformacion);
    }

    @Override
    @Transactional(readOnly = true)
    public Informacion encontrar(Informacion informacion) {
        return informacionDAO.findById(informacion.getIdInformacion()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existFileName(String newName){
        List <Informacion> names = informacionDAO.getInformationListWithOutFile();
        return names.stream().anyMatch(nombreArchivo -> (nombreArchivo.equals(newName)));
    }
}
