
//****************************************************************
// Rahul Shrestha
//
// Java Templated Doubly Linked List Class
//****************************************************************

//****************************************************************
//
// DLList Class
//
//****************************************************************
class DLList<T> {

    //****************************************************************
    //
    // DLListNode nested class
    //
    //****************************************************************
    private static class DLListNode<T> {
        //****************************************************************
        // DLList node private data members
        //****************************************************************
        private T data;
        private DLListNode<T> prev;
        private DLListNode<T> next;

        //****************************************************************
        // Constructor
        //****************************************************************
        DLListNode(T item, DLListNode<T> p, DLListNode<T> n) {
            data = item;
            prev = p;
            next = n;
        }

       
    }

    //****************************************************************
    // DLList private data members
    //****************************************************************
    private DLListNode<T> header;
    private DLListNode<T> trailer;
    //****************************************************************
    // !!!IMPORTANT!!!
    // Node current acts as an iterator.
    // We define that a node with a non-null data field is a real node.
    // Node current should always point to a real node if there is
    // at least one real node in the list.
    // Otherwise, node current is null.
    //****************************************************************
    private DLListNode<T> current;
    private int size = 0;

    //****************************************************************
    // Default constructor
    //****************************************************************
    DLList() {
        header = new DLListNode<>(null, null, null);
        trailer = new DLListNode<>(null, header, null);
        header.next=trailer;
        current = null;
    }

    //****************************************************************
    // Construct a list by shallow copying an existing list.
	// Set node current of the new list to the first node if the list 
	// is not empty.
	// Difference between shallow copy and deep copy:
	// www.geeksforgeeks.org/copy-python-deep-copy-shallow-copy
	//****************************************************************
    
    DLList(DLList<T> other) {
        this();
        DLListNode<T> otherNode = other.header.next;

        while (otherNode != other.trailer) {
            this.insertLast(otherNode.data);
            if (otherNode == other.current) {
                this.current = trailer.prev;
            }
            otherNode = otherNode.next;
        }
    }

    //****************************************************************
    // Delete all the nodes in the list.
    void clear() {
        header.next=trailer;
        trailer.prev=header;
        current = null;
        size = 0;
    }

    //****************************************************************
    // Return the number of nodes in the list.
    int size() {
        return size;
    }

    //****************************************************************
    // Return whether the list is empty.
    boolean isEmpty() {
        return size == 0;
    }

    //****************************************************************
    // Return whether node current points to the first node.
    boolean atFirst() {
        
            return current!=null&& current.prev == header;
        }
    

    //****************************************************************
    // Return whether node current points to the last node.
    boolean atLast() {
    	
            return current!= null && current.next== trailer;
        }
    

    //****************************************************************
    // Set node current to the first node, and return true.
    // Or return false if the list is empty.
    boolean first() {
        if (size != 0) {
            current = header.next;
            return true;
        }
        return false;
    }

    //****************************************************************
    // Set node current to the last node, and return true.
    // Or return false if the list is empty.
    boolean last() {
        if (size != 0) {
            current = trailer.prev;
            return true;
        }
        return false;
    }

    //****************************************************************
    // Set node current to its next node, and return true.
    // Or return false if no such node.
    boolean next() {
        // Are we already at the last real node? If so, no next real node.
        if (current != null && current.next != trailer) {
            current = current.next;
            return true;
        }
        return false; // we’re at the last or we have no real node at all
    }
    
    

    //****************************************************************
    // Set node current to its previous node, and return true.
    // Or return false if no such node.
    boolean previous() {
        // Are we already at the first real node? If so, no previous real node.
        if (current != null && current.prev != header) {
            current = current.prev;
            return true;
        }
        return false;
    }
    
    

    //****************************************************************
    // The index of the first real node is 0.
    // Set node current to the node at the specified index, and return
    // true.
    // Or return false if no such node.
    boolean seek(int loc) {
    	// If the position is valid  initialize the traversal from the beginning
        if (loc >= 0 && loc < size) {
            current = header.next;
         // Traverse the linked list 
            for (int i = 0; i < loc; i++) {
                current = current.next;
            }
            return true;
        }
        return false;
    }

    //****************************************************************
    // Return the content of node current.
    // Or return null if the list is empty.
    T dataRead() {
        if (size != 0 && current !=null) {
            return current.data;
        }
        else {
        return null;
        }
    }

    //****************************************************************
    // Rewrite the content of node current if the list is not empty.
    void dataWrite(T item) {
        if (size != 0) {
            current.data=item;
        }
    }

    //****************************************************************
    // Insert a new node to the front of the list, and set node
    // current to the new node.
    void insertFirst(T item) {
        DLListNode<T> newNode = new DLListNode<>(item, header, header.next);
        header.next.prev= newNode;
        header.next= newNode;
        current = newNode;
        size++;
    }

    //****************************************************************
    // Insert a new node to the end of the list, and set node current
    // to the new node.
    void insertLast(T item) {
        DLListNode<T> newNode = new DLListNode<>(item, trailer.prev, trailer);
        trailer.prev.next= newNode;
        trailer.prev=newNode;
        current = newNode;
        size++;
    }

    //****************************************************************
    // Insert a new node before where node current points to if the
    // list is not empty; or insert a new node if the list is empty.
    // Set node current to the new node.
    void insertAtCurrent(T item) {
        if (isEmpty() || current == null) {
            insertFirst(item);
        }else{

        
            DLListNode<T> newNode = new DLListNode<>(item, current.prev, current);
            current.prev.next=newNode;
            current.prev=newNode;
            current = newNode;
            size++;
        
        }
    }

    //****************************************************************
    // Delete the first node in the list if the list is not empty.
    // Set node current to the new first node; or set node current to
    // null if the list becomes empty.
    void deleteFirst() {
        if (!isEmpty()) {
            DLListNode<T> firstNode = header.next;
            header.next=firstNode.next;
            firstNode.next.prev=header;
    
            size--;
            if (size == 0) {
                current = null;  // list is now empty
            } else {
                current = header.next;  // point to new first real node
            }
        }
    }

    //****************************************************************
    // Delete the last node in the list if the list is not empty.
    // Set node current to the new last node; or set node current to
    // null if the list becomes empty.
    void deleteLast() {
        if (!isEmpty()) {
            DLListNode<T> lastNode = trailer.prev;
            trailer.prev=lastNode.prev;
            lastNode.prev.next=trailer;
    
            size--;
            if (size == 0) {
                current = null;
            } else {
                current = trailer.prev;
            }
        }
    }
    

    //****************************************************************
    // Delete the node where node current points to if the list is not
    // empty.
    // Set node current to its next node; or set node current to its
    // previous node if node current was at the end of the list; or
    // set node current to null if the list becomes empty.
    void deleteAtCurrent() {
        if (size != 0 && current != null) {
            DLListNode<T> loc = current;
            current.prev.next=current.next;
            current.next.prev =current.prev;
            
            if(loc.next==trailer){
                current=loc.prev;
            }else{
                current=loc.next;
            }
            size--;
            if (isEmpty()) {
                current = null;
            } else if (current == trailer) {
                current = current.prev;
            }
        }
    }
}
