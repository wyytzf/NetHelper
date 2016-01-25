package helper.cache;

/**
 * Created by weiyuyang on 16-1-25.
 */
public interface Cache<K,V> {
    public V get(K key);
    public void put(K key,V value);
    public void remove(K key);
}
