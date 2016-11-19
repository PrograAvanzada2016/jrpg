package prograavanzada2016.anotherworld.servicios;

import prograavanzada2016.anotherworld.cliente.ClienteJugable;
import prograavanzada2016.anotherworld.servicios.ServicioServer;

public class ServiceLocator {
	
	public static ServicioServer localizar(String tipo,ClienteJugable clienteJugable) {
		if(tipo.equals("loginResponse")){
			return new LoginResponseService(clienteJugable);
		}else if(tipo.equals("personajeConsultaResponse")){
			return new PersonajeConsultaResponseService(clienteJugable);
		}else if(tipo.equals("personajeNuevoConectado")){
			
		}else if(tipo.equals("respuestaGenericaNuevoUsuario")){
			return new RespuestaGenericaNuevoUsuarioService(clienteJugable);
		}else if(tipo.equals("crearPersonajeResponse")){
			return new CrearPersonajeNuevoResponseService(clienteJugable);
		}
		return null;
	}

}