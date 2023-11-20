package method;

import java.util.ArrayList;

public class Methods {

    public static int[][] northEastMethod(int[] demand, int[] offer, int summary) {
        int[][] values = new int[offer.length][demand.length];
        int row = 0, column = 0;

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                System.out.print(values[i][j] + "\t");
            }
            System.out.print(offer[i] + "\n");
        }
        for (int i = 0; i < demand.length; i++) {
            System.out.print(demand[i] + "\t");
        }
        System.out.println(summary);
        System.out.println("------------------");

        while (summary > 0 || (row == offer.length - 1 && column == demand.length - 1)) {
            if (offer[row] <= demand[column]) {
                values[row][column] = offer[row];
                summary -= offer[row];
                demand[column] -= offer[row];
                offer[row] -= offer[row];
                row += 1;
            } else {
                values[row][column] = demand[column];
                summary -= demand[column];
                offer[row] -= demand[column];
                demand[column] -= demand[column];
                column += 1;
            }
            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < values[i].length; j++) {
                    System.out.print(values[i][j] + "\t");
                }
                System.out.print(offer[i] + "\n");
            }
            for (int i = 0; i < demand.length; i++) {
                System.out.print(demand[i] + "\t");
            }
            System.out.println(summary);
            System.out.println("------------------");
        }
        if (summary == 0) {
            return values;
        }
        return null;
    }

    public static int[][] getAlphasAndBetas(int[][] values, int[][] valency) {
        Integer[] alphas = new Integer[values.length];
        Integer[] betas = new Integer[values[0].length];

        for (Integer i: alphas) {
            i = null;
        }

        for (Integer i: betas) {
            i = null;
        }

        alphas[0] = 0;

        boolean allAlphasAndBetasFound = false;

        while (!allAlphasAndBetasFound) {
            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < values[i].length; j++) {
                    if (values[i][j] == 0) {
                        continue;
                    }
                    if (alphas[i] != null && betas[j] == null) {
                        betas[j] = valency[i][j] - alphas[i];
                    }
                    if (alphas[i] == null && betas[j] != null) {
                        alphas[i] = valency[i][j] - betas[j];
                    }
                }
            }

            allAlphasAndBetasFound = true;
            for (Integer i: alphas) {
                if (i == null) {
                    allAlphasAndBetasFound = false;
                }
            }
            for (Integer i: betas) {
                if (i == null) {
                    allAlphasAndBetasFound = false;
                }
            }
        }

        int[] resultedAlphas = new int[values.length];
        int[] resultedBetas = new int[values[0].length];

        for (int i = 0; i < alphas.length; i++) {
            resultedAlphas[i] = alphas[i];
        }

        for (int i = 0; i < betas.length; i++) {
            resultedBetas[i] = betas[i];
        }

        int[][] result = new int[][] {resultedAlphas, resultedBetas};

        return result;
    }

    public static ArrayList<Cell> getDeltas(int[] alphas, int[] betas, int[][] values, int[][] valency) {
        ArrayList<Cell> deltas = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                if (values[i][j] == 0) {
                    deltas.add(new Cell(i, j,valency[i][j] - alphas[i] - betas[j]));
                }
            }
        }

        return deltas;
    }

    public static ArrayList<Cell> getCells(Cell delta, int[][] values) {
        int[][] roadMap = new int[values.length][values[0].length];
        for (int[] i: roadMap) {
            for (int j: i) {
                j = 0;
            }
        }

        return searchRoad(roadMap, delta);
    }

    private static ArrayList<Cell> searchRoad(int[][] roadMap, Cell delta) {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(delta);
        
        return cells;
    }

}
