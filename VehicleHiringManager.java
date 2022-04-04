public class VehicleHiringManager {
	LocatorMap<String> LM ;
	public VehicleHiringManager() {
		LM =new TreeLocatorMap<String>();
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return LM;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		LM=locatorMap;
		
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		return LM.add(id, loc).first;
		
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		return LM.move(id, loc).first;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		return LM.remove(id).first;	
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		return LM.getLoc(id).first;
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		List<String> empty=new LinkedList<String>();
		
       if(loc==null || r<0 )
    	   return empty;
		
        int LowerLeft=loc.x-r;
        int LowerLeftY=loc.y-r;
        int upperRightX=loc.x+r;
        int upperRight=loc.y+r;
        Location lowerLeft=new Location(LowerLeft,LowerLeftY);
        Location UpperRight=new Location(upperRightX,upperRight);
        Pair<List<String>,Integer> s=LM.getInRange(lowerLeft, UpperRight);
        return s.first;
        
}
}
