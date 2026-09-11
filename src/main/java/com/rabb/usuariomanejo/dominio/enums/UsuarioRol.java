package com.rabb.usuariomanejo.dominio.enums;

import com.rabb.usuariomanejo.dominio.excepciones.InvalidoUsuarioEstatusExcepcion;

public enum UsuarioRol {
    ADMINISTRADOR,
    OPERADOR,
    MIEMBRO;

    public static UsuarioRol fromString(final String value) {
        for (final UsuarioRol role : values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw InvalidoUsuarioEstatusExcepcion.becauseValueIsInvalid(value);
    }
}