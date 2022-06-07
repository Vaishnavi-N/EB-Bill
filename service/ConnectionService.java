package com.ksr.eb.service;
import com.ksr.eb.entity.Commercial;
import com.ksr.eb.entity.Domestic;
import com.ksr.eb.exception.InvalidConnectionException;
import com.ksr.eb.exception.InvalidReadingException;
public class ConnectionService {
 public boolean validate(int currentReading, int previousReading, String type) throws InvalidReadingException, InvalidConnectionException
 {
	if(currentReading < previousReading || currentReading<0 || previousReading<0) {
	throw new InvalidReadingException();
	}
	if(type.equals("Domestic")||type.equals("Commercial")) {}
	else {
		throw new InvalidConnectionException();
	}
		return true; 
 }
 public float calculateBillAmt(int currentReading, int previousReading, String type) {
	 boolean b=true;
	 float billAmount=0;
	 try {
		 b=validate(currentReading, previousReading, type);
	      if(b==true) {
			if(type.equals("Domestic")){
				float []slabs=new float[3];
				 slabs[0]=2.3f;
				 slabs[1]=4.2f;
				 slabs[2]=5.5f;
				 Domestic d= new Domestic(currentReading,previousReading,slabs);
				 billAmount=d.computeBill();
			}
			else if(type.equals("Commercial")) {
				float []slabs=new float[3];
			    slabs[0]=5.2f;
				slabs[1]=6.8f;
				slabs[2]=8.3f;
				Commercial c= new Commercial(currentReading,previousReading,slabs);
				billAmount=c.computeBill();
			}
		 }
	 }
	 catch(InvalidReadingException e){
		 return -1;
	 }
	 catch(InvalidConnectionException e){
		 return -2;
	 }
	 return billAmount;
 }
 public String generateBill(int currentReading,int previousReading,String type) {
	 String message=" ";
	 float billAmount=calculateBillAmt(currentReading,previousReading,type);
	 if(billAmount==-1) {
     message="Incorrect Reading";
	 }
	 else if(billAmount==-2) {
     message="Invalid ConnectionType";
	 }
	 else {
	 message="Amount to be paid:"+ billAmount;
	 }
	 return message;
 }
}
