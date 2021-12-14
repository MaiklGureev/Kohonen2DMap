package com.company.nn;

import com.company.Config;
import com.company.Dataset;

public class NeuralNetwork {

    Neuron[][] neurons;

    double[][] train = Dataset.train;
    double[][] test = Dataset.test;

    public NeuralNetwork() {
        neurons = new Neuron[Config.sizeOfMap][Config.sizeOfMap];
        for (int i = 0; i < Config.sizeOfMap; i++) {
            for (int j = 0; j < Config.sizeOfMap; j++) {
                neurons[i][j] = new Neuron(i, j, Config.countAttrsInVector);
            }
        }

        if (Config.isEnableNormalization) {
            train = normalizationSet(Dataset.train);
            test = normalizationSet(Dataset.test);
        }
    }

    public void train() {
        if (Config.algorithmDefaultType == AlgorithmType.WTM) {
            trainWTM();
        }
        if (Config.algorithmDefaultType == AlgorithmType.WTA) {
            trainWTA();
        }
    }

    // обучение по алгоритму победитель получает больше
    public void trainWTM() {
        double lambda;
        double n = Config.nDefault;

        for (int t = 0; t < Config.countTrainIterations; t++) {
            //расчёт уровня соседства 5.22
            lambda = Config.lambdaMax * Math.pow(Config.lambdaMin / Config.lambdaMax, 1.0 * t / Config.countTrainIterations);
            // расчёт коэффицента обучения 5.23
            n = n * Math.pow(Config.nMin / n, 1.0 * t / Config.countTrainIterations);
            for (int d = 0; d < train.length; d++) {
                Neuron neuronWinner = findNeuronWinnerWTM(train[d]);
                trainNeuronWTM(neuronWinner, train[d], lambda, n);
                trainNeighborhoodsNeuronsWTM(neuronWinner, Config.radius, lambda, n);
            }
            if (t % Config.printErrorEvery == 0) {
                System.out.println();
                System.out.println(String.format("Train iteration: %d/%d", t, Config.countTrainIterations));
                printErrorKohonen2dMapOnTrainDataset();
            }
        }
    }

    // обучение по алгоритму победитель получает всё
    public void trainWTA() {
        double n = Config.nDefault;

        for (int t = 0; t < Config.countTrainIterations; t++) {
            for (int d = 0; d < train.length; d++) {
                Neuron neuronWinner = findNeuronWinnerWTA(train[d]);
                neuronWinner.correctPotential(true);
                trainNeuronWTA(neuronWinner, train[d], n);
                trainNeighborhoodsNeuronsWTA(neuronWinner, Config.radius);
            }
            if (t % Config.printErrorEvery == 0) {
                System.out.println();
                System.out.println(String.format("Train iteration: %d/%d", t, Config.countTrainIterations));
                printErrorKohonen2dMapOnTrainDataset();
            }
        }
    }


    // обучение одного нейрона формула 5.18, 5.19
    public void trainNeuronWTM(Neuron neuronWinner, double[] inputVector, double lambda, double n) {
        double s = neuronWinner.calcNeighborhoodFunction(inputVector, lambda);
        for (int w = 0; w < neuronWinner.arrayOfWeight.length; w++) {
            neuronWinner.arrayOfWeight[w] = neuronWinner.arrayOfWeight[w] + n * s * (inputVector[w] - neuronWinner.arrayOfWeight[w]);
        }
    }

    // обучение одного нейрона формула 5.15, 5.16
    public void trainNeuronWTA(Neuron neuronWinner, double[] inputVector, double n) {
        for (int w = 0; w < neuronWinner.arrayOfWeight.length; w++) {
            neuronWinner.arrayOfWeight[w] = neuronWinner.arrayOfWeight[w] + n * 1 * (inputVector[w] - neuronWinner.arrayOfWeight[w]);
        }
    }

    // обучение нейронов соседей в заданном радиусе, радиус задаётся в количестве нейронов
    public void trainNeighborhoodsNeuronsWTM(Neuron neuronWinner, int radius, double lambda, double n) {
        int iMin = neuronWinner.i - radius;
        int iMax = neuronWinner.i + radius;
        int jMin = neuronWinner.j - radius;
        int jMax = neuronWinner.j + radius;

        for (int i = 0; i < Config.sizeOfMap; i++) {
            for (int j = 0; j < Config.sizeOfMap; j++) {
                if (iMin <= i && i <= iMax && jMin <= j && j <= jMax && i != neuronWinner.i && j != neuronWinner.j) {
                    trainNeuronWTM(neurons[i][j], neuronWinner.arrayOfWeight, lambda, n);
                }
            }
        }
    }

