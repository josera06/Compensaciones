package mx.com.jrc.Compensaciones.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="tb_usuario",schema="compensaciones")
public class Usuario implements Serializable {
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id_usuario")
    private List<Rol> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_trabajador", referencedColumnName = "id_trabajador")
    private Trabajador trabajador;
}
