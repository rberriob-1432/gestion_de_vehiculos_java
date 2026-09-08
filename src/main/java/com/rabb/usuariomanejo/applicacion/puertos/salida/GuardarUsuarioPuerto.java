package com.rabb.usuariomanejo.applicacion.puertos.salida;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
public interface GuardarUsuarioPuerto {
    UsuarioModelo save(UsuarioModelo usuarioModelo);
}