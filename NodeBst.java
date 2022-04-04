
public class NodeBst<K,T> {
	
		public K k;
		public T data;
		public NodeBst<K,T> left,right;
		
		/** Creates a new instance of BSTNode */
		public NodeBst(K key, T val) {
			k = key;
			data = val;
			left = right = null;
		}
		
		
	}


