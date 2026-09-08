package com.rabb.usuariomanejo.dominio.enums;

import com.rabb.usuariomanejo.dominio.excepciones.InvalidoUsuarioEstatusExcepcion;

public enum UsuarioRol {
    ADMINISTRADOR,
    OPERADOR,
    MIEMBRO;

    public static UsuarioRol fromString(final String value) {
        for (final UsuarioRol rol : values()) {
            if (rol.name().equalsIgnoreCase(value)) {
                return rol;
            }
        }
        throw InvalidoUsuarioEstatusExcepcion.becauseValueIsInvalid(value);
    }
}