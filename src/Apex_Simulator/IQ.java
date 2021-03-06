package Apex_Simulator;

import Utility.Constants;
import Utility.Instruction;

public class IQ {
	
	private Instruction []IQEntry  = new Instruction[Constants.IQ_COUNT];
	private int IQIndex = 0;
	public Processor processor;

	/**
	 * Constructor for IQ initializes the IQEntry.
	 */
	public IQ() {
		//reset all IQEntrys	
		for(int i=0; i < Constants.IQ_COUNT; ++i){
			IQEntry[i] = new Instruction();			
		}	
		IQIndex = 0;
	}
	//Write entry into IQ
	public boolean writeIQEntry(Instruction data){
		if(IQIndex >= 0 && IQIndex < Constants.REG_COUNT){		
			IQEntry[IQIndex] = data;
			IQIndex++;
			return true;}
		else{
			  return false;}
		}	
	//Read an entry from IQ
	public Instruction readIQEntry(int index) throws Exception{
		if(index >= 0 && index < Constants.IQ_COUNT){
			if(this.IQEntry[index] != null){
			return this.IQEntry[index];}
			else{ return null;
			}
		}
		else{
			throw new Exception("Illegal IQ Index : "+index);}
	}
	//Remove an entry from IQ
	public void removeIQEntry(int index) throws Exception{
		if(index >= 0 && index < Constants.IQ_COUNT){
			if(this.IQEntry[index] != null){
				int incIndex = Constants.IQ_COUNT - index -1;
				for(int i = 1; i < incIndex; i++){
				IQEntry[index] = IQEntry[index+i];
				if(IQEntry[index+i].opCode == null){
				  break;}
				index++;
				}				
				IQEntry[Constants.IQ_COUNT - 1] = new Instruction();				
				IQIndex--;
			}
		}
		else{
			throw new Exception("Illegal IQ Index : "+index);}		
	}
	
	//Flush corresponding IQ Entry
	public void flushIQEntry(int index) throws Exception{
		if(index >= 0 && index < Constants.IQ_COUNT){
			for(int i = index; index < Constants.IQ_COUNT; i++){
				processor.iQ.removeIQEntry(i);
			}
		}
		else{
			throw new Exception("Illegal IQ Index : "+index);
		}
	}
	

		
	
}
