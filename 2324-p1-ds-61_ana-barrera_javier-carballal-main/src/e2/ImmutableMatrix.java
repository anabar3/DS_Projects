package e2;

import java.sql.Array;
import java.util.Arrays;

public class ImmutableMatrix {

    private final int[][] mat;

    private boolean isRagged(int[][] arr){
        int rows = arr.length, columns = arr[0].length;
        for (int i=1; i<rows; i++){
            if(arr[i].length != columns) return true;
        }
        return false;
    }

    public ImmutableMatrix(int [][] arr) {
        if(isRagged(arr)) throw new IllegalArgumentException("Matrix must not be ragged");

        int rows = arr.length, columns = arr[0].length;
        mat = new int[rows][columns];

        for(int i = 0; i < rows; i++){
            mat[i] = Arrays.copyOf(arr[i], columns);
        }
    }
    public ImmutableMatrix(int rows, int columns){

        if (rows<=0 || columns <=0) throw new IllegalArgumentException("Invalid row / column size");

        mat = new int[rows][columns];
        int counter = 1;


        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                mat[i][j] = counter;
                counter += 1;
            }
        }
    }

    @Override
    public String toString(){
        String matString = "";
        for(int i = 0; i < mat.length; i++){
            matString = matString.concat(Arrays.toString(mat[i])+'\n');
        }
        return matString;
    }

    public int at(int posrow, int poscol){
        if (posrow < 0 || posrow > rowCount() || poscol < 0 || poscol > columnCount()) throw new IllegalArgumentException("Invalid indexes");
        return mat [posrow][poscol];
    }

    public int rowCount (){
        return mat.length;
    }

    public int columnCount(){
        return mat[0].length;
    }

    public int [][] toArray2D(){
        return Arrays.copyOf(mat, mat.length);
    }

    public ImmutableMatrix reverse(){
        ImmutableMatrix originalMatrix = new ImmutableMatrix(mat);
        int rows = originalMatrix.rowCount();
        int columns = originalMatrix.columnCount();

        int [][] newarr = new int[rows][columns];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                newarr[i][j] = originalMatrix.at(i, columns-j-1);
            }
        }

        return new ImmutableMatrix(newarr);
    }

    public ImmutableMatrix transpose(){
        ImmutableMatrix originalMatrix = new ImmutableMatrix(mat);
        int rows = originalMatrix.rowCount();
        int columns = originalMatrix.columnCount();

        int [][] newarr = new int[columns][rows];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                newarr[j][i] = originalMatrix.at(i, j);
            }
        }

        return new ImmutableMatrix(newarr);
    }

    public ImmutableMatrix reshape(int newColumns){
        ImmutableMatrix originalMatrix = new ImmutableMatrix(mat);
        int rows = originalMatrix.rowCount();
        int columns = originalMatrix.columnCount();

        if(rows*columns%newColumns != 0) throw new IllegalArgumentException("Matrix must not be ragged");

        int newRows = rows*columns/newColumns;

        int [][] newarr = new int[newRows][newColumns];

        for(int i = 0; i < newRows; i++){
            for(int j = 0; j < newColumns; j++){
                int absPos = newColumns * i + j;
                newarr[i][j] = originalMatrix.at(absPos/columns,absPos%columns);
            }
        }

        return new ImmutableMatrix(newarr);
    }


}

