package prograavanzada2016.anotherworld.modelos;

import com.google.gson.annotations.Expose;

import prograavanzada2016.anotherworld.entities.Personaje;

public class Usuario {
	
	private int clienteId;
	private long id;
	private String nombre;
	private String apellido;
	private String nombreUsuario;
	private String password;
	private PersonajeModel personaje;
	//private Personaje personajeJugador;
	
	public Usuario(long id, String nombre, String apellido, String nombreUsuario, String password, PersonajeModel personaje) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.personaje = personaje;
			
	}
	

	public Usuario(){
		id=0;
		nombre="";
		apellido="";
		nombreUsuario="";
		password="";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public PersonajeModel getPersonaje() {
		return personaje;
	}
	public void setPersonaje(PersonajeModel personaje) {
		this.personaje = personaje;
	}


	public int getClienteId() {
		return clienteId;
	}


	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	
}
