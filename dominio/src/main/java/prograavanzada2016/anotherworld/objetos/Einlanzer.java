package prograavanzada2016.anotherworld.objetos;

import prograavanzada2016.anotherworld.castas.Guerrero;

public class Einlanzer extends ObjetoArma{
	public Einlanzer() throws Exception{
		this.setBonusAtaque(300);
		this.setPuntosDeEnergiaPorAtaque(10);
		this.setCasta(new Guerrero());
		this.setNivelMinimo(50);
	}
}
