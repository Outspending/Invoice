package me.outspending.invoice.timespan;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ExpirableList<V> {

    private final Map<V, Entry<V>> data = new HashMap<>();

    public void add(V value, long seconds) {
        long expirationTime = System.currentTimeMillis() + (seconds * 1000);
        data.put(value, new Entry<>(value, expirationTime));
    }

    public V get(V value) {
        Entry<V> entry = data.get(value);
        if (entry != null && System.currentTimeMillis() < entry.expirationTime) {
            return entry.value;
        } else {
            data.remove(value);
            return null;
        }
    }

    public boolean containsKey(V value) {
        Entry<V> entry = data.get(value);
        if (entry != null && System.currentTimeMillis() < entry.expirationTime) {
            return true;
        } else {
            data.remove(value);
            return false;
        }
    }

    public void remove(V value) {
        data.remove(value);
    }

    public void clear() {
        data.clear();
    }

    public void forEach(@NotNull Consumer<? super V> action) {
        Objects.requireNonNull(action);
        for (Map.Entry<V, Entry<V>> entry : data.entrySet()) {
            V v;
            try {
                v = entry.getKey();
            } catch (IllegalStateException ise) {
                throw new ConcurrentModificationException(ise);
            }
            action.accept(v);
        }
    }

    public List<V> getExpired() {
        List<V> expired = new ArrayList<>();
        if (data.isEmpty())
            return expired;

        data.forEach((key, value) -> {
            if (System.currentTimeMillis() > value.expirationTime)
                expired.add(key);
        });
        return expired;
    }

    public Entry<V> getLastExpirable() {
        if (data.isEmpty())
            return null;

        Entry<V> last = null;
        for (Entry<V> entry : data.values()) {
            if (last == null || entry.expirationTime > last.expirationTime)
                last = entry;
        }
        return last;
    }

    public Entry<V> getFirstExpirable() {
        if (data.isEmpty())
            return null;

        Entry<V> first = null;
        for (Entry<V> entry : data.values()) {
            if (first == null || entry.expirationTime < first.expirationTime)
                first = entry;
        }
        return first;
    }

    public Entry<V> getEntry(V value) {
        return data.get(value);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private static class Entry<V> {
        private final V value;
        private final long expirationTime;

        private Entry(V value, long expirationTime) {
            this.value = value;
            this.expirationTime = expirationTime;
        }
    }
}
