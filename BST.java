
public class BST<K extends Comparable<K>, T> implements Map<K, T> {
	private NodeBst<K,T> root,current;
	
	public BST() {
		root=current=null;
	}

	@Override
	public boolean empty() {
		return root==null;
	}
	

	@Override
	public boolean full() {
		
		return false;
	}

	@Override
	public T retrieve() {
		if(empty())
			return null;
		return current.data;
	
	}

	@Override
	public void update(T e) {
		if(empty())
			return ;
		current.data=e;

	}
	
	private NodeBst<K,T> ubdate (NodeBst<K,T> f,K key,T data) {
		if(empty())
			return null;
		f.k=key;
		f.data=data;
		return f;
		}

		//no changing in the current unless found
		public Pair<Boolean, Integer> find(K key) {
			if(key==null)
				return new Pair<Boolean,Integer>(false,0);
		Integer counter=0;
		Boolean check=false;
		Pair<Boolean,Integer> x=new Pair<Boolean,Integer>(check,counter);
		if(root==null)
		return x;
		NodeBst<K,T> q=root;
		NodeBst<K,T> p=root;

		while(p!=null) {
		q=p;
		if(p.k.compareTo(key)==0) {
		current=p;
		check=true;
		counter++;
		break;
		}else if(p.k.compareTo(key)>0) {
		p=p.left;
		counter++;
		}else {
		p=p.right;
		counter++;
		}
		}
		 Pair<Boolean,Integer> f=new Pair<Boolean,Integer>(check,counter);
		return f;
		}
		     // change the current to make it easy to insert aftr
		private Pair<Boolean, Integer> findAndChange(K key) {
			
		Integer counter=0;
		Boolean check=false;
		Pair<Boolean,Integer> x=new Pair<Boolean,Integer>(check,counter);
		if(root==null)
		return x;
		NodeBst<K,T> q=root;
		NodeBst<K,T> p=root;

		while(p!=null) {
		q=p;
		if(p.k.compareTo(key)==0) {
		check=true;
		counter++;
        return new Pair<Boolean,Integer>(check,counter);
		}else if(p.k.compareTo(key)>0) {
		p=p.left;
		counter++;
		}else {
		p=p.right;
		counter++;
		}
		}
		Pair<Boolean,Integer> f=new Pair<Boolean,Integer>(check,counter);
		 current=q;
		return f;
		}

