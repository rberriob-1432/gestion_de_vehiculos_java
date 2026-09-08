package com.rabb.usuariomanejo.applicacion.puertos.salida;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import java.util.List;

public interface ConseguirTodosUsuariosPuerto {
    List<UsuarioModelo> getAll();
}