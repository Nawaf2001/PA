

public class TreeLocator<T> implements Locator<T> {
private NodeLocater<T> root,current;

public TreeLocator() {
	root=current=null;
}
private boolean empty() {
return root==null;
}
public int add(T e, Location loc) {
	if(loc ==null) {
		return 0;
	}
	if(e==null) {
		return 0;
	}
NodeLocater<T> val=new NodeLocater<T>(e,loc);
val.data.insert(e);
if (empty()) {
root=current=val;
return 0;
}
int counter=find1(loc).second;
boolean check=find1(loc).first;
NodeLocater<T> p=current;
int s=p.locat.x;
int z=p.locat.y;
if(!check) {
if(loc.x<s && loc.y<=z) {
current.c1=val;

}
else if(loc.x<=s && loc.y>z) {
current.c2=val;
}else if(loc.x>s && loc.y>=z) {
current.c3=val;
}else if(loc.x>=s &&loc.y<z) {
current.c4=val;
}

}
else {
current.data.insert(e);
}
return counter;
}



private Pair<Boolean,Integer> find(Location loc) {
	if(loc==null) {
		return new Pair<Boolean,Integer>(false,0);
	}
boolean check=false;
int counter=0;
int s=root.locat.x;
int z=root.locat.y;
NodeLocater<T> q=root;
NodeLocater<T> p=root;
while(p!=null) {
q=p;
if(loc.x<s && loc.y<=z) {
counter++;
p=p.c1;
if(p!=null) {
s=p.locat.x;
z=p.locat.y;
}
}
else if(loc.x<=s && loc.y>z) {
counter++;
p=p.c2;
if(p!=null) {
s=p.locat.x;
z=p.locat.y;
}
}else if(loc.x>s && loc.y>=z) {
counter++;
p=p.c3;
if(p!=null) {
s=p.locat.x;
z=p.locat.y;
}
}else if(loc.x>=s && loc.y<z) {
counter++;
p=p.c4;
if(p!=null) {
s=p.locat.x;
z=p.locat.y;
}
}else if (loc.x==s && loc.y==z) {
check=true;

break;
}


}
current=q;
return new Pair<Boolean,Integer>(check,counter);
}
/// counts if the element is found
private Pair<Boolean,Integer> find1(Location loc) {
	if(loc==null)
		return new Pair<Boolean,Integer>(false,0);
boolean check=false;
int counter=0;
int s=root.locat.x;
int z=root.locat.y;
NodeLocater<T> q=root;
NodeLocater<T> p=root;
while(p!=null) {
q=p;
if(loc.x<s && loc.y<=z) {
counter++;
p=p.c1;
if(p!=null) {
s=p.locat.x;
z=p.locat.y;
}
}
else if(loc.x<=s && loc.y>z) {
counter++;
p=p.c2;
if(p!=null) {
s=p.locat.x;
z=p.locat.y;
}
}else if(loc.x>s && loc.y>=z) {
counter++;
p=p.c3;
if(p!=null) {
s=p.locat.x;
z=p.locat.y;
}
}else if(loc.x>=s && loc.y<z) {
counter++;
p=p.c4;
if(p!=null) {
s=p.locat.x;
z=p.locat.y;
}
}else if (loc.x==s && loc.y==z) {
check=true;
counter++;
break;
}


}
current=q;
return new Pair<Boolean,Integer>(check,counter);
}


@Override
public Pair<List<T>, Integer> get(Location loc) {
	List<T> q=new LinkedList<T>();
if(empty())
	return new Pair<List<T>,Integer>(q,0);
Integer s=find1(loc).second;
boolean check=find1(loc).first;
if(!check) {
return new Pair<List<T>,Integer>(q,s);
}else {
List<T> v =current.data;
return new Pair<List<T>,Integer>(v,s);
}

}

@Override
public Pair<Boolean, Integer> remove(T e, Location loc) {
if(e==null) {	
	
	return new Pair<Boolean,Integer>(false,0);
}
if(loc==null) {
	
	return new Pair<Boolean,Integer>(false,0);
}
if(empty()) {
	
return new Pair<Boolean,Integer>(false,0);
}
Integer s=find1(loc).second;
boolean check=find1(loc).first;
NodeLocater<T> p=current;
boolean g=false;
if(!check) {
	
return new Pair<Boolean,Integer>(check,s);

}else  {
	
current.data.findFirst();
while(!current.data.last()) {
	
	if(current.data.retrieve().equals(e)) {
		current.data.remove();
		g=true;
	}else {
		current.data.findNext();
	}
}

if(current.data.retrieve().equals(e)) {
	g=true;

	current.data.remove();
}




}

return new Pair<Boolean,Integer>(g,s);
}


private void traverse(NodeLocater<T> node,List<Pair<Location, List<T>>> x ) {
	if(node==null)
		return;
	Pair<Location,List<T>> s=new Pair<Location,List<T>>(node.locat,node.data);
	x.insert(s);
	traverse(node.c1, x );
	
	traverse(node.c2, x );
	traverse(node.c3, x );
	traverse(node.c4, x );
	
	
}

public List<Pair<Location, List<T>>> getAll() {
	List<Pair<Location, List<T>>> f=new LinkedList<Pair<Location, List<T>>>();
	
	if(empty()) {
		return f;
	}
	
	traverse(root,f);
   return f;
}
private Pair<Location,List<T>> getAllData(NodeLocater<T> node,List<T>l) {
	List<T> s=new LinkedList<T>();
	if(node.data.empty())
		return new Pair<Location,List<T>>(node.locat,s);

node.data.findFirst();
while(!node.data.last()) {
l.insert(node.data.retrieve());
node.data.findNext();
}
l.insert(node.data.retrieve());

return new Pair<Location,List<T>>(node.locat,l);
}



private void rec(NodeLocater<T> node,Location lowerLeft, Location upperRight,Pair<List<Pair<Location, List<T>>>, Integer> g,Location lR,Location uL){
   if(node ==null) {
   return ;
   }
   g.second++;
   if(( node.locat.x >= lowerLeft.x && node.locat.y >= lowerLeft.y) && ( node.locat.x <= upperRight.x && node.locat.y <= upperRight.y)) {
	   
	   if(lowerLeft.x==upperRight.x && lowerLeft.y==upperRight.y ) {
		   List<T> d=new LinkedList<T>();
		   g.first.insert(getAllData(node,d));
		   return;
	   }
   List<T> d=new LinkedList<T>();
   g.first.insert(getAllData(node,d));
   
   }
if(( node.locat.x > lowerLeft.x && node.locat.y > lowerLeft.y) && ( node.locat.x < upperRight.x && node.locat.y < upperRight.y)) {
	
rec(node.c1,lowerLeft,upperRight,g,lR,uL);
rec(node.c2,lowerLeft,upperRight,g,lR,uL);
rec(node.c3,lowerLeft,upperRight,g,lR,uL);
rec(node.c4,lowerLeft,upperRight,g,lR,uL);
}else if((node.locat.x==lR.x) &&(node.locat.y>lR.y && node.locat.y<upperRight.y)) {
rec(node.c1,lowerLeft,upperRight,g,lR,uL);
rec(node.c2,lowerLeft,upperRight,g,lR,uL);
rec(node.c4,lowerLeft,upperRight,g,lR,uL);
}else if((node.locat.x==uL.x)&& (node.locat.y>lowerLeft.y && node.locat.y<uL.y)) {
rec(node.c2,lowerLeft,upperRight,g,lR,uL);
rec(node.c3,lowerLeft,upperRight,g,lR,uL);
rec(node.c4,lowerLeft,upperRight,g,lR,uL);

}else if((node.locat.x>lowerLeft.x && node.locat.x<lR.x)&& (node.locat.y==lowerLeft.y)) {
	
rec(node.c1,lowerLeft,upperRight,g,lR,uL);
rec(node.c2,lowerLeft,upperRight,g,lR,uL);
rec(node.c3,lowerLeft,upperRight,g,lR,uL);
}else if((node.locat.x>uL.x && node.locat.x<upperRight.x)&& (node.locat.y==upperRight.y)) {
rec(node.c1,lowerLeft,upperRight,g,lR,uL);
rec(node.c3,lowerLeft,upperRight,g,lR,uL);
rec(node.c4,lowerLeft,upperRight,g,lR,uL);
///angles
}else if((node.locat.x==uL.x)&&(node.locat.y==uL.y)) {
rec(node.c3,lowerLeft,upperRight,g,lR,uL);
rec(node.c4,lowerLeft,upperRight,g,lR,uL);
}else if((node.locat.x==upperRight.x)&&(node.locat.y==upperRight.y)) {
	
rec(node.c1,lowerLeft,upperRight,g,lR,uL);
rec(node.c4,lowerLeft,upperRight,g,lR,uL);

}else if((node.locat.x==lR.x)&&(node.locat.y==lR.y)) {
rec(node.c1,lowerLeft,upperRight,g,lR,uL);
rec(node.c2,lowerLeft,upperRight,g,lR,uL);
}else if((node.locat.x==lowerLeft.x)&&(node.locat.y==lowerLeft.y)) {
rec(node.c2,lowerLeft,upperRight,g,lR,uL);
rec(node.c3,lowerLeft,upperRight,g,lR,uL);
/// outside four squere
}else if((node.locat.x<=uL.x)&& (node.locat.y>uL.y)) {
rec(node.c4,lowerLeft,upperRight,g,lR,uL);
}else if((node.locat.x>upperRight.x)&& (node.locat.y>=upperRight.y)) {
rec(node.c1,lowerLeft,upperRight,g,lR,uL);
}else if((node.locat.x<lowerLeft.x)&& (node.locat.y<=lowerLeft.y)) {
rec(node.c3,lowerLeft,upperRight,g,lR,uL);
}else if((node.locat.x>=lR.x)&& (node.locat.y<lR.y)) {
rec(node.c2,lowerLeft,upperRight,g,lR,uL);
/// outside four squere
}else if((node.locat.x>uL.x && node.locat.x<=upperRight.x)&& (node.locat.y>upperRight.y)) {
	
rec(node.c1,lowerLeft,upperRight,g,lR,uL);
rec(node.c4,lowerLeft,upperRight,g,lR,uL);
}else if((node.locat.x>=lowerLeft.x && node.locat.x<lR.x)&& (node.locat.y<lowerLeft.y)) {
rec(node.c2,lowerLeft,upperRight,g,lR,uL);
rec(node.c3,lowerLeft,upperRight,g,lR,uL);
}else if((node.locat.x<uL.x)&& (node.locat.y<=uL.y && node.locat.y>lowerLeft.y)) {
rec(node.c3,lowerLeft,upperRight,g,lR,uL);
rec(node.c4,lowerLeft,upperRight,g,lR,uL);
}else if((node.locat.x>lR.x)&& (node.locat.y<upperRight.y && node.locat.y>=lR.y)) {
rec(node.c1,lowerLeft,upperRight,g,lR,uL);
rec(node.c2,lowerLeft,upperRight,g,lR,uL);
}





}


@Override
public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
	Integer count=0;

	List<Pair<Location, List<T>>> s=new LinkedList<Pair<Location, List<T>>>();
	Pair<List<Pair<Location, List<T>>>, Integer> g=new Pair<List<Pair<Location, List<T>>>, Integer>(s,count);
	if(lowerLeft==null || upperRight==null) {
		return g;
	}
if(empty()) {
return g;
}
Location lR=new Location(upperRight.x,lowerLeft.y);
Location uL=new Location(lowerLeft.x,upperRight.y);




rec(root,lowerLeft,upperRight,g,lR,uL);

return g;
}
    
}

