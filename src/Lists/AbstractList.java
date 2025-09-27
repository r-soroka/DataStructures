package Lists;

public abstract class AbstractList<Type> implements MagpieList<Type> {
    //start Instance Variable(s)
    protected int size = 0;
    //end Instance Variable(s)

    //start Method(s)
    @Override
    public int size() {return size;}

    @Override
    public abstract Type get(int index);

    @Override
    public boolean isEmpty() {return size == 0;}

    @Override
    public void clear() {size=0;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(get(i));
            if (i != size - 1) sb.append(", ");
        } //end for loop
        return sb.append("]").toString();
    } // end toString
    //end Method(s)
} //end class AbstractList<Type>