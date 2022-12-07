package tick;

// Standard Library
import java.math.BigInteger;

public class Tick {
    private static Tick _instance = null;

    private BigInteger _tick = BigInteger.ZERO;
    private Tick() {}

    public static Tick getInstance() {
        if (_instance == null) {
            _instance = new Tick();
        }

        return _instance;
    }

    public boolean greaterThan(BigInteger tick) {
        return _tick.compareTo(tick) > 0;
    }

    public boolean greaterThan(Integer tick) {
        return greaterThan(new BigInteger(tick.toString()));
    }

    public boolean lessThan(BigInteger tick) {
        return _tick.compareTo(tick) < 0;
    }

    public boolean lessThan(Integer tick) {
        return lessThan(new BigInteger(tick.toString()));
    }

    public void increment() {
        _tick = _tick.add(BigInteger.ONE);
    }
}
