import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashMap;

public class CategoryList<T> implements Iterable<T> {
    private final HashMap<Class<? extends T>, LinkedList<? extends T>> _map = new HashMap<>();

    public <U extends T> boolean add(U object) {
        LinkedList<U> list = (LinkedList<U>) _map.get(object.getClass());

        if (list == null) {
            list = new LinkedList<>();

            _map.put((Class<? extends T>) object.getClass(), list);
        }

        return list.add(object);
    }

    public <U extends T> LinkedList<U> getCategory(Class<U> valueType) {
        return (LinkedList<U>) _map.get(valueType);
    }

    @Override
    public Iterator<T> iterator() {
        return new CategoryListIterator<>(_map.values().iterator());
    }
}
