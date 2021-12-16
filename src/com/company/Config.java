package com.company;

import com.company.nn.AlgorithmType;

import java.util.Random;

public class Config {

    public static AlgorithmType algorithmDefaultType = AlgorithmType.WTM;
    public static int indicatorClassA = 1000;
    public static int indicatorClassB = 2000;
    public static int indicatorClassC = 3000;
    public static int indicatorClassNone = 0;
    public static int indicatorClassUndefined = -1;


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
//    public static int radius = 1;

    //  c  -  c  -  c  -  c  -  a  -
    // -  -  -  c  -  c  -  -  -  a
    // c  -  c  -  -  -  c  -  a  -
    // -  -  -  c  -  b  -  -  -  a
    // c  -  c  -  c  -  b  -  a  -
    // -  c  -  b  -  b  -  -  -  -
    // c  -  c  -  b  -  b  -  b  -
    // -  -  -  b  -  b  -  b  -  b
    // -  -  -  -  b  -  b  -  -  -
    // -  -  -  b  -  b  -  b  -  b

    //оптимальные настройки для гауссовского соседства WTM
    public static double lambdaMin = 1;
    public static double lambdaMax = 4;

    public static double nMin = 0.01;
    public static double nDefault = 0.05;

    public static double pMin = 0.74;
    public static double pDefault = 0.75;

    public static double startWeight = 0.1;

    public static int sizeOfMap = 10;
    public static int countNeurons = sizeOfMap * sizeOfMap;

    public static int countAttrsInVector = 4;
    public static int countTrainIterations = 3000;
    public static int printErrorEvery = countTrainIterations / 10;
    public static boolean isEnableNormalization = true;

    public static Random random = new Random(10);

    public static int radius = 1;

    // c  -  c  -  c  -  c  -  b  -
    // -  -  -  c  -  c  -  b  -  b
    // c  -  c  -  c  -  c  -  b  -
    // -  -  -  b  -  c  -  -  -  -
    // c  -  c  -  b  -  -  -  a  -
    // -  b  -  b  -  b  -  a  -  a
    // b  -  b  -  b  -  -  -  a  -
    // -  b  -  b  -  b  -  a  -  a
    // b  -  b  -  b  -  -  -  a  -
    // -  -  -  b  -  b  -  a  -  a
}
