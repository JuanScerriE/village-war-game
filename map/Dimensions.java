package map;

public class Dimensions {
    private int _cols;
    private int _rows;

    public Dimensions(int cols, int rows) {
        _cols = cols;
        _rows = rows;
    }

    public Point randomPoint() {
        return new Point(
                (int)(Math.random()*_cols),
                (int)(Math.random()*_rows)
        );
    }
}
