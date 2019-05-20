package net.thumbtack.school.file.matrix;

import java.util.*;

public class MatrixNonSimilarRows {
    private int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix){
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows(){
        Set<int[]> result = new HashSet<>();
        List<Set<Integer>> setRows = new ArrayList<>();
        for (int[] row : matrix){
            Set<Integer> set = new HashSet<>();
            for (Integer el : row)
                set.add(el);
            setRows.add(set);
        }

        boolean similar = false;
        List<Integer> skip = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++){
            if (!skip.contains(i)) {
                for (int j = i + 1; j < matrix.length && !similar; j++) {
                    if (setRows.get(i).equals(setRows.get(j))) {
                        similar = true;
                        result.add(matrix[i]);
                        for (int k = j; k < matrix.length; k++)
                            if (setRows.get(i).equals(setRows.get(k)))
                                skip.add(k);
                    }
                }
                if (!similar)
                    result.add(matrix[i]);
                similar = false;
            }
        }
        return result;
    }
}
