// The basic entry stored in ProbingHashTable

class HashEntry {
    Hashable element;   // the element
    boolean  isActive;  // false is deleted
    int counter;

    public HashEntry( Hashable e ) {
        this( e, true );
    }

    public HashEntry( Hashable e, boolean i ) {
        element   = e;
        isActive  = i;
        counter = 0;
    }
}
