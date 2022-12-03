package map;

public class Point {
    private int _x;
    private int _y;

    public static Point random(int cols, int rows) {
        return new Point(
                (int)(Math.random()*cols),
                (int)(Math.random()*rows)
        );
    }

    public Point(Point other) {
       _x = other._x;
       _y = other._y;
    }
    public Point(int x, int y) {
        _x = x;
        _y = y;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public void update(int x, int y) {
        _x = x;
        _y = y;
    }

    public int distanceFrom(Point other) {
        int deltaX = _x - other._x;
        int deltaY = _y - other._y;

        return (int)Math.sqrt(deltaX*deltaX + deltaY*deltaY);
    }

    public Point add(Point other) {
        _x += other._x;
        _y += other._y;

        return this;
    }

    public Point directionTo(Point other) {
        int deltaX = other._x - _x;
        int deltaY = other._y - _y;

        if (deltaX != 0) {
            deltaX /= Math.abs(deltaX);
        }

        if (deltaY != 0) {
            deltaY /= Math.abs(deltaY);
        }

        return new Point(deltaX, deltaY);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", _x, _y);
    }
}