    // обучение нейронов соседей в заданном радиусе, радиус задаётся в количестве нейронов, корретикруются потенциалы
    public void trainNeighborhoodsNeuronsWTA(Neuron neuronWinner, int radius) {
        int iMin = neuronWinner.i - radius;
        int iMax = neuronWinner.i + radius;
        int jMin = neuronWinner.j - radius;
        int jMax = neuronWinner.j + radius;

        for (int i = 0; i < Config.sizeOfMap; i++) {
            for (int j = 0; j < Config.sizeOfMap; j++) {
                if (iMin <= i && i <= iMax && jMin <= j && j <= jMax && i != neuronWinner.i && j != neuronWinner.j) {
                    neurons[i][j].correctPotential(false);
                }
            }
        }
    }

    // поиск нейрона с минимальным расстоянием до текущего вектора
    public Neuron findNeuronWinnerWTM(double[] vector) {

        neurons[0][0].calcDistanceBetweenNeuronAndInputVector(vector);
        Neuron neuronWithMinDistance = neurons[0][0];

        for (int i = 0; i < Config.sizeOfMap; i++) {
            for (int j = 0; j < Config.sizeOfMap; j++) {
                if (neuronWithMinDistance.calcDistanceBetweenNeuronAndInputVector(vector) >
                        neurons[i][j].calcDistanceBetweenNeuronAndInputVector(vector)) {
                    neuronWithMinDistance = neurons[i][j];
                }
            }
        }
        return neuronWithMinDistance;
    }

    private Neuron findNeuronWinnerWTA(double[] vector) {

        Neuron neuronWithMinDistance = neurons[0][0];

        for (int i = 0; i < Config.sizeOfMap; i++) {
            for (int j = 0; j < Config.sizeOfMap; j++) {
                if (neurons[i][j].p > Config.pMin) {
                    neuronWithMinDistance = neurons[i][j];
                    break;
                }
            }
        }

        for (int i = 0; i < Config.sizeOfMap; i++) {
            for (int j = 0; j < Config.sizeOfMap; j++) {
                if (neuronWithMinDistance.calcDistanceBetweenNeuronAndInputVector(vector) >
                        neurons[i][j].calcDistanceBetweenNeuronAndInputVector(vector) &&
                        neurons[i][j].p > Config.pMin) {
                    neuronWithMinDistance = neurons[i][j];
                }
            }
        }
        return neuronWithMinDistance;
    }

    // переопределение компонентов векторов датасета в соответствии с формулой 5.8
    public double[][] normalizationSet(double[][] data) {
        double[][] result = new double[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            double divider = calcDividerForNormalization(data[i]);
            for (int j = 0; j < Config.countAttrsInVector; j++) {
                result[i][j] = data[i][j] / divider;
            }
            result[i][data[0].length-1] = data[i][data[0].length-1];
        }
        return result;
    }

    // расчёт знаменателя из обучающего вектора для формулы 5.8
    private double calcDividerForNormalization(double[] vector) {
        double result = 0;
        for (int i = 0; i < Config.countAttrsInVector; i++) {
            result += Math.pow(vector[i], 2);
        }
        result = Math.sqrt(result);
        return result;
    }

    // расчёт значения ошибки для 1 нейрона на всём количестве векторов формула 5.13, опечатка в формуле дробь 1/2,
    // должно быть 1/p по книжке оссовского
    public double calculateError(double[][] dataset) {
        double result = 0;
        for (int i = 0; i < dataset.length; i++) {
            Neuron neuronWinner = findNeuronWinnerWTM(dataset[i]);
            for (int j = 0; j < Config.countAttrsInVector; j++) {
                result += Math.pow(neuronWinner.calcDistanceBetweenNeuronAndInputVector(dataset[i]), 2);
            }
        }
        result = 1.0 / Config.countNeurons * result;
        return result;
    }


