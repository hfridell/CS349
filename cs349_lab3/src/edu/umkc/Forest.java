package edu.umkc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Forest implements Iterable<Tree>{

    private List<Tree> forest = new ArrayList<>();


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Tree> iterator() {
        return forest.iterator();
    }

    public void growTree(String name){
        forest.add(new Tree(name));
    }
}
