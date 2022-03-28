package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.dao.TrabajadorDAO;
import mx.com.jrc.Compensaciones.domain.Trabajador;
import mx.com.jrc.Compensaciones.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrabajadorServiceImpl implements TrabajadorService{

    @Autowired
    private TrabajadorDAO trabajadorDAO;

    @Override
    @Transactional(readOnly = true)
    public Trabajador existeTrabajadorPorEmail(Trabajador trabajador){
        return trabajadorDAO.getTrabajadorByEmail(trabajador);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Trabajador> listarTrabajador() {
        return (List<Trabajador>) trabajadorDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Trabajador> listarPendientesConfirmar() {
        return (List<Trabajador>) trabajadorDAO.getPendientesConfirmar();
    }

    @Override
    @Transactional
    public void confirmarTrabajador(Trabajador trabajador) {
        trabajadorDAO.save(trabajador);
    }

    @Override
    @Transactional
    public void guardar(Trabajador trabajador) {
        trabajadorDAO.save(trabajador);
    }

    @Override
    @Transactional
    public void eliminar(Trabajador trabajador) {
        trabajadorDAO.delete(trabajador);
    }

    @Override
    @Transactional(readOnly = true)
    public Trabajador encontrar(Trabajador trabajador) {
        return trabajadorDAO.findById(trabajador.getIdTrabajador()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Trabajador encontrar(Usuario usuario) {
        return trabajadorDAO.getTrabajadorByUsuario(usuario);
    }

}
