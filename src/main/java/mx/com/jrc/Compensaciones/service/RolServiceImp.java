package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.dao.RolDAO;
import mx.com.jrc.Compensaciones.domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RolServiceImp implements RolService{

    @Autowired
    private RolDAO rolDAO;

    @Override
    public List<Rol> listarSolicitud() {
        return (List<Rol>) rolDAO.findAll();
    }

    @Override
    public void guardar(Rol rol) {
        rolDAO.save(rol);
    }

    @Override
    public void eliminar(Rol rol) {
        rolDAO.delete(rol);
    }

    @Override
    public Rol encontrar(Rol rol) {
        return rolDAO.findById(rol.getIdRol()).orElse(null);
    }
}