    public void printErrorKohonen2dMapOnTrainDataset() {
        System.out.println("----------------printErrorKohonen2dMapOnTrainDataset----------------");
        System.out.print(String.format("E = %.5f ", calculateError(train)));
        System.out.println();
    }

    public void printErrorKohonen2dMapOnTestDataset() {
        System.out.println("----------------printErrorKohonen2dMapOnTestDataset----------------");
        System.out.print(String.format("E = %.5f ", calculateError(test)));
        System.out.println();
    }


    public void testAndPrintKohonen2dMap() {
        System.out.println("----------------testAndPrintKohonen2dMap----------------");
        for (int d = 0; d < train.length; d++) {
            Neuron neuronWinner = findNeuronWinnerWTM(train[d]);
            neuronWinner.clearClassCounts();
            if ((int) train[d][train[0].length - 1] == 1000) {
                neuronWinner.countClassA++;
            }
            if ((int) train[d][train[0].length - 1] == 2000) {
                neuronWinner.countClassB++;
            }
            if ((int) train[d][train[0].length - 1] == 3000) {
                neuronWinner.countClassC++;
            }
        }

        for (int i = 0; i < Config.sizeOfMap; i++) {
            for (int j = 0; j < Config.sizeOfMap; j++) {
                int a = neurons[i][j].countClassA;
                int b = neurons[i][j].countClassB;
                int c = neurons[i][j].countClassC;
                System.out.print(String.format("[A:%02d,B:%02d,C:%02d]", a, b, c));
            }
            System.out.println();
        }
    }

    public void testAndPrintKohonen2dMapVer2() {
        System.out.println("----------------testAndPrintKohonen2dMapVer2----------------");
        for (int d = 0; d < train.length; d++) {
            Neuron neuronWinner = findNeuronWinnerWTM(train[d]);
            neuronWinner.clearClassCounts();
            if ((int) train[d][train[0].length - 1] == 1000) {
                neuronWinner.countClassA++;
            }
            if ((int) train[d][train[0].length - 1] == 2000) {
                neuronWinner.countClassB++;
            }
            if ((int) train[d][train[0].length - 1] == 3000) {
                neuronWinner.countClassC++;
            }
        }

        for (int i = 0; i < Config.sizeOfMap; i++) {
            for (int j = 0; j < Config.sizeOfMap; j++) {
                int a = neurons[i][j].countClassA;
                int b = neurons[i][j].countClassB;
                int c = neurons[i][j].countClassC;

                if (a == 0 && b == 0 && c == 0) {
                    System.out.print("[0000]");
                } else if (a > b && a > c) {
                    System.out.printf("[A:%02d]", a);
                } else if (b > a && b > c) {
                    System.out.printf("[B:%02d]", b);
                } else if (c > a && c > b) {
                    System.out.printf("[C:%02d]", c);
                } else {
                    System.out.print("[%%%%]");
                }

            }
            System.out.println();
        }
    }

    public void testAndPrintKohonen2dMapVer3() {
        System.out.println("----------------testAndPrintKohonen2dMapVer3----------------");
        for (int d = 0; d < train.length; d++) {
            Neuron neuronWinner = findNeuronWinnerWTM(train[d]);
            neuronWinner.clearClassCounts();
            if ((int) train[d][train[0].length - 1] == 1000) {
                neuronWinner.countClassA++;
            }
            if ((int) train[d][train[0].length - 1] == 2000) {
                neuronWinner.countClassB++;
            }
            if ((int) train[d][train[0].length - 1] == 3000) {
                neuronWinner.countClassC++;
            }
        }

        for (int i = 0; i < Config.sizeOfMap; i++) {
            for (int j = 0; j < Config.sizeOfMap; j++) {
                int a = neurons[i][j].countClassA;
                int b = neurons[i][j].countClassB;
                int c = neurons[i][j].countClassC;


                if (a > b && a > c) {
                    //только класс А
                    System.out.print(" a ");
                } else if (b > a && b > c) {
                    //только класс Б
                    System.out.print(" b ");
                } else if (c > a && c > b) {
                    //только класс С
                    System.out.print(" c ");
                } else if (a == 0 && b == 0 && c == 0) {
                    //никто не попал
                    System.out.print(" - ");
                } else {
                    //попали разные образцы
                    System.out.print(" % ");
                }

            }
            System.out.println();
        }
    }
}
