package mx.com.jrc.Compensaciones.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@Table(name="tb_rol",schema="compensaciones")
public class Rol implements Serializable {
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @NotEmpty
    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

}
