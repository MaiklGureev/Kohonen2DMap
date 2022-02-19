package com.company;

import com.company.nn.AlgorithmType;

import java.util.Random;

public class Config {

    public static AlgorithmType algorithmDefaultType = AlgorithmType.WTA;
    public static int indicatorClassA = 1000;
    public static int indicatorClassB = 2000;
    public static int indicatorClassC = 3000;
    public static int indicatorClassNone = 0;
    public static int indicatorClassUndefined = -1;

    // настройки для WTA
    public static double lambdaMin = 1;
    public static double lambdaMax = 5;

    public static double nMin = 0.01;
    public static double nDefault = 0.5;

    public static double pMin = 0.7;
    public static double pDefault = 0.75;

    public static double startWeight = 0.1;

    public static int sizeOfMap = 10;
    public static int countNeurons = sizeOfMap * sizeOfMap;

    public static int countAttrsInVector = 4;
    public static int countTrainIterations = 1000;
    public static int printErrorEvery = countTrainIterations / 10;
    public static boolean isEnableNormalization = true;

    public static Random random = new Random(1);

    public static int radius = 3;

    // b  b  b  b  a  a  c  a  b  -
    // c  b  -  a  a  b  c  a  -  c
    // b  a  -  c  b  c  c  -  -  c
    // b  a  c  a  c  c  b  -  c  c
    // -  b  a  c  b  -  -  b  b  a
    // -  -  b  -  -  c  -  b  -  -
    // a  -  -  b  -  b  b  -  -  -
    // c  -  -  b  -  -  a  a  -  -
    // -  -  -  -  -  -  -  c  -  b
    // -  -  -  -  c  -  b  a  -  c

    //оптимальные настройки для гауссовского соседства WTM
//    public static double lambdaMin = 1;
//    public static double lambdaMax = 4;
//
//    public static double nMin = 0.01;
//    public static double nDefault = 0.05;
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
//    public static int countTrainIterations = 3000;
//    public static int printErrorEvery = countTrainIterations / 10;
//    public static boolean isEnableNormalization = true;
//
//    public static Random random = new Random(10);
//
//    public static int radius = 1;

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
