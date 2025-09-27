package Lists;

public class SinglyLinkedList<Type> extends AbstractList<Type> {
    //start Inner Class(s)
    private static class Node<Type> {
        //start Instance Variable(s)
        private Type data;
        private Node<Type> link;
        //end Instance Variable(s)

        //start Contructor(s)
        public Node(Type data) {
            this.data = data;
            this.link = null;
        }
        //end Constructor(s)

        //start Setter(s) & Getter(s)
        public Type getData() { return this.data; }
        public void setData(Type data) { this.data = data; }

        public Node<Type> getLink() { return this.link; }
        public void setLink(Node<Type> link) { this.link = link; }
        //end Setter(s) & Getter(s)
    } // end class Node<Type>
    //end Inner Class(s)

    //start Instance Variable(s)
    private int maxSize;
    private Node<Type> head;
    private Node<Type> tail;
    //end Instance Variable(s)

    //start Constructor(s)
    public SinglyLinkedList() {
        this.size = 0;
        this.maxSize = -1;
        this.head = null;
        this.tail = null;
    }
    public SinglyLinkedList(int maxSize) {
        if (maxSize == 0 || maxSize < -1){
            throw new IllegalArgumentException("maxSize must be -1 (unlimited) or > 0");
        }//end if
        this.size = 0;
        this.maxSize = maxSize;
        this.head = null;
        this.tail = null;
    }
    //end Constructor(s)

    //start Add Method(s)
    public void addFirst(Type data) {
        if (this.size != -1 && this.size >= this.maxSize) {
            throw new IllegalArgumentException("max size exceeded");
        }
        Node<Type> node = new Node<>(data);
        node.setLink(this.head);
        this.head = node;
        if (this.tail == null) { this.tail = node; }
        this.size++;
    }
    public void addLast(Type data) {
        if (this.size != -1 && this.size >= this.maxSize) {
            throw new IllegalArgumentException("max size exceeded");
        }
        Node<Type> node = new Node<>(data);
        if (this.tail == null) { this.head = this.tail = node; }
        else {
            this.tail.setLink(node);
            this.tail = node;
        }
        this.size++;
    }
    public void insertAt(int index, Type data) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException("index out of range");
        }
        if (this.size != -1 && this.size >= this.maxSize) {
            throw new IllegalArgumentException("max size exceeded");
        }
        if (index == 0) { addFirst(data); }
        else if (index == this.size) { addLast(data); }
        else {
            Node<Type> node = new Node<>(data);
            Node<Type> curr = this.head;
            for (int i = 1; i < index; i++){
                curr = curr.getLink();
            }
            node.setLink(curr.getLink());
            curr.setLink(node);
           this.size++;
        }
    }
    //end Add Method(s)

    //start Remove Method(s)
    public Type removeFirst() {
        if (this.size == 0){
            throw new IllegalArgumentException("List is empty");
        }
        Type data = this.head.getData();
        if (this.size == 1){
            this.head = null;
            this.tail = null;
        } else { this.head = this.head.getLink(); }
        this.size--;
        return data;
    }
    public Type removeLast() {
        if (this.size == 0) { // empty list
            throw new IllegalStateException("List is empty");
        }
        Type data = this.tail.getData();
        if (this.head == this.tail) {
            this.head = this.tail = null;
        } else {
            Node<Type> curr = this.head;
            while (curr.getLink() != this.tail) {
                curr = curr.getLink();
            }
            this.tail = curr;
            this.tail.setLink(null);
        }
        this.size--;
        return data;
    }
    public Type removeAt(int index) {
        if (index < 0 || index >= this.size){
            throw new IllegalArgumentException("index out of range");
        }
        if (index == 0){ return removeFirst(); }
        if (index == this.size - 1){ return removeLast(); }
        Node<Type> curr = this.head;
        for (int i = 1; i < index; i++){
            curr = curr.getLink();
        }
        this.size--;
        Type data = curr.getLink().getData();
        curr.setLink(curr.getLink().getLink());
        return data;
    }
    public void truncate(int newSize) {
        if (newSize < 0) {
            throw new IllegalArgumentException("newSize (" + newSize + ") must be >= 0");
        }
        if (this.size < newSize) {
            throw new IllegalArgumentException("size (" + this.size + ") must be >= newSize (" + newSize +  ")");
        }
        if (newSize == 0) {
            this.head = this.tail = null;
            this.size = newSize;
            return;
        }
        Node<Type> curr = this.head;
        for (int i = 1; i < newSize; i++) {
            curr = curr.getLink();
        }//end for
        this.tail = curr;
        this.tail.setLink(null);
        this.size = newSize;
    }
    //end Remove Method(s)

    //start Getter(s) & Setter(s)
    public Type get(int index) {
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index out of range");
        }
        Node<Type> curr = this.head;
        for (int i = 0; i < index; i++){
            curr = curr.getLink();
        }
        return curr.getData();
    }
    public void set(int index, Type data) {
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index out of range");
        }
        Node<Type> curr = this.head;
        for (int i = 0; i < index; i++){
            curr = curr.getLink();
        }
        curr.setData(data);
    }
    //end Getter(s) & Setter(S)

    // Utility methods
    public boolean contains(Type data) {
        Node<Type> curr = this.head;
        while (curr != null) {
            if ((data == null && curr.getData() == null) || (data != null && data.equals(curr.getData()))) { return true; }
            curr = curr.getLink();
        }
        return false;
    }
    public int indexOf(Type data) {
        if (!contains(data)) {
            throw new IllegalArgumentException("data not found");
        }
        Node<Type> curr = this.head;
        for (int i = 0; i < size; i++) {
            if (curr.getData().equals(data)) { return i; }
            curr = curr.getLink();
        }
        return -1;
    }
    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //start Max Size Method(s)
    public int getMaxSize() { return this.maxSize; }
    public void setMaxSize(int maxSize) {
        if  (maxSize < -1 || maxSize == 0){
            throw new IllegalArgumentException("maxSize must be -1 (unlimited) or > 0");
        }
        if (maxSize != -1 && this.size > maxSize) {
            throw new IllegalStateException("List has " + this.size + " elements, cannot shrink maxSize to " + maxSize + ". If intentional please truncate using truncate(int newSize) first.");
        }
        this.maxSize = maxSize;
    }
    //end Max Size Method(s)
}