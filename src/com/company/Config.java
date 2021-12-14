package com.company;

import com.company.nn.AlgorithmType;

import java.util.Random;

public class Config {

    public static AlgorithmType algorithmDefaultType = AlgorithmType.WTM;

    // настройки для WTA
//    public static double lambdaMin = 1;
//    public static double lambdaMax = 5;
//
//    public static double nMin = 0.01;
//    public static double nDefault = 0.2;
//
//    public static double pMin = 0.74;
//    public static double pDefault = 0.75;
//
//    public static double startWeight = 0.1;
//
//    public static int sizeOfMap = 10;
//    public static int countNeurons = sizeOfMap * sizeOfMap;
//
//    public static int countAttrsInVector = 4;
//    public static int countTrainIterations = 1000;
//    public static int printErrorEvery = countTrainIterations / 10;
//    public static boolean isEnableNormalization = true;
//
//    public static Random random = new Random(10);
//
//    public static int radius = 3;

    // -  b  -  b  -  b  b  b  -  -
    // a  -  b  -  b  b  -  c  -  -
    // -  -  b  b  -  -  b  c  -  -
    // a  -  -  -  -  -  b  -  -  -
    // b  -  -  -  -  a  b  -  c  -
    // -  -  c  -  b  -  b  -  -  -
    // -  c  -  -  a  -  b  c  -  -
    // -  -  b  b  c  -  -  -  -  -
    // -  -  -  -  -  -  -  -  -  -
    // -  -  -  -  -  -  -  -  -  -

    //оптимальные настройки для гауссовского соседства WTM
    public static double lambdaMin = 1;
    public static double lambdaMax = 2;

    public static double nMin = 0.01;
    public static double nDefault = 0.1;

    public static double pMin = 0.74;
    public static double pDefault = 0.75;

    public static double startWeight = 0.1;

    public static int sizeOfMap = 10;
    public static int countNeurons = sizeOfMap * sizeOfMap;

    public static int countAttrsInVector = 4;
    public static int countTrainIterations = 300;
    public static int printErrorEvery = countTrainIterations/10;
    public static boolean isEnableNormalization = true;

    public static Random random = new Random(10);

    public static int radius = 2;

    // c  c  -  c  c  b  b  -  b  b
    // c  -  -  c  -  c  -  b  b  b
    // -  c  c  -  -  c  -  -  -  b
    // c  c  c  -  c  b  -  -  -  -
    // c  -  -  -  -  b  -  -  -  a
    // -  b  b  b  b  -  -  -  a  a
    // b  b  -  b  -  -  -  -  a  a
    // b  -  -  b  -  -  a  a  -  a
    // b  b  -  b  -  -  a  -  a  a
    // -  b  b  b  b  a  a  a  -  a
}
