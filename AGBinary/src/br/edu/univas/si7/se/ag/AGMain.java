package br.edu.univas.si7.se.ag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AGMain {

	private List<Individual> currentGeneration;
	private List<Individual> newGeneration;
	private int epoch;
	private Random rand = new Random(AGParameters.SEED);
	
	public void execute() {
		createInitialPopulation();
		evaluate();
		Collections.sort(currentGeneration, new BinaryComparator());
		printCurrentGeneration();
		epoch = 1;
		logEpoch();
		while(stopCriteria()) {
			executeCrossing();
			currentGeneration = newGeneration;
			executeMutation();
			evaluate();
			Collections.sort(currentGeneration, new BinaryComparator());
			printCurrentGeneration();
			epoch++;
			logEpoch();
		}
	}

	private void logEpoch() {
		//log da época
		System.out.println("Epoch: " + epoch);
		Individual best = getTheBestIndividual();
		System.out.println(best.getInfo() 
				+ " = " + Integer.parseInt(best.getInfo(), 2));
	}

	private Individual getTheBestIndividual() {
		Individual best = currentGeneration.get(0);
		for (Individual individual : currentGeneration) {
			if(individual.getFitness() < best.getFitness()) {
				best = individual;
			}
		}
		return best;
	}

	private void executeMutation() {
		//TODO: tarefa para casa
		//DICA: criar uma constante com o percentual (de 1% a 3%) de mutação
		//ou seja, percentual de indivíduos que serão mutados naquela época
		
		int amount = rand.nextInt(100);
		if(amount <= AGParameters.MUTATION_RATE) {
			amount++;
			
			for (int i = 0; i < amount; i++) {
				int elementChoosen = rand.nextInt(currentGeneration.size() - 1);
				Individual individual = currentGeneration.get(elementChoosen);
				System.out.println("Mutating: " + individual);
				
				int position = rand.nextInt(6);//posição que será alterada
				char bit = individual.getInfo().charAt(position);
				bit = bit == '0' ? '1' : '0';//inverte o bit

				StringBuilder builder = new StringBuilder(individual.getInfo());
				builder.setCharAt(position, bit);//seta o novo caractere
				
				individual.setInfo(builder.toString());
				System.out.println("Mutated: " + individual);
			}
			
		}
	}

	private void executeCrossing() {
		newGeneration = new ArrayList<Individual>();
		
		int pos = 0;
		
//		currentGeneration.sort(new BinaryComparator());//versão mais nova do java
		
		while(currentGeneration.size() != newGeneration.size()) {
		
			//choose the parents
			//TODO: melhorar
//			Individual individualOne = currentGeneration.get(pos++);
//			Individual individualTwo = currentGeneration.get(pos++);
			
			Individual individualOne = chooseParent();
			Individual individualTwo = chooseParent();
			
			//cross
			int crossPoint = rand.nextInt(6) + 1;
			
			String info1 = individualOne.getInfo();
			String info2 = individualTwo.getInfo();
			
			String part11 = info1.substring(0, crossPoint); //parte 1 do ind 1
			String part12 = info2.substring(crossPoint, 7); //parte 2 do ind 2
			
			Individual childOne = new Individual(part11 + part12);
			
			String part21 = info2.substring(0, crossPoint); //part 1 do ind 2
			
			String part22 = info1.substring(crossPoint, 7); //part 2 do ind 1
			Individual childTwo = new Individual(part21 + part22);
			
			newGeneration.add(childOne);
			newGeneration.add(childTwo);
		}
	}

	//TODO: exercício para casa: pensar e implementar a roleta invertida
	private Individual chooseParent() {
		double max = 0; //soma de todos os fitness
		for (Individual individual : currentGeneration) {
			max += individual.getFitness();
		}
//		System.out.println("Choose parent: max: " + max);
		
		double point = rand.nextInt((int)max);//ponto sorteado
//		System.out.println("point: " + point);
		
		double sum = 0; //soma dos fitness
		
		for (Individual individual : currentGeneration) {
			sum += individual.getFitness();
//			System.out.println("sum: " + sum);
			if(sum > point) {
				System.out.println("Parent: " + individual);
				return individual;
			}
		}
		//se não achou nenhum, então retorna o último
		Individual individual = currentGeneration.get(currentGeneration.size() - 1);
//		System.out.println("Parent last: " + individual);
		return individual;
	}

	private boolean stopCriteria() {
		return epoch < AGParameters.MAX_ITERATIONS;
	}

	private void evaluate() {
		for (Individual individual : currentGeneration) {
			individual.calculateFitness();
		}		
	}

	private void createInitialPopulation() {
		currentGeneration = new ArrayList<Individual>();
		for (int i = 0; i < AGParameters.POPULATION_SIZE; i++) {
			int x = rand.nextInt(Individual.BIT_SIZE);// max of 7 bits
			
			String rawInfo = Integer.toBinaryString(x);
			String info = String.format("%7s", rawInfo).replace(' ', '0');
			
			currentGeneration.add(new Individual(info));
		}
	}
	
	private void printCurrentGeneration() {
		for (Individual individual : currentGeneration) {
			System.out.println(individual);
		}
	}
}