		public Pair<Boolean, Integer> insert(K key, T data) {
			if(key==null || data==null) {
				return new Pair<Boolean,Integer>(false,0);
			}
		Boolean check=false;
		Integer count=0;
		Pair<Boolean,Integer> v=new Pair<Boolean,Integer>(check,0);
		if(empty()) {
		root=current=new NodeBst<K,T>(key,data);
		Pair<Boolean,Integer> s=new Pair<Boolean,Integer>(true,0);
		check=true;
		return s ;
		}
		
		Pair<Boolean,Integer> x=findAndChange(key);
		count=x.second;
		if(x.first) {
		Pair<Boolean,Integer> q=new Pair<Boolean,Integer>(false,count);
		return q;
		}

		NodeBst<K,T>q=current;
		if(q.k.compareTo(key)>0) {
		q.left=new NodeBst<K,T>(key,data);
		check=true;
		current=q.left;
		Pair<Boolean,Integer> t=new Pair<Boolean,Integer>(true,count);
		return t;
		}
		else {
		q.right=new NodeBst<K,T>(key,data);
		check=true;
		current=q.right;
		Pair<Boolean,Integer> p=new Pair<Boolean,Integer>(true,count);
		return p;
		}


		}
		    //find value before the current intendent to be deleted
		private Pair<Boolean, Integer> findAndChange1(K key) {
		Integer counter=0;
		Boolean check=false;
		Pair<Boolean,Integer> x=new Pair<Boolean,Integer>(check,counter);
		if(root==null)
		return x;
		NodeBst<K,T> q=root;
		NodeBst<K,T> p=root;

		while(p!=null) {
		if(p.k.compareTo(key)==0) {
		check=true;
		counter++;
		current=q;
		break;
		}
		q=p;  //last number before the element to be deleted
		if(p.k.compareTo(key)>0) {
		p=p.left;
		counter++;
		}else {
		p=p.right;
		counter++;
		}
		}
		Pair<Boolean,Integer> f=new Pair<Boolean,Integer>(check,counter);
		 current=q;
		return f;
		}


private int checkIfRoot(K key) {
if(root.k.compareTo(key)==0)
return 1;
return 0;
}




public Pair<Boolean, Integer> remove(K key) {
if(key==null) {
return new Pair<Boolean,Integer>(false,0);
}
Integer counter=0;
Boolean check=false;
Pair<Boolean,Integer> s=new Pair<Boolean,Integer>(check,counter);
if(root==null) {
return s;
}
Pair<Boolean,Integer> f=findAndChange1(key);
counter =f.second;
int CheckRoot=checkIfRoot(key);
if(!f.first) {
Pair<Boolean,Integer> u=new Pair<Boolean,Integer>(check,counter);
return u;
}
NodeBst<K,T> q=current;
NodeBst<K,T> p=current;

if(q==root && CheckRoot==1) {
if(q.right!=null) {
p=p.right;
if(p.left==null) {
current.k=p.k;
current.data=p.data;
if(p.right!=null)
current.right=p.right;
else
current.right=null;
return new Pair<Boolean,Integer>(true,counter);
}else {
while(p!=null) {
if(p.left!=null) {
q=p;
}
if(p.left==null)
break;
p=p.left;
}
current.k=p.k;
current.data=p.data;
if(p.right!=null)
q.left=p.right;
else
q.left=null;
return new Pair<Boolean,Integer>(true,counter);

}

}else if(q.left!=null) {
p=p.left;
if(p.right==null) {
current.k=p.k;
current.data=p.data;
if(p.left!=null)
current.left=p.left;
else
current.left=null;
return new Pair<Boolean,Integer>(true,counter);
}else {
q=p;
p=p.right;
while(p!=null) {
if(p.right!=null) {
q=p;
}
if(p.right==null)
break;
p=p.right;
}
current.k=p.k;
current.data=p.data;
if(p.left!=null)
q.right=p.left;
else
q.right=null;
return new Pair<Boolean,Integer>(true,counter);
}
}
}

//start
int co=0;

if(q.k.compareTo(key)>0){
p=p.left;
q=q.left;
co=2;
}else {
p=p.right;
q=q.right;
co=1;
}

if(p.right!=null && p.left != null) {
p=p.right;

if(p.left==null) {
if(co==2) {
current.left.k=p.k;
current.left.data=p.data;

if(p.right!=null)
q.right=p.right;
else
q.right=null;

current=root;
return new Pair<Boolean,Integer>(true,counter);
}else if(co==1) {

current.right.k=p.k;
current.right.data=p.data;
if(p.right!=null)
q.right=p.right;
else
q.right=null;

current=root;
return new Pair<Boolean,Integer>(true,counter);
}
}
while(p!=null) {
if(p.left!=null) {
q=p;
}
if(p.left==null)
break;
p=p.left;


}
if(co==2) {
current.left.k=p.k;
current.left.data=p.data;
if(p.right!=null)
q.left=p.right;
else
q.left=null;

}else if(co==1) {
current.right.k=p.k;
current.right.data=p.data;
if(p.right!=null)
q.left=p.right;
else
q.left=null;
}
current=root;

Pair<Boolean,Integer> i=new Pair<Boolean,Integer>(true,counter);
return i;


}// two
else if(p.right!=null && p.left ==null){
p=p.right;
if(co==2) {
current.left=p;
q=null;
current=root;
return new Pair<Boolean,Integer>(true,counter);
}else if(co==1) {
current.right=p;
q=null;
return new Pair<Boolean,Integer>(true,counter);
}
}
//three
else if(p.right==null && p.left !=null){
p=p.left;
if(co==2) {
current.left=p;
q=null;
current=root;
return new Pair<Boolean,Integer>(true,counter);
}else if(co==1) {
current.right=p;
q=null;
current=root;
return new Pair<Boolean,Integer>(true,counter);
}

}else {
if(co==2) {
current.left=null;
q=null;
p=null;
current=root;
return new Pair<Boolean,Integer>(true,counter);
}else if(co==1) {
current.right=null;
q=null;
p=null;
current=root;
return new Pair<Boolean,Integer>(true,counter);
}
}
current=root;
return new Pair<Boolean,Integer>(true,counter);



}
		
		
        private void AscendingOrder(NodeBst<K,T> j,List<K> a){
        
        	
        	
        	if(j==null) {
        		return ;
        	}
        	
        	 AscendingOrder(j.left,a);
             a.insert(j.k);
             AscendingOrder(j.right,a);
             

        }
		@Override
		public List<K> getAll() {
			if(empty())
				return null;
			List<K> n=new LinkedList<K>();
			AscendingOrder(root,n);
			
			return n;
		}
	
}