package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.domain.Rol;


import java.util.List;

public interface RolService {
    public List<Rol> listarSolicitud();

    public void guardar(Rol rol);

    public void eliminar(Rol rol);

    public Rol encontrar(Rol rol);
}
