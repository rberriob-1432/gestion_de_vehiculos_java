package com.rabb.usuariomanejo.dominio.enums;
import com.rabb.usuariomanejo.dominio.excepciones.InvalidoUsuarioEstatusExcepcion;

public enum UsuarioEstatus {
    ACTIVO,
    INACTIVO,
    PENDIENTE,
    BLOQUEADO;

    public static UsuarioEstatus fromString(final String value) {
        for (final UsuarioEstatus estatus : values()) {
            if (estatus.name().equalsIgnoreCase(value)) {
                return estatus;
            }
        }
        throw InvalidoUsuarioEstatusExcepcion.becauseValueIsInvalid(value);
    }
}