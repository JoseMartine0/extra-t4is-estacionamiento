package mx.uv.estacionamiento;

import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class EndPoint{

    private int LUGARESMAXIMOS = 80;
    private int lugaresVacios = 0;

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "BuscarPorIdRequest")
    @ResponsePayload


    public SaludarResponse ConsultarLugaresVacios(){
        SaludarResponse respuesta = new SaludarResponse();
        int lugaresVacios = LUGARESMAXIMOS - lugaresOcupados;
        
        respuesta.setRespuesta(integer.toString(lugaresVacios));

        return respuesta;
    }

    //agregar
    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "AgregarReq")
    @ResponsePayload

    public AgregarRes Buscar(@RequestPayLoad BuscarPorIdRequest peticion){
        AgregarRes respuesta = new AgregarRes();

        int lugaresAAgregar = peticion.getLugares();

        if (lugaresAAgregar < 0){
            respuesta.setData("lugares no pueden ser negativos ");
            return respuesta;
        }

        if (lugaresVacios + lugaresAAgregar > lugaresVacios){
            respuesta.setData("no se pueden agregar" + lugaresAAgregar + "lugares. Excede el limite");
            return respuesta;

        }

        lugaresVacios += lugaresAAgregar;

        respuesta.setData("operacion realizada con exito. Lugares vacios actuales: "+lugaresVacios);
        return respuesta;
    }


    //Eliminar

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "EliminarReq")
    @ResponsePayload

    public EliminarRes Buscar(@RequestPayLoad BuscarPorIdRequest peticion){
        EliminarRes respuesta = new EliminarRes();

        int lugaresAEliminar = peticion.getLugares();

        if (lugaresAEliminar < 0){
            respuesta.setData("no pueden ser menos ");
            return respuesta;
        }

        if (lugaresAEliminar > lugaresVacios){
            respuesta.setData("no hay lugares vacios para eliminar");
            return respuesta;

        }

        lugaresVacios += lugaresAEliminar;

        respuesta.setData("operacion realizada con exito. Lugares vacios actuales: "+lugaresVacios);
        return respuesta;
    }


}
