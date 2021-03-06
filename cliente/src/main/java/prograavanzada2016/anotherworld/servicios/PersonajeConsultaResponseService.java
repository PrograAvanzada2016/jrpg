package prograavanzada2016.anotherworld.servicios;

import java.io.PrintWriter;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import prograavanzada2016.anotherworld.cliente.ClienteJugable;
import prograavanzada2016.anotherworld.interfaces.VentanaCrearPersonaje;
import prograavanzada2016.anotherworld.interfaces.VentanaDeBienvenida;
import prograavanzada2016.anotherworld.juego.Game;
import prograavanzada2016.anotherworld.mensajes.MessageBase;
import prograavanzada2016.anotherworld.mensajes.RawMessage;
import prograavanzada2016.anotherworld.mensajes.request.PersonajeNuevoConectadoMessage;
import prograavanzada2016.anotherworld.mensajes.response.PersonajeConsultaResponseMessage;
import prograavanzada2016.anotherworld.modelos.Usuario;

public class PersonajeConsultaResponseService implements ServicioServer{
	
	

	private ClienteJugable clienteJugable;
	
	public PersonajeConsultaResponseService(ClienteJugable clienteJugable) {
		this.clienteJugable=clienteJugable;
	}
	
	@Override
	public void ejecutar(MessageBase message) throws Exception {
		PersonajeConsultaResponseMessage pcm = (PersonajeConsultaResponseMessage) message;
		
		Usuario usuario = new Gson().fromJson(pcm.Payload, Usuario.class);
		//pusimo esto asi por el momento para saber si hay o no hay usuario
		this.clienteJugable.setUsuario(usuario);
		if(usuario.getPersonaje()==null){			
			new VentanaCrearPersonaje(this.clienteJugable);
		}
		else{
			
			Game game = new Game("Another World", 800, 600, clienteJugable);
			
			clienteJugable.setJuego(game);
			
			game.start();
			
			PrintWriter salida = new PrintWriter(this.clienteJugable.getSocket().getOutputStream());
			
			RawMessage rawMessageLogin = new RawMessage();
	    	rawMessageLogin.type = "buscarPersonajes";
	    	rawMessageLogin.message = new PersonajeConsultaResponseMessage(new Gson().toJson(clienteJugable.getUsuario()));
			
			salida.println(new Gson().toJson(rawMessageLogin));
			salida.flush();
		}
		
	}


}
