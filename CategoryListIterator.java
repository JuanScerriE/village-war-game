import java.util.Iterator;
import java.util.LinkedList;

public class CategoryListIterator<T> implements Iterator<T> {
    private final Iterator<LinkedList<? extends T>> _categories;
    private Iterator<? extends T> _category = null;

    public CategoryListIterator(Iterator<LinkedList<? extends T>> categories) {
        _categories = categories;
    }

    @Override
    public boolean hasNext() {
        while (_categories.hasNext() || _category.hasNext()) {
            if (_category == null || !_category.hasNext()) {
                _category = _categories.next().iterator();
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public T next() {
        return _category.next();
    }
}
