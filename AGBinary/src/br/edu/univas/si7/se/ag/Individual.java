package br.edu.univas.si7.se.ag;

public class Individual {

	private String info;
	private double fitness;
	public static int BIT_SIZE = 127;
	
	public Individual(String info) {
		this.info = info;
	}
	
	public void calculateFitness() {
		int x = Integer.parseInt(info, 2);
		//TODO: melhorar este cálculo
		fitness = Math.pow(x, 2) * 2 - 3 * x + 4;
//		fitness = Math.pow(x, 3) * 5 - Math.pow(x, 2) * 2 - 3 * x + 4;
		fitness = 1000/fitness;//calcula o inverso, pois nós vamos buscar o menor
		//usa mil pois os resultados estão muito pequenos
		
		//testar com http://www.calculadoraonline.com.br/grafica
		
		//sen(x+cos(X^2)^2-2)
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public double getFitness() {
		return fitness;
	}

	@Override
	public String toString() {
		return "Individual [info=" + info
				+ ", value=" + Integer.parseInt(info, 2)
				+ ", fitness=" + fitness + "]";
	}
	
	
}
