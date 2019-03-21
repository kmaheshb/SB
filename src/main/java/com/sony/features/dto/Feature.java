package com.sony.features.dto;

public class Feature {
	String name;
	boolean isActive;
	
	public Feature() {
		
	}
	
	public Feature(String name, boolean isActive) {
		super();
		this.name = name;
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Feature [name=" + name + ", isActive=" + isActive + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// If the object is compared with itself then return true   
        if (obj == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Feature)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Feature f = (Feature) obj; 
          
        // Compare the data members and return accordingly  
        return name.equals(f.name)
                && Boolean.compare(isActive, f.isActive) == 0; 
	}	
	
}
