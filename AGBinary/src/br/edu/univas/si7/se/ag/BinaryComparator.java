package br.edu.univas.si7.se.ag;

import java.util.Comparator;

public class BinaryComparator implements Comparator<Individual> {

	@Override
	public int compare(Individual o1, Individual o2) {
		return (int) (o1.getFitness() - o2.getFitness());
	}
}
