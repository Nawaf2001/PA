public interface Map<K extends Comparable<K>, T> {

	
	boolean empty();

	
	boolean full();

     T retrieve();

	 void update(T e);

	
	 Pair<Boolean, Integer> find(K key);

	
	Pair<Boolean, Integer> insert(K key, T data);

	
	Pair<Boolean, Integer> remove(K key);

	
	List<K> getAll();
}
