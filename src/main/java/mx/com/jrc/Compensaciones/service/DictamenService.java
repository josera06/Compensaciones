package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.domain.Adscripcion;
import mx.com.jrc.Compensaciones.domain.Dictamen;

import java.util.List;

public interface DictamenService {
    public List<Dictamen> listarDictamen();

    public void guardar(Dictamen dictamen);

    public void eliminar(Dictamen dictamen);

    public Dictamen encontrar(Dictamen dictamen);
}
