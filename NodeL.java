
public class NodeL <T> {
	public T data ;
	public NodeL<T> next;

	public NodeL() {
		data=null;
		next=null;
	}
	public NodeL(T val) {
		data=val;
		next=null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public NodeL<T> getNext() {
		return next;
	}

	public void setNext(NodeL<T> next) {
		this.next = next;
	}
}
