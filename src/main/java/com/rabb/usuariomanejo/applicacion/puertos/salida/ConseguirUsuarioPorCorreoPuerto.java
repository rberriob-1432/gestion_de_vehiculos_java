package com.rabb.usuariomanejo.applicacion.puertos.salida;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import com.rabb.usuariomanejo.dominio.ov.UsuarioCorreo;
import java.util.Optional;
public interface ConseguirUsuarioPorCorreoPuerto {
    Optional<UsuarioModelo> getByEmail(UsuarioCorreo correo);
}