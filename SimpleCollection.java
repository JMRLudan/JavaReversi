
import java.util.Iterator;
import java.util.List;

public interface SimpleCollection<E> extends Iterable<E> {
boolean isEmpty();
void add(E x);
boolean contains(Object o);
}
