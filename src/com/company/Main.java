package com.company;

import com.company.nn.NeuralNetwork;

public class Main {


    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork();
        neuralNetwork.train();
        neuralNetwork.printErrorKohonen2dMapOnTrainDataset();
        neuralNetwork.testAndPrintKohonen2dMap();
        neuralNetwork.testAndPrintKohonen2dMapVer2();
        neuralNetwork.testAndPrintKohonen2dMapVer3();
        neuralNetwork.printErrorKohonen2dMapOnTestDataset();
    }
}
