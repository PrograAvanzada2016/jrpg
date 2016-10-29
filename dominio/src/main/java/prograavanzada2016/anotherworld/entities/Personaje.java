package prograavanzada2016.anotherworld.entities;

import prograavanzada2016.anotherworld.castas.Casta;
import prograavanzada2016.anotherworld.combates.Alianza;
import prograavanzada2016.anotherworld.combates.Loot;
import prograavanzada2016.anotherworld.habilidades.HabilidadPersonaje;
import prograavanzada2016.anotherworld.objetos.*;
import prograavanzada2016.anotherworld.razas.Raza;

public class Personaje extends Ente{
	
	protected Casta casta;
	protected Raza raza;
	
	protected ObjetoArma objetoArma;
	protected ObjetoArmadura objetoArmadura;
	
	//experiencia que tiene el jugador en el momento
	protected int experienciaActual;
	//experiencia que necesita el jugador para subir de nivel
	protected int experienciaLimite;
	
	
	protected Alianza alianza = null;
	
	public Personaje(String nombre, Raza raza, Casta casta) throws Exception {
		super(nombre);
		this.raza=raza;
		this.casta=casta;
		this.casta.calcularStats(this);
		this.casta.guardarHabilidades();
		this.puntosAdicionalesALosStats = 0;
		//cuando se crea un personaje se le debe modificar sus stats segun la raza
		//this.casta.calcularStats(this, raza);
	}

	@Override
	public void serAtacado(int da�o) {
		int da�oNeto = da�o-this.defensa;
		if(da�oNeto>0){
			this.restarSaludEnUso(da�oNeto);
		}
	}
	
	@Override
	public void serAtacadoConMagia(int da�o) {
		int da�oNeto = da�o;
		if(da�oNeto>0){
			this.restarSaludEnUso(da�oNeto);
		}
	}

	@Override
	public int calcularPuntosDeAtaque() {
		return this.ataque;
	}

	@Override
	public void despuesDeAtacar(Ente ente) {
		if(!ente.estaVivo)
		{
			this.sumarExperiencia(5);;
		}
	}

	@Override
	public void serHechizado(HabilidadPersonaje habilidad) {
		// TODO Auto-generated method stub
		
	}
	
	public int getExperienciaActual() {
		return experienciaActual;
	}
	
	public void subirNivel() {
		this.nivel = this.nivel+1;
		this.puntosAdicionalesALosStats += 5;
	}

	public void sumarExperiencia(int experiencia) {
		this.experienciaActual += experiencia;
		if(this.experienciaActual>=this.experienciaLimite){
			this.subirNivel();
			this.experienciaActual=0;
		}
	}
	
	public void equiparArma(ObjetoArma objetoArma){
		if(objetoArma.getCasta().equals(this.getCasta()) && objetoArma.getNivelMinimo()<=this.getNivel()){
			this.objetoArma=objetoArma;
			this.setAtaque(objetoArma.getBonusAtaque()+this.getAtaque());
			this.setPuntosDeEnergiaPorAtaque(objetoArma.getPuntosDeEnergiaPorAtaque());
		}else{
			System.out.println("No se puede equipar el arma, reload reload");
		}
	}
	
	public void equiparArmadura(ObjetoArmadura objetoArmadura){
		if(objetoArmadura.getCasta().equals(this.getCasta()) && objetoArmadura.getNivelMinimo()<=this.getNivel()){
			this.objetoArmadura=objetoArmadura;
			this.setDefensa(objetoArmadura.getBonusDefensa()+this.getDefensa());
		}else{
			System.out.println("Una fuerza extra�a me impide usar esta armadura");
		}
	}
	
	public void lanzarHabilidad(int id,Ente enteObjetivo){
		HabilidadPersonaje habilidad = this.getCasta().getHabilidad(id);
		if(habilidad.getNivelRequerido()<=this.getNivel()){
			habilidad.LanzarHechizo(this, enteObjetivo);
		}else{
			System.out.println("Siento que aun me falta energias para esta tecnica.");
		}
	}
	
	public int getExperienciaLimite() {
		return experienciaLimite;
	}

	public void setExperienciaLimite(int experienciaLimite) {
		this.experienciaLimite = experienciaLimite;
	}

	public Casta getCasta() {
		return casta;
	}

	public void setCasta(Casta casta) {
		this.casta = casta;
	}

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}
	
	
	
	public void mostrarStats(){
		System.out.println("Nombre: "+super.getNombre());
		System.out.println("Nivel: "+super.getNivel());
		System.out.println("Fuerza: "+this.getFuerza());
		System.out.println("Destreza: "+this.getDestreza());
		System.out.println("Inteligecia: "+this.getInteligencia());
		System.out.println("Salud: "+this.getSalud());
		System.out.println("Salud en uso: "+this.getSaludEnUso());
		System.out.println("Mana: "+this.getMana());
		System.out.println("Mana en uso: "+this.getManaEnUso());
		System.out.println("Energia: "+this.getEnergia());
		System.out.println("Energia en uso:"+this.energiaEnUso);
	}

	@Override
	public void dropearObjetos(Loot loot)
	{
		// Al morir, �dropea el mejor item?
	}
	
	public void crearAlianza(String nombreAlianza)
	{
		abandonarAlianzaActual();
		this.alianza = new Alianza(nombreAlianza, this);
	}
	
	public void a�adirPersonajeAlianza(Personaje p)
	{
		// Checkear que el personaje a a�adir no est� en la alianza.
		this.alianza.agregarMiembro(p);
		p.serA�adidoAlianza(this.alianza);
	}
	
	public void serA�adidoAlianza(Alianza a)
	{
		// Consultar si quiere aceptar.
		this.abandonarAlianzaActual();
		this.alianza = a;
	}
	
	public void abandonarAlianzaActual()
	{
		if (this.alianza != null)
		{
			this.alianza.quitarMiembro(this);
			this.alianza = null;
		}
	}
	
	public String getNombreAlianza()
	{
		return (this.alianza != null) ? this.alianza.getNombre() : "";
	}
	
	public int getCantMienbrosAlianza()
	{
		return (this.alianza != null) ? this.alianza.getCantMiembros() : 0;
	}
	
	public float getBonusExp()
	{
		return (this.alianza != null) ? this.alianza.getBonusExp() : 1;
	}

	@Override
	public void despuesDeMorir() {
		// TODO Auto-generated method stub
		
	}
}
