
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
private Map<K,Location> bst;
private Locator<K> TL;

    public TreeLocatorMap() {
    bst=new BST<K,Location>();
    TL=new TreeLocator<K>();
    }
	@Override
	public Map<K, Location> getMap() {
		
		return bst;
		
	}

	@Override
	public Locator<K> getLocator() {
		
			return TL;

	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
	    if(k==null || loc==null)
		return new Pair<Boolean,Integer>(false,0);
	    Pair f=bst.insert(k, loc);
	    int count= (int)f.second;
	    boolean found=(boolean)f.first;
	    if(!found) {
	    	return new Pair<Boolean,Integer>(false,count);
	    }
	    else {
	    	
	    	TL.add(k, loc);
	    	
	    	return new Pair<Boolean,Integer>(true,count);
	    }
	 
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		 if(k==null || loc==null)
				return new Pair<Boolean,Integer>(false,0);
	int total=0;
	Pair<Boolean,Integer> t=bst.find(k);
	total=t.second;
	boolean check=t.first;
	if(!check) {
		return new Pair<Boolean,Integer>(false,total);
	}
	else {
		Location x=bst.retrieve();
		TL.remove(k, x);
		TL.add(k, loc);
		
		bst.update(loc);
		
		return new Pair<Boolean,Integer>(true,total);
		
	}
		
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		if(k==null) {
			return new Pair<Location,Integer>(null,0);
		}
		Pair<Boolean,Integer> f=bst.find(k);
		int count=f.second;
		boolean check=f.first;
		if(!check)
			return new Pair<Location,Integer>(null,count);
		else {
			Location s=bst.retrieve();
		return new Pair<Location,Integer>(s,count);
		
		}
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
       if(k==null)
    	   return new Pair<Boolean,Integer>(false,0);
       Pair<Boolean,Integer> s=bst.find(k);
       int counter =s.second;
       boolean check=s.first;
       if(!check)
    	   return new Pair<Boolean,Integer>(false,counter);
       else {
      Location x=bst.retrieve();
      TL.remove(k, x);
      bst.remove(k);
      
      return new Pair<Boolean,Integer>(true,counter);
      
       }
	}

	@Override
	public List<K> getAll() {
		return bst.getAll();
	}
	private void AddList() {
		
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		List<K> empty=new LinkedList<K>();
		if(lowerLeft==null || upperRight==null)
			return new Pair<List<K>,Integer>(new LinkedList<K>(),0);
		Pair<List<Pair<Location, List<K>>>, Integer> s=TL.inRange(lowerLeft, upperRight);
		int count =s.second;
		if(s.first.empty())
			return new Pair<List<K>,Integer>(empty,count);
		List<K> w=new LinkedList<K>();
		
			s.first.findFirst();
		while(!s.first.last()) {	
		
			s.first.retrieve().second.findFirst();
			if(!s.first.retrieve().second.empty())
		while(!s.first.retrieve().second.last()) { 
			
				w.insert(s.first.retrieve().second.retrieve());
		         s.first.retrieve().second.findNext();
		}
		w.insert(s.first.retrieve().second.retrieve());
		s.first.findNext();
		}
			s.first.retrieve().second.findFirst();
			if(!s.first.retrieve().second.empty())
		while(!s.first.retrieve().second.last()) { 
		
				w.insert(s.first.retrieve().second.retrieve());
		         s.first.retrieve().second.findNext();
		}
		w.insert(s.first.retrieve().second.retrieve());
		
		
		return new Pair<List<K>,Integer>(w,count);
		
		
		
		
	}

}
