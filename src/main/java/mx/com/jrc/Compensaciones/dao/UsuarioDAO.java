package mx.com.jrc.Compensaciones.dao;

import mx.com.jrc.Compensaciones.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuario,Long> {

    Usuario findByUsername(String username);

}
