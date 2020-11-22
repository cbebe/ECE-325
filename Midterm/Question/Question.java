import java.util.*;

class EvenIterator<T> implements Iterator<T> {
    private Even<T> list;
    private int current;

    public EvenIterator(Even<T> list) {
        this.list = list;
        current = 1;
    }

    public boolean hasNext() {
        return current < list.list.size();
    }

    public T next() {
        if (!this.hasNext())
            throw new NoSuchElementException();
        T next = list.list.get(current);
        current += 2;
        return next;
    }
}

class Even<T> implements Iterable<T> {
    // Add attributes based on the rq
    Iterator<T> itr;
    List<T> list;

    public <C extends Collection<T>> Even(C collection) {
        // Add the code required to construct the adapter
        itr = collection.iterator();
        list = new ArrayList<T>(collection);

    }

    // Add the required methods to implement the expected interface(s)
    public Iterator<T> iterator() {
        return new EvenIterator<T>(this);
    }
}

public class Question {
    public static void main(String[] args) {
        List<Character> list = new ArrayList<>();
        String cad = "0H1e3l4l5o6 7W8o9r0l1d2!";
        for (Character chr : cad.toCharArray())
            list.add(chr);

        System.out.println(list);

        for (char v : new Even<>(list.subList(0, 12)))
            System.out.print(v);
        System.out.println();
        for (char v : new Even<>(list.subList(12, list.size())))
            System.out.print(v);
    }
}