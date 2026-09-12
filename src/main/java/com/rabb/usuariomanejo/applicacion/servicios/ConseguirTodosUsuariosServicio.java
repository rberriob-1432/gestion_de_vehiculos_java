package com.rabb.usuariomanejo.applicacion.servicios;
import com.rabb.usuariomanejo.applicacion.puertos.entrada.ConseguirTodosUsuariosUsoCaso;
import com.rabb.usuariomanejo.applicacion.puertos.salida.ConseguirTodosUsuariosPuerto;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ConseguirTodosUsuariosServicio
        implements ConseguirTodosUsuariosUsoCaso {

    private final ConseguirTodosUsuariosPuerto
            conseguirTodosUsuariosPuerto;

    @Override
    public List<UsuarioModelo> execute() {
        return conseguirTodosUsuariosPuerto.getAll();
    }
}

