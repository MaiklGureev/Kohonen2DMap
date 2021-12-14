package com.company.nn;

import com.company.Config;

public class Neuron {

    int i, j;
    double error = 0;
    double[] arrayOfWeight;

    double p = Config.pDefault;

    int countClassA = 0;
    int countClassB = 0;
    int countClassC = 0;

    public Neuron(int i, int j, int countAttrs) {
        this.i = i;
        this.j = j;
        arrayOfWeight = new double[countAttrs];
        for (int a = 0; a < countAttrs; a++) {
            arrayOfWeight[a] = Config.startWeight * Config.random.nextDouble();
        }
    }

    // расчёт значения соседства гауссовского типа для текущего значения расстояния между вектором и нейроном 5.18
    public double calcNeighborhoodFunction(double[] vector, double lambda) {
        double currentDistanceToNeuron = calcDistanceBetweenNeuronAndInputVector(vector);
        return Math.exp(-Math.pow(currentDistanceToNeuron, 2) / (2 * Math.pow(lambda, 2)));
    }

    // эвклидова мера 5.4
    public double calcDistanceBetweenNeuronAndInputVector(double[] inputVector) {
        double currentDistanceToNeuron = 0;
        for (int i = 0; i < arrayOfWeight.length; i++) {
            currentDistanceToNeuron += Math.pow(inputVector[i] - arrayOfWeight[i], 2);
        }
        currentDistanceToNeuron = Math.sqrt(currentDistanceToNeuron);
        return currentDistanceToNeuron;
    }

    public void correctPotential(boolean isWinnerNeuron) {
        if (isWinnerNeuron) {
            p = p - Config.pMin;
        } else {
            p = p + 1.0 / Config.countNeurons;
        }
    }

    void clearClassCounts() {
        countClassA = 0;
        countClassB = 0;
        countClassC = 0;
    }

}
