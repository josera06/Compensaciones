package mx.com.jrc.Compensaciones.service;

import mx.com.jrc.Compensaciones.dao.DictamenDAO;
import mx.com.jrc.Compensaciones.domain.Dictamen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DictamenServiceImpl implements DictamenService{

    @Autowired
    private DictamenDAO dictamenDao;

    @Override
    @Transactional(readOnly = true)
    public List<Dictamen> listarDictamen() {
        return (List<Dictamen>) dictamenDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Dictamen dictamen) {
        dictamenDao.save(dictamen);
    }

    @Override
    @Transactional
    public void eliminar(Dictamen dictamen) {
        dictamenDao.delete(dictamen);
    }

    @Override
    @Transactional(readOnly = true)
    public Dictamen encontrar(Dictamen dictamen) {
        return dictamenDao.findById(dictamen.getIdDictamen()).orElse(null);
    }
}
