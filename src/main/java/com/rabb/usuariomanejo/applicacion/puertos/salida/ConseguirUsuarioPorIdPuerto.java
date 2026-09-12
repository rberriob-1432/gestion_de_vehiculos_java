package com.rabb.usuariomanejo.applicacion.puertos.salida;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import com.rabb.usuariomanejo.dominio.ov.UsuarioId;
import java.util.Optional;
public interface ConseguirUsuarioPorId {
    Optional<UsuarioModelo> getById(UsuarioId usuarioId);
}