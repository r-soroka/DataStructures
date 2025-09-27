package Lists;

public interface MagpieList<Type> {
    void addFirst(Type data);
    Type removeFirst();
    void addLast(Type data);
    Type removeLast();
    int size();

    Type get(int index);

    boolean isEmpty();
    void clear();
}