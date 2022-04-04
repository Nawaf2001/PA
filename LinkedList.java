
public class LinkedList<T> implements List<T> {
private NodeL<T> head;
private NodeL<T> current;

	

	@Override
	public boolean empty() {
		return head==null;
	}

	@Override
	public boolean full() {
		
		return false;
	}

	@Override
	public void findFirst() {
		if (!empty())
			current=head;
		
	}

	@Override
	public void findNext() {
		if(!empty())
			current=current.next;

	}

	@Override
	public boolean last() {
        if(current==null)
        	return false;
		return current.next==null;
	}

	@Override
	public T retrieve() {
		
		if(!empty())
			return current.data;
			return null;
	}

	@Override
	public void update(T e) {
		if (!empty())
			current.data=e;

	}

	@Override
	public void insert(T e) {
		if(e==null)
			return;
		NodeL<T> tmp;
		if (empty()) {
			current = head = new NodeL<T> (e);
		}
		else {
			tmp = current.next;
			current.next = new NodeL<T> (e);
			current = current.next;
			current.next = tmp;
		}
	}

		

	

	@Override
	public void remove() {
		if(!empty()) {
			if(current==head)
				head=head.next;
			else {
				NodeL<T> tmp=head;
				while(tmp.next != current)
					tmp=tmp.next;
				tmp.next=current.next;
			}
			if(current.next == null)
				current=head;
			else
				current=current.next;
					}
		else 
			return;

	}
	

}
