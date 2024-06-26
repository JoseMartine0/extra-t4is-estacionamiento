package mx.uv.estacionamiento;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.AgregarRequest;
import https.t4is_uv_mx.saludos.AgregarResponse;
import https.t4is_uv_mx.saludos.EliminarRequest;
import https.t4is_uv_mx.saludos.EliminarResponse;
import https.t4is_uv_mx.saludos.SaludarResponse;

@Endpoint
public class EndPoint {
    private int LUGARESMAXIMOS = 80;
    private int lugaresVacios = 0;

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "SaludarRequest")
    @ResponsePayload
    public SaludarResponse ConsultarLugaresVacios() {
        SaludarResponse respuesta = new SaludarResponse();

        respuesta.setRespuesta(Integer.toString(lugaresVacios));

        return respuesta;
    }

    // agregar
    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "AgregarRequest")
    @ResponsePayload
    public AgregarResponse AgregarLugaresVacios(@RequestPayload AgregarRequest peticion) {
        AgregarResponse respuesta = new AgregarResponse();

        int lugaresAAgregar = peticion.getLugares();

        if (lugaresAAgregar < 0) {
            respuesta.setRespuesta("lugares no pueden ser negativos ");
            return respuesta;
        }

        if (lugaresVacios + lugaresAAgregar > LUGARESMAXIMOS) {
            respuesta.setRespuesta("no se pueden agregar" + lugaresAAgregar + "lugares. Excede el limite");
            return respuesta;

        }

        lugaresVacios += lugaresAAgregar;

        respuesta.setRespuesta("operacion realizada con exito. Lugares vacios actuales: " + lugaresVacios);
        return respuesta;
    }

    // Eliminar
    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "EliminarRequest")
    @ResponsePayload
    public EliminarResponse EliminarLugaresVacios(@RequestPayload EliminarRequest peticion) {
        EliminarResponse respuesta = new EliminarResponse();

        int lugaresAEliminar = peticion.getLugares();

        if (lugaresAEliminar < 0) {
            respuesta.setRespuesta("no pueden ser menos ");
            return respuesta;
        }

        if (lugaresAEliminar > lugaresVacios) {
            respuesta.setRespuesta("no hay lugares vacios para eliminar");
            return respuesta;
        }

        lugaresVacios += lugaresAEliminar;

        respuesta.setRespuesta("operacion realizada con exito. Lugares vacios actuales: " + lugaresVacios);
        return respuesta;
    }

}
