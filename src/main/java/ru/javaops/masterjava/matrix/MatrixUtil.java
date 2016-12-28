package ru.javaops.masterjava.matrix;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/**
 * gkislin
 * 03.07.2016
 */
public class MatrixUtil {

    // TODO implement parallel multiplication matrixA*matrixB
    public static int[][] concurrentMultiply(int[][] matrixA, int[][] matrixB, ExecutorService executor) throws InterruptedException, ExecutionException {
        int[][] matrixC;// = new int[matrixSize][matrixSize];
        return (int[][])executor.submit(new Callable() {
            public int[][] call() throws Exception {
                return singleThreadMultiply(matrixA, matrixB);
            }
        }).get();

        //return null;
    }

    public static int[][] singleThreadMultiply(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];

        int currentColumn[] = new int[matrixSize];

        try {
            for (int j = 0; ; j++) {
                for (int k = 0; k < matrixSize; k++) {
                    currentColumn[k] = matrixB[k][j];
                }
                for (int i = 0; i < matrixSize; i++) {
                    int currentRow[] = matrixA[i];
                    int sum = 0;
                    for (int k = 0; k < matrixSize; k++) {
                        sum += currentRow[k] * currentColumn[k];
                    }
                    matrixC[i][j] = sum;
                }
            }


/*
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    int sum = 0;
                    for (int k = 0; k < matrixSize; k++) {
                        sum += matrixA[i][k] * matrixB[k][j];
                    }
                    matrixC[i][j] = sum;
                }
            }
*/
        }
        catch (IndexOutOfBoundsException ignored) {}
        return matrixC;
    }

    public static int[][] create(int size) {
        int[][] matrix = new int[size][size];
        Random rn = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rn.nextInt(10);
            }
        }
        return matrix;
    }

    public static boolean compare(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
