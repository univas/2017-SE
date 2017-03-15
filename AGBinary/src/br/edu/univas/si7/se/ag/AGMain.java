package br.edu.univas.si7.se.ag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AGMain {

	private List<Individual> currentGeneration;
	private List<Individual> newGeneration;
	private int epoch;
	
	public void execute() {
		createInitialPopulation();
		evaluate();
		printCurrentGeneration();
		epoch = 1;
		logEpoch();
		while(stopCriteria()) {
			executeCrossing();
			currentGeneration = newGeneration;
			executeMutation();
			evaluate();
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
	}

	private void executeCrossing() {
		newGeneration = new ArrayList<Individual>();
		
		int pos = 0;
		
		while(currentGeneration.size() != newGeneration.size()) {
		
			//choose the parents
			//TODO: melhorar
			Individual individualOne = currentGeneration.get(pos++);
			Individual individualTwo = currentGeneration.get(pos++);
			
			//cross
			int crossPoint = new Random().nextInt(6) + 1;
			
			String info1 = individualOne.getInfo();
			String info2 = individualTwo.getInfo();
			
			String part11 = info1.substring(0, crossPoint); //parte 1 do ind 1
			String part12 = info2.substring(crossPoint, 7); //parte 2 do ind 2
			
			Individual childOne = new Individual(part11 + part12);
			
			String part21 = info2.substring(0, crossPoint); //part 1 do ind 2
			
			try{
				String part22 = info1.substring(crossPoint, 7); //part 2 do ind 1
				Individual childTwo = new Individual(part21 + part22);
				
				newGeneration.add(childOne);
				newGeneration.add(childTwo);
			}catch(Exception e) {
				System.out.println(Integer.parseInt(individualOne.getInfo(), 2)  + " - " + info1 + " cp: " + crossPoint);
				System.out.println(Integer.parseInt(individualTwo.getInfo(), 2)  + " - " + info2 + " cp: " + crossPoint);
			}
		}
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
		Random rand = new Random(83);
		currentGeneration = new ArrayList<Individual>();
		for (int i = 0; i < AGParameters.POPULATION_SIZE; i++) {
			int x = rand.nextInt(127);// max of 7 bits
			
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
