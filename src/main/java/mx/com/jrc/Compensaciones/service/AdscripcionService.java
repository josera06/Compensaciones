package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.domain.Adscripcion;

import java.util.List;

public interface AdscripcionService {
    public List<Adscripcion> listarAdscripiones();

    public void guardar(Adscripcion adscripcion);

    public void eliminar(Adscripcion adscripcion);

    public Adscripcion encontrar(Adscripcion adscripcion);
}
