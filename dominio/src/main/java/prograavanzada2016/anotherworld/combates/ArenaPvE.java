package prograavanzada2016.anotherworld.combates;

import java.util.ArrayList;

import prograavanzada2016.anotherworld.entities.Enemigo;
import prograavanzada2016.anotherworld.entities.Ente;
import prograavanzada2016.anotherworld.entities.Personaje;
import prograavanzada2016.anotherworld.resources.GeneradorMagico;

public class ArenaPvE {
	private GrupoEnemigos grupoEnemigos;
	private GrupoPersonajes grupoPersonajes;
	private int cantidadDeExperiencia;
	private ArrayList<Float> listoPersonajes;
	private ArrayList<Float> listoEnemigos;
	private Loot loot;
	private boolean gananLosPersonajes;
	
	public ArenaPvE(GrupoEnemigos grupoEnemigos, GrupoPersonajes grupoPersonajes)throws Exception {
		this.grupoEnemigos = grupoEnemigos;
		this.grupoPersonajes = grupoPersonajes;
		listoPersonajes=new ArrayList<>();
		listoEnemigos=new ArrayList<>();
		this.loot = new Loot();
		
		for(Enemigo enemigo : grupoEnemigos.getGrupo()){
			enemigo.setArena(this);
		}
	}
	
	public ArenaPvE(GrupoEnemigos grupoEnemigos, Alianza alianza) throws Exception {
		this.grupoEnemigos = grupoEnemigos;
		this.grupoPersonajes.setGrupo(alianza.getMiembros()); 
		listoPersonajes=new ArrayList<>();
		listoEnemigos=new ArrayList<>();
		this.loot = new Loot();
		
		for(Enemigo enemigo : grupoEnemigos.getGrupo()){
			enemigo.setArena(this);
		}
	}	

	public GrupoEnemigos getGrupoEnemigos() {
		return grupoEnemigos;
	}


	public void setGrupoEnemigos(GrupoEnemigos grupoEnemigos) {
		this.grupoEnemigos = grupoEnemigos;
	}


	public GrupoPersonajes getGrupoPersonajes() {
		return grupoPersonajes;
	}


	public void setGrupoPersonajes(GrupoPersonajes grupoPersonajes) {
		this.grupoPersonajes = grupoPersonajes;
	}


	public int getCantidadDeExperiencia() {
		return cantidadDeExperiencia;
	}


	public void setCantidadDeExperiencia(int cantidadDeExperiencia) {
		this.cantidadDeExperiencia += cantidadDeExperiencia;
	}
	
	
	public ArrayList<Float> getListoPersonajes() {
		return listoPersonajes;
	}


	public void setListoPersonajes(ArrayList<Float> listoPersonajes) {
		this.listoPersonajes = listoPersonajes;
	}


	public ArrayList<Float> getListoEnemigos() {
		return listoEnemigos;
	}


	public void setListoEnemigos(ArrayList<Float> listoEnemigos) {
		this.listoEnemigos = listoEnemigos;
	}
	
	public boolean isGananLosPersonajes() {
		return gananLosPersonajes;
	}


	public void setGananLosPersonajes(boolean gananLosPersonajes) {
		this.gananLosPersonajes = gananLosPersonajes;
	}

	public void armarTurnos() throws Exception{
		int contadorDeAtaquesPersonajes=0;
		int contadorDeAtaquesEnemigos=0;
		for(int x=0; x<100000 && this.grupoEnemigos.viven() && this.grupoPersonajes.viven();x++){
			//System.out.println("**************************");
			if(x%2==0){
				//turno aliados
				boolean flag=true;
				Ente enteConTurno = null;
				while(flag){
					Ente enteElegido = this.getGrupoPersonajes().seleccionarUnoAlAzar();
					flag=this.yaAtacoPersnaje(enteElegido);
					enteConTurno=enteElegido;
				}
				//Aca iria el turno del personaje por ahora es todo automatico
				enteConTurno.atacar(this.getGrupoEnemigos().seleccionarUnoAlAzar());
				contadorDeAtaquesPersonajes+=1;
				if(contadorDeAtaquesPersonajes==this.getGrupoPersonajes().getGrupo().size()){
					this.resetearTurnosPersonajes();
				}
			}else{
				//turno enemigos
				boolean flag=true;
				Ente enteConTurno = null;
				while(flag){
					Ente enteElegido = this.getGrupoPersonajes().seleccionarUnoAlAzar();
					flag=this.yaAtacoPersnaje(enteElegido);
					enteConTurno=enteElegido;
				}
				//Aca iria el turno del personaje por ahora es todo automatico
				enteConTurno.atacar(this.getGrupoPersonajes().seleccionarUnoAlAzar());
				contadorDeAtaquesEnemigos+=1;
				if(contadorDeAtaquesEnemigos==this.getGrupoEnemigos().getGrupo().size()){
					this.resetearTurnosEnemigos();
				}
			}
			
			if(!this.grupoEnemigos.viven()){
				//System.out.println("Murieron todos los Enemigos");
				this.otorgarExperiencia();
			}
			if(!this.grupoPersonajes.viven()) {
				//System.out.println("Murieron todos los Personajes");
			}
		}
	}


	private boolean yaAtacoPersnaje(Ente ente) throws Exception{
		boolean flag=false;
		for(Float id: this.getListoPersonajes()){
			if(id.equals(ente.getIdEnte())){
				flag=true;
			}
		}
		if(!flag){
			this.getListoPersonajes().add(ente.getIdEnte());
		}
		return flag;
	}
	
	private boolean yaAtacoEnemigo(Ente ente) throws Exception{
		boolean flag=false;
		for(Float id: this.getListoEnemigos()){
			if(id.equals(ente.getIdEnte())){
				flag=true;
			}
		}
		if(!flag){
			this.getListoEnemigos().add(ente.getIdEnte());
		}
		return flag;
	}
	
	private void resetearTurnosEnemigos() throws Exception{
		this.setListoEnemigos(new ArrayList<>());
	}
	
	private void resetearTurnosPersonajes() throws Exception{
		this.setListoPersonajes(new ArrayList<>());
	}
	
	private void otorgarExperiencia(){
		for(Personaje personaje : this.getGrupoPersonajes().getGrupo()){
			personaje.sumarExperiencia(this.getCantidadDeExperiencia());
		}
	}

	public int[] armarTurnosAuto() {
		int turnos[] = new int[10];
		for(int x=0; x<turnos.length;x++){
			if(x%2==0){
				turnos[x]=0;
			}else{
				turnos[x]=1;
			}
		}
		return turnos;
	}
}
