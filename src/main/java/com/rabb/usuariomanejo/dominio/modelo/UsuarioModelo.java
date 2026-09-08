package com.rabb.usuariomanejo.dominio.modelo;

import com.rabb.usuariomanejo.dominio.enums.UsuarioEstatus;
import com.rabb.usuariomanejo.dominio.enums.UsuarioRol;
import com.rabb.usuariomanejo.dominio.excepciones.UsuarioNombre;
import com.rabb.usuariomanejo.dominio.ov.UsuarioContraseña;
import com.rabb.usuariomanejo.dominio.ov.UsuarioCorreo;
import com.rabb.usuariomanejo.dominio.ov.UsuarioId;
import lombok.Value;

@Value
public class UsuarioModelo {

    UsuarioId id;
    UsuarioNombre nombre;
    UsuarioCorreo correo;
    UsuarioContraseña contraseña;
    UsuarioRol rol;
    UsuarioEstatus estatus;

    public static UsuarioModelo create(
            final UsuarioId id,
            final UsuarioNombre nombre,
            final UsuarioCorreo correo,
            final UsuarioContraseña contraseña,
            final UsuarioRol rol) {
        return new UsuarioModelo(id, nombre, correo, contraseña, rol, UsuarioEstatus.PENDIENTE);
    }

    public UsuarioModelo activate() {
        return new UsuarioModelo(id, nombre, correo, contraseña, rol, UsuarioEstatus.ACTIVO);
    }

    public UsuarioModelo deactivate() {
        return new UsuarioModelo(id, nombre, correo, contraseña, rol, UsuarioEstatus.INACTIVO);
    }
}