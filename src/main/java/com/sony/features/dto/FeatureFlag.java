package com.sony.features.dto;

public class FeatureFlag {

	private String name;
	private int value;
	
	public FeatureFlag() {
		
	}

	public FeatureFlag(String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "FeatureFlag [name=" + name + ", value=" + value + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// If the object is compared with itself then return true   
        if (obj == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of FeatureFlag or not 
          "null instanceof [type]" also returns false */
        if (!(obj instanceof FeatureFlag)) { 
            return false; 
        } 
          
        // typecast o to FeatureFlag so that we can compare data members  
        FeatureFlag f = (FeatureFlag) obj; 
          
        // Compare the data members and return accordingly  
        return name.equals(f.name)
                && Integer.compare(value, f.value) == 0; 
	}
}
