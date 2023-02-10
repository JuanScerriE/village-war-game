package util;

import java.util.*;

@SuppressWarnings("unchecked")
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

    public boolean addList(List<? extends T> list) {
        boolean changed = false;

        for (var object : list) {
            changed = add(object);
        }

        return changed;
    }

    public boolean addAll(CategoryList<T> list) {
        boolean changed = false;

        for (var object : list) {
            changed = add(object);
        }

        return changed;
    }

    public <U extends T> boolean remove(U object) {
        LinkedList<U> list = (LinkedList<U>) _categories.get(object.getClass());

        if (list == null) {
            return false;
        }

        return list.remove(object);
    }

    public boolean removeList(List<? extends T> list) {
        boolean changed = false;

        for (var object : list) {
            changed = remove(object);
        }

        return changed;
    }

    public boolean removeAll(CategoryList<T> list) {
        boolean changed = false;

        for (var object : list) {
            changed = remove(object);
        }

        return changed;
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

    public <U extends T> boolean hasCategory(Class<U> valueType) {
        var category = _categories.get(valueType);

        return category != null && !category.isEmpty();
    }

    public int size() {
        if (_categories.isEmpty()) {
            return 0;
        }

        int size = 0;

        for (var category : _categories.values()) {
            size += category.size();
        }

        return size;
    }

    public <U extends T> int sizeOfCategory(Class<U> valueType) {
        if (!hasCategory(valueType)) {
            return 0;
        }

        return _categories.get(valueType).size();
    }

    @Override
    public Iterator<T> iterator() {
        return new CategoryListIterator<>(_categories.values());
    }

    public static class CategoryListIterator<T> implements Iterator<T> {
        private final Iterator<LinkedList<? extends T>> _categories;
        private Iterator<? extends T> _category = null;

        public CategoryListIterator(Collection<LinkedList<? extends T>> categories) {
            _categories = categories.iterator();
        }

        @Override
        public boolean hasNext() {
            while (_categories.hasNext() || (_category != null && _category.hasNext())) {
                if (_category != null && _category.hasNext()) {
                    return true;
                }

                _category = _categories.next().iterator();
            }

            return false;
        }

        @Override
        public T next() {
            return _category.next();
        }
    }
}
