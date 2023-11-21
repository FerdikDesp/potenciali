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

            System.out.println("alphas");
            for (Integer i: alphas) {
                System.out.print(i + " ");
            }
            System.out.println("betas");
            for (Integer i: betas) {
                System.out.print(i + " ");
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

    public static int[][] searchRoad(int[][] values, Cell delta) {
        int[][] roadMap = new int[values.length][values[0].length];
        roadMap[delta.getRow()][delta.getColumn()] = 1;

        roadMap = goUp(values, roadMap, delta, delta);

        boolean isMapClear = true;

        for (int i = 0; i < roadMap.length; i++) {
            for (int j = 0; j < roadMap[i].length; j++) {
                if (i == delta.getRow() && j == delta.getColumn()) {
                    if (roadMap[i][j] == 0 || roadMap[i][j] == -1) {
                        isMapClear = false;
                    }
                } else {
                    if (roadMap[i][j] == 1 || roadMap[i][j] == -1) {
                        isMapClear = false;
                    }
                }
            }
        }

        if (isMapClear) {
            roadMap = goDown(values, roadMap, delta, delta);
        } else {
            return roadMap;
        }

        for (int i = 0; i < roadMap.length; i++) {
            for (int j = 0; j < roadMap[i].length; j++) {
                if (i == delta.getRow() && j == delta.getColumn()) {
                    if (roadMap[i][j] == 0 || roadMap[i][j] == -1) {
                        isMapClear = false;
                    }
                } else {
                    if (roadMap[i][j] == 1 || roadMap[i][j] == -1) {
                        isMapClear = false;
                    }
                }
            }
        }

        if (isMapClear) {
            roadMap = goLeft(values, roadMap, delta, delta);
        } else {
            return roadMap;
        }

        for (int i = 0; i < roadMap.length; i++) {
            for (int j = 0; j < roadMap[i].length; j++) {
                if (i == delta.getRow() && j == delta.getColumn()) {
                    if (roadMap[i][j] == 0 || roadMap[i][j] == -1) {
                        isMapClear = false;
                    }
                } else {
                    if (roadMap[i][j] == 1 || roadMap[i][j] == -1) {
                        isMapClear = false;
                    }
                }
            }
        }

        if (isMapClear) {
            roadMap = goRight(values, roadMap, delta, delta);
        } else {
            return roadMap;
        }

        for (int i = 0; i < roadMap.length; i++) {
            for (int j = 0; j < roadMap[i].length; j++) {
                if (i == delta.getRow() && j == delta.getColumn()) {
                    if (roadMap[i][j] == 0 || roadMap[i][j] == -1) {
                        isMapClear = false;
                    }
                } else {
                    if (roadMap[i][j] == 1 || roadMap[i][j] == -1) {
                        isMapClear = false;
                    }
                }
            }
        }

        return roadMap;

        /*ArrayList<Cell> road = new ArrayList<>();
        road.add(delta);

        road.add(goUp(roadMap, delta, road));
        road.add(goDown(roadMap, delta, road));
        road.add(goLeft(roadMap, delta, road));
        road.add(goRight(roadMap, delta, road));

        return road;*/
    }

    public static int[][] goUp(int[][] values, int[][] roadMap, Cell delta, Cell current) {
        for (int[] i: roadMap) {
            for (int j: i) {
                if (j == 2) {
                    return roadMap;
                }
            }
        }

        for (int[] i: roadMap) {
            for (int j: i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        System.out.println("------------------");
        System.out.println("Движение вверх, изн. позиция ячейки: Строка " + current.getRow() + ", столбец " + current.getColumn());
        boolean isEven = true;

        for (int[] i: roadMap) {
            for (int j: i) {
                if (j != 0) {
                    isEven = !isEven;
                }
            }
        }

        boolean isAnyVariants = false;

        for (int i = current.getRow() - 1; i >= 0; i--) {
            if (roadMap[i][current.getColumn()] != 0) {
                System.out.println("Найдено совпадение!");
                roadMap[current.getRow()][i] = 2;
                return roadMap;
            }
            if (values[i][current.getColumn()] != 0) {
                if (isEven) {
                    roadMap[i][current.getColumn()] = 1;
                } else {
                    roadMap[i][current.getColumn()] = -1;
                }
                int[][] roadMapNew = roadMap;
                roadMapNew = goLeft(values, roadMap, delta, new Cell(i, current.getColumn(), current.getValue()));
                for (int[] k: roadMapNew) {
                    for (int j: k) {
                        if (j == 2) {
                            return roadMapNew;
                        }
                    }
                }
                boolean isRoadsEquals = true;
                for (int k1 = 0; k1 < roadMapNew.length; k1++) {
                    for (int j = 0; j < roadMapNew[k1].length; j++) {
                        //if (roadMapNew[k1][j] != roadMap[k1][j]) {
                        if (roadMapNew[k1][j] == 2) {
                            isRoadsEquals = false;
                        }
                    }
                }
                if (isRoadsEquals) {
                    roadMapNew = roadMap;
                    roadMapNew = goRight(values, roadMap, delta, new Cell(i, current.getColumn(), current.getValue()));
                    for (int[] k: roadMapNew) {
                        for (int j: k) {
                            if (j == 2) {
                                return roadMapNew;
                            }
                        }
                    }
                    isRoadsEquals = true;
                    for (int k1 = 0; k1 < roadMapNew.length; k1++) {
                        for (int j = 0; j < roadMapNew[k1].length; j++) {
                            //if (roadMapNew[k1][j] != roadMap[k1][j]) {
                            if (roadMapNew[k1][j] == 2) {
                                isRoadsEquals = false;
                            }
                        }
                    }

                    if (isRoadsEquals) {
                        roadMap[i][current.getColumn()] = 0;
                    }
                }
            }
        }

        if (!isAnyVariants) {
            System.out.println("Вариантов нет, откатываемся");
            /*for (int i = 0; i < roadMap.length; i++) {
                for (int j = 0; j < roadMap[i].length; j++) {
                    if (i == delta.getRow() && j == delta.getColumn()) {
                        roadMap[i][j] = 1;
                    } else {
                        roadMap[i][j] = 0;
                    }
                }
            }*/
            /*roadMap[current.getRow()][current.getColumn()] = 0;
            roadMap[delta.getRow()][delta.getColumn()] = 1;*/
        }

        return roadMap;
    }

    public static int[][] goDown(int[][] values, int[][] roadMap, Cell delta, Cell current) {
        for (int[] i: roadMap) {
            for (int j: i) {
                if (j == 2) {
                    return roadMap;
                }
            }
        }

        for (int[] i: roadMap) {
            for (int j: i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        System.out.println("------------------");
        System.out.println("Движение вниз, изн. позиция ячейки: Строка " + current.getRow() + ", столбец " + current.getColumn());
        boolean isEven = true;

        for (int[] i: roadMap) {
            for (int j: i) {
                if (j != 0) {
                    isEven = !isEven;
                }
            }
        }

        boolean isAnyVariants = false;

        for (int i = current.getRow() + 1; i < values.length; i++) {
            if (roadMap[i][current.getColumn()] != 0) {
                System.out.println("Найдено совпадение!");
                roadMap[current.getRow()][i] = 2;
                return roadMap;
            }
            if (values[i][current.getColumn()] != 0) {
                if (isEven) {
                    roadMap[i][current.getColumn()] = 1;
                } else {
                    roadMap[i][current.getColumn()] = -1;
                }
                int[][] roadMapNew = roadMap;
                roadMapNew = goLeft(values, roadMap, delta, new Cell(i, current.getColumn(), current.getValue()));
                for (int[] k: roadMapNew) {
                    for (int j: k) {
                        if (j == 2) {
                            return roadMapNew;
                        }
                    }
                }
                boolean isRoadsEquals = true;

                for (int[] k: roadMap) {
                    for (int j: k) {
                        System.out.print(j + "\t");
                    }
                    System.out.println();
                }

                System.out.println("------------------");
                System.out.println("------------------");

                for (int[] k: roadMapNew) {
                    for (int j: k) {
                        System.out.print(j + "\t");
                    }
                    System.out.println();
                }

                for (int k1 = 0; k1 < roadMapNew.length; k1++) {
                    for (int j = 0; j < roadMapNew[k1].length; j++) {
                        //if (roadMapNew[k1][j] != roadMap[k1][j]) {
                        if (roadMapNew[k1][j] == 2) {
                            isRoadsEquals = false;
                        }
                    }
                }
                System.out.println(isRoadsEquals);
                if (isRoadsEquals) {
                    roadMapNew = roadMap;
                    roadMapNew = goRight(values, roadMap, delta, new Cell(i, current.getColumn(), current.getValue()));
                    for (int[] k: roadMapNew) {
                        for (int j: k) {
                            if (j == 2) {
                                return roadMapNew;
                            }
                        }
                    }
                    isRoadsEquals = true;
                    for (int k1 = 0; k1 < roadMapNew.length; k1++) {
                        for (int j = 0; j < roadMapNew[k1].length; j++) {
                            //if (roadMapNew[k1][j] != roadMap[k1][j]) {
                            if (roadMapNew[k1][j] == 2) {
                                isRoadsEquals = false;
                            }
                        }
                    }

                    if (isRoadsEquals) {
                        roadMap[i][current.getColumn()] = 0;
                    }
                }
            }
        }

        if (!isAnyVariants) {
            System.out.println("Вариантов нет, откатываемся");
            /*for (int i = 0; i < roadMap.length; i++) {
                for (int j = 0; j < roadMap[i].length; j++) {
                    if (i == delta.getRow() && j == delta.getColumn()) {
                        roadMap[i][j] = 1;
                    } else {
                        roadMap[i][j] = 0;
                    }
                }
            }*/
            /*roadMap[current.getRow()][current.getColumn()] = 0;
            roadMap[delta.getRow()][delta.getColumn()] = 1;*/
        }

        return roadMap;
    }

    public static int[][] goLeft(int[][] values, int[][] roadMap, Cell delta, Cell current) {
        for (int[] i: roadMap) {
            for (int j: i) {
                if (j == 2) {
                    return roadMap;
                }
            }
        }

        for (int[] i: roadMap) {
            for (int j: i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        System.out.println("------------------");
        System.out.println("Движение влево, изн. позиция ячейки: Строка " + current.getRow() + ", столбец " + current.getColumn());
        boolean isEven = true;

        for (int[] i: roadMap) {
            for (int j: i) {
                if (j != 0) {
                    isEven = !isEven;
                }
            }
        }

        boolean isAnyVariants = false;

        for (int i = current.getColumn() - 1; i >= 0; i--) {
            if (roadMap[current.getRow()][i] != 0) {
                System.out.println("Найдено совпадение!");
                roadMap[current.getRow()][i] = 2;
                return roadMap;
            }
            if (values[current.getRow()][i] != 0) {
                if (isEven) {
                    roadMap[current.getRow()][i] = 1;
                } else {
                    roadMap[current.getRow()][i] = -1;
                }
                int[][] roadMapNew = roadMap;
                roadMapNew = goUp(values, roadMap, delta, new Cell(current.getRow(), i, current.getValue()));
                for (int[] k: roadMapNew) {
                    for (int j: k) {
                        if (j == 2) {
                            return roadMapNew;
                        }
                    }
                }
                boolean isRoadsEquals = true;
                for (int k1 = 0; k1 < roadMapNew.length; k1++) {
                    for (int j = 0; j < roadMapNew[k1].length; j++) {
                        //if (roadMapNew[k1][j] != roadMap[k1][j]) {
                        if (roadMapNew[k1][j] == 2) {
                            isRoadsEquals = false;
                        }
                    }
                }
                if (isRoadsEquals) {
                    roadMapNew = roadMap;
                    roadMapNew = goDown(values, roadMap, delta, new Cell(current.getRow(), i, current.getValue()));
                    for (int[] k: roadMapNew) {
                        for (int j: k) {
                            if (j == 2) {
                                return roadMapNew;
                            }
                        }
                    }
                    isRoadsEquals = true;
                    for (int k1 = 0; k1 < roadMapNew.length; k1++) {
                        for (int j = 0; j < roadMapNew[k1].length; j++) {
                            //if (roadMapNew[k1][j] != roadMap[k1][j]) {
                            if (roadMapNew[k1][j] == 2) {
                                isRoadsEquals = false;
                            }
                        }
                    }

                    if (isRoadsEquals) {
                        roadMap[current.getRow()][i] = 0;
                    }
                }
            }
        }

        if (!isAnyVariants) {
            System.out.println("Вариантов нет, откатываемся");
            /*for (int i = 0; i < roadMap.length; i++) {
                for (int j = 0; j < roadMap[i].length; j++) {
                    if (i == delta.getRow() && j == delta.getColumn()) {
                        roadMap[i][j] = 1;
                    } else {
                        roadMap[i][j] = 0;
                    }
                }
            }*/
            /*roadMap[current.getRow()][current.getColumn()] = 0;
            roadMap[delta.getRow()][delta.getColumn()] = 1;*/
        }

        return roadMap;
    }

    public static int[][] goRight(int[][] values, int[][] roadMap, Cell delta, Cell current) {
        for (int[] i: roadMap) {
            for (int j: i) {
                if (j == 2) {
                    return roadMap;
                }
            }
        }

        for (int[] i: roadMap) {
            for (int j: i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        System.out.println("------------------");
        System.out.println("Движение вправо, изн. позиция ячейки: Строка " + current.getRow() + ", столбец " + current.getColumn());
        boolean isEven = true;

        for (int[] i: roadMap) {
            for (int j: i) {
                if (j != 0) {
                    isEven = !isEven;
                }
            }
        }

        boolean isAnyVariants = false;

        for (int i = current.getColumn() + 1; i < values[0].length; i++) {
            System.out.println("Вариантов нет, откатываемся");
            if (roadMap[current.getRow()][i] != 0) {
                System.out.println("Найдено совпадение!");
                roadMap[current.getRow()][i] = 2;
                return roadMap;
            }
            if (values[current.getRow()][i] != 0) {
                if (isEven) {
                    roadMap[current.getRow()][i] = 1;
                } else {
                    roadMap[current.getRow()][i] = -1;
                }
                int[][] roadMapNew = roadMap;
                roadMapNew = goUp(values, roadMap, delta, new Cell(current.getRow(), i, current.getValue()));
                for (int[] k: roadMapNew) {
                    for (int j: k) {
                        if (j == 2) {
                            return roadMapNew;
                        }
                    }
                }
                boolean isRoadsEquals = true;
                for (int k1 = 0; k1 < roadMapNew.length; k1++) {
                    for (int j = 0; j < roadMapNew[k1].length; j++) {
                        //if (roadMapNew[k1][j] != roadMap[k1][j]) {
                        if (roadMapNew[k1][j] == 2) {
                            isRoadsEquals = false;
                        }
                    }
                }
                if (isRoadsEquals) {
                    roadMapNew = roadMap;
                    roadMapNew = goDown(values, roadMap, delta, new Cell(current.getRow(), i, current.getValue()));
                    for (int[] k: roadMapNew) {
                        for (int j: k) {
                            if (j == 2) {
                                return roadMapNew;
                            }
                        }
                    }
                    isRoadsEquals = true;
                    for (int k1 = 0; k1 < roadMapNew.length; k1++) {
                        for (int j = 0; j < roadMapNew[k1].length; j++) {
                            //if (roadMapNew[k1][j] != roadMap[k1][j]) {
                            if (roadMapNew[k1][j] == 2) {
                                isRoadsEquals = false;
                            }
                        }
                    }

                    if (isRoadsEquals) {
                        roadMap[current.getRow()][i] = 0;
                    }
                }

            }
        }

        if (!isAnyVariants) {
            System.out.println("Вариантов нет, откатываемся");
            /*for (int i = 0; i < roadMap.length; i++) {
                for (int j = 0; j < roadMap[i].length; j++) {
                    if (i == delta.getRow() && j == delta.getColumn()) {
                        roadMap[i][j] = 1;
                    } else {
                        roadMap[i][j] = 0;
                    }
                }
            }*/
            /*roadMap[current.getRow()][current.getColumn()] = 0;
            roadMap[delta.getRow()][delta.getColumn()] = 1;*/
        }

        return roadMap;
    }

}