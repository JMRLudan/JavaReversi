import java.util.Iterator;
import java.util.List;

public class PathSet implements SimpleCollection<List<String>> {
    
private PathSetNode root;
public PathSet() {
this.root = new PathSetNode();
}
@Override
public boolean isEmpty() {
return root.isEmpty();
}
@Override
public void add(List<String> path) {
root.add(path);
}
@Override
public boolean contains(Object o) {
List<String> path = (List<String>) o;
return root.contains(path);
}
@Override
public Iterator<List<String>> iterator() {
List<List<String>> lls = root.toListOfPaths();
return lls.iterator();
}
}