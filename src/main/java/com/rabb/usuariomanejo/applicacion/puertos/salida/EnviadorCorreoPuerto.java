package com.rabb.usuariomanejo.applicacion.puertos.salida;
import com.rabb.usuariomanejo.dominio.modelo.CorreoModeloEnvio;
public interface EnviadorCorreoPuerto {
    void enviar(CorreoModeloEnvio destinocorreo);
}