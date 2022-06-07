package com.ksr.eb.entity;

public class Commercial extends Connection{

	public Commercial(int currentReading, int previousReading, float[] slabs) {
		super(currentReading, previousReading, slabs);
	}
    public float computeBill() {
    	int units=currentReading-previousReading;
    	float billAmount=0;
    	float electricityDuty=0;
    	if(units<=50) {
    		billAmount=units*slabs[0];
    	}
    	else if(units>50 && units<=100) {
    		billAmount=50*slabs[0]+(units-50)*slabs[1];
    	}
    	else {
    		billAmount=50*slabs[0]+50*slabs[1]+(units-100)*slabs[2];
    	}
    	
    	if(billAmount>=10000) {
    		electricityDuty= billAmount*0.09f;
    	}
    	else if(billAmount>=5000) {
    		electricityDuty= billAmount*0.06f;
    	}
    	else if(billAmount<5000) {
    		electricityDuty= billAmount*0.02f;
    	}
    	billAmount=billAmount+electricityDuty;
    	return billAmount;
    	
    			
    }
}
