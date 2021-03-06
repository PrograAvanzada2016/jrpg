package prograavanzada2016.anotherworld.mapas;

import java.awt.image.BufferedImage;

public class Animacion {
	
	private int velocidad;
	private int indice;
	private long ultimoTiempo, timer;
	private BufferedImage[] frames;
	
	public Animacion(int velocidad, BufferedImage[] frames) throws Exception{
		this.velocidad = velocidad;
		this.frames = frames;
		indice = 0;
		timer = 0;
		ultimoTiempo = System.currentTimeMillis();
	}
	
	public void actualizar() {
		timer += System.currentTimeMillis() - ultimoTiempo;
		ultimoTiempo = System.currentTimeMillis();
		
		if(timer > velocidad) {
			indice++;
			timer = 0;
			if(indice >= frames.length) {
				indice = 0;
			}
		}
	}
	
	public BufferedImage getFrameActual() {
		return frames[indice];
	}

}
