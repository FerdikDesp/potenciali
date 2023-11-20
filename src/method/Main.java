package method;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[][] valency = new int[][] {{2, 7, 2}, {4, 1, 6}, {3, 5, 2}};
        /*for (int[] i: valency) {
            for (int j: i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }*/
        int[] demand = new int[] {61, 12, 74};
        int[] offer = new int[] {80, 42, 25};
        int summary = 147;

        int[][] values = Methods.northEastMethod(demand, offer, summary);

        for (int[] i: values) {
            for (int j: i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        System.out.println("------------------");

        int[][] alphasAndBetas = Methods.getAlphasAndBetas(values, valency);

        for (int[] i: alphasAndBetas) {
            for (int j: i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        System.out.println("------------------");

        int[] alphas = alphasAndBetas[0];
        int[] betas = alphasAndBetas[1];

        ArrayList<Cell> deltas = Methods.getDeltas(alphas, betas, values, valency);

        for (Cell delta: deltas) {
            System.out.println("(" + delta.getRow() + " " + delta.getColumn() + " " + delta.getValue() + ")");
        }
        System.out.println("------------------");
    }
}