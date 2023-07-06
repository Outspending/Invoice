package me.outspending.invoice.timespan;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ExpirableList<V> {

    private final List<Entry<V>> data = new ArrayList<>();

    public void put(V value, long seconds) {
        long expirationTime = System.currentTimeMillis() + (seconds * 1000);
        data.add(new Entry<>(value, expirationTime));
    }

    public V get(V value) {
        Entry<V> entry = data.get(data.indexOf(value));
        if (entry != null && System.currentTimeMillis() < entry.expirationTime) {
            return entry.value;
        } else {
            data.remove(entry);
            return null;
        }
    }

    public boolean containsKey(V value) {
        Entry<V> entry = data.get(data.indexOf(value));
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
        for (Entry<V> entry : data) {
            V v;
            try {
                v = entry.value;
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

        data.forEach((value) -> {
            if (System.currentTimeMillis() > value.expirationTime)
                expired.add(value.value);
        });
        return expired;
    }

    public Entry<V> getLastExpirable() {
        if (data.isEmpty())
            return null;

        Entry<V> last = null;
        for (Entry<V> entry : data) {
            if (last == null || entry.expirationTime > last.expirationTime)
                last = entry;
        }
        return last;
    }

    public Entry<V> getFirstExpirable() {
        if (data.isEmpty())
            return null;

        Entry<V> first = null;
        for (Entry<V> entry : data) {
            if (first == null || entry.expirationTime < first.expirationTime)
                first = entry;
        }
        return first;
    }

    public Entry<V> getEntry(V value) {
        return data.get(data.indexOf(value));
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
