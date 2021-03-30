
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class PathSetNode {
    private TreeMap<String, PathSetNode> children;
    private boolean isLast;

    public PathSetNode() {
        this.children = new TreeMap<String, PathSetNode>();
        this.isLast = false;
    }

    public boolean isEmpty() {
        return (!this.isLast && children.isEmpty());
    }

    public void add(List<String> path) {
        PathSetNode pointer = this;
        for (String word : path) {
            if (!pointer.children.containsKey(word)) {
                PathSetNode newNode = new PathSetNode();
                pointer.children.put(word, newNode);
                pointer = newNode;
            } else {
                pointer = pointer.children.get(word);
            }
        }
        pointer.isLast = true;
    }

    public boolean contains(List<String> path) {
        PathSetNode pointer = this;
        for (String word : path) {
            if (!pointer.children.containsKey(word)) {
                return false;
            } else {
                pointer = pointer.children.get(word);
            }
        }
        return pointer.isLast;
    }
    
    private void parser(LinkedList<String> path, List<List<String>> pathList) {
        for (Entry<String, PathSetNode> e : children.entrySet()) {
            LinkedList<String> clone = new LinkedList<String>();
            for (String s : path) {
                clone.add(s);
            }
            clone.add(e.getKey());
            if (e.getValue().isLast) {
                pathList.add(clone);
            } 
            if (!e.getValue().isEmpty()) {
     
                e.getValue().parser(clone, pathList); 
            }          
        }
    }
  
    public List<List<String>> toListOfPaths() {
        List<List<String>> pathList = new LinkedList<List<String>>();
        LinkedList<String> path = new LinkedList<String>();
        parser(path, pathList);
        return pathList;
    }
}
