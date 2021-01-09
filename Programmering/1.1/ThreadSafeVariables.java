import java.util.concurrent.atomic.AtomicInteger;

// Thread safe class and instance variable acces

// Time spent: 20 minutes

public class ThreadSafeVariables {

    //Thread safe class variable
    public static AtomicInteger incrementableAtomic = new AtomicInteger(0);

    public void incrementAtomic() {
        incrementableAtomic.incrementAndGet();
    }

    public int getAtomic() {
        return incrementableAtomic.get();
    }

    // Thread safe instance variable
    private int incrementable = 0;

    public synchronized void incrementInt() {
        incrementable++;
    }

    public int getInt() {
        return incrementable;
    }

}
