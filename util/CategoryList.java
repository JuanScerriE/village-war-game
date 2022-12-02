package util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashMap;

public class CategoryList<T> implements Iterable<T> {
    private final HashMap<Class<? extends T>, LinkedList<? extends T>> _categories = new HashMap<>();

    public <U extends T> boolean add(U object) {
        LinkedList<U> list = (LinkedList<U>) _categories.get(object.getClass());

        if (list == null) {
            list = new LinkedList<>();

            _categories.put((Class<? extends T>) object.getClass(), list);
        }

        return list.add(object);
    }

    public boolean isEmpty() {
        if (_categories.isEmpty()) {
            return true;
        }

        for (var category : _categories.values()) {
            if (!category.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public <U extends T> LinkedList<U> getCategory(Class<U> valueType) {
        return (LinkedList<U>) _categories.get(valueType);
    }

    @Override
    public Iterator<T> iterator() {
        return new CategoryListIterator<>(_categories.values().iterator());
    }
}
