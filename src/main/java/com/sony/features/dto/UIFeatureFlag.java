package com.sony.features.dto;

public class UIFeatureFlag {
	String region;
	boolean isActive;
	int positionFromRight;
	
	public UIFeatureFlag() {
		
	}
	
	public UIFeatureFlag(String region, boolean isActive, int positionFromRight) {
		super();
		this.region = region;
		this.isActive = isActive;
		this.positionFromRight = positionFromRight;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Feature [region=" + region + ", isActive=" + isActive + ", positionFromRight=" + positionFromRight + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// If the object is compared with itself then return true   
        if (obj == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of UIFeatureFlag or not 
          "null instanceof [type]" also returns false */
        if (!(obj instanceof UIFeatureFlag)) { 
            return false; 
        } 
          
        // typecast o to UIFeatureFlag so that we can compare data members  
        UIFeatureFlag f = (UIFeatureFlag) obj; 
          
        // Compare the data members and return accordingly  
        return region.equals(f.region)
                && Boolean.compare(isActive, f.isActive) == 0
                && Integer.compare(positionFromRight, f.positionFromRight) == 0; 
	}	
	
}
