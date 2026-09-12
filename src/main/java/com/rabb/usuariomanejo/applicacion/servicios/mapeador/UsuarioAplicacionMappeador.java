package com.rabb.usuariomanejo.applicacion.servicios.mapeador;
import java.util.Objects;
import com.rabb.usuariomanejo.applicacion.servicios.dto.comandos.ActualizarUsuarioComando;
import com.rabb.usuariomanejo.applicacion.servicios.dto.comandos.CrearUsuarioComando;
import com.rabb.usuariomanejo.applicacion.servicios.dto.comandos.EliminarUsuarioComando;
import com.rabb.usuariomanejo.applicacion.servicios.dto.query.ConseguirUsuarioPorIdQuery;
import com.rabb.usuariomanejo.dominio.enums.UsuarioEstatus;
import com.rabb.usuariomanejo.dominio.enums.UsuarioRol;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import com.rabb.usuariomanejo.dominio.ov.UsuarioContraseña;
import com.rabb.usuariomanejo.dominio.ov.UsuarioCorreo;
import com.rabb.usuariomanejo.dominio.ov.UsuarioId;
import com.rabb.usuariomanejo.dominio.ov.UsuarioNombre;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsuarioAplicacionMappeador {

    public UsuarioModelo fromCreateCommandToModel(final CrearUsuarioComando comando) {
        return UsuarioModelo.create(
                new UsuarioId(comando.id()),
                new UsuarioNombre(comando.nombre()),
                new UsuarioCorreo(comando.correo()),
                UsuarioContraseña.fromPlainText(comando.password()),
                UsuarioRol.fromString(comando.role()));
    }

    public UsuarioModelo fromUpdateCommandToModel(
            final ActualizarUsuarioComando comando, final UsuarioContraseña currentContraseña) {

        final UsuarioContraseña passwordToUse = resolveContraseña(comando.password(), currentContraseña);

        return new UsuarioModelo(
                new UsuarioId(comando.id()),
                new UsuarioNombre(comando.nombre()),
                new UsuarioCorreo(comando.email()),
                passwordToUse,
                UsuarioRol.fromString(comando.role()),
                UsuarioEstatus.fromString(comando.status()));
    }

    public UsuarioId fromConseguirUsuarioPorIdQueryToUsuarioId(final ConseguirUsuarioPorIdQuery query) {
        return new UsuarioId(query.id());
    }

    public UsuarioId fromDeleteCommandToUsuarioId(final EliminarUsuarioComando comando) {
        return new UsuarioId(comando.id());
    }

    private UsuarioContraseña resolveContraseña(
            final String newPlainContraseña, final UsuarioContraseña currentContraseña) {
        if (Objects.isNull(newPlainContraseña) || newPlainContraseña.isBlank()) {
            return currentContraseña;
        }
        return UsuarioContraseña.fromPlainText(newPlainContraseña);
    }
}