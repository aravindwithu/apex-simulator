package Stages;

import Apex_Simulator.Processor;
import Apex_Simulator.CycleListener;
import Apex_Simulator.ProcessListener;
import Utility.Constants;
import Utility.Instruction;

public class MultiplicationFU implements ProcessListener{
	
	public Processor processor;
	public Instruction instruction;
	public CycleListener pc;
	public int mulCount;
	CycleListener result;
	/**
	 * Constructor for MultiplicationFU stage initializes PC(instruction Address), result(like a latch which has results of the stage).
	 * @param processor a Processor object.
	 */
	public MultiplicationFU(Processor processor) {
		pc = new CycleListener(processor);
		result = new CycleListener(processor);
		this.processor = processor;
		processor.processListeners.add(this);
	}
	
	/**
	 * MultiplicationFU process method performs multiplication on source operands and write the result in a temp result
	 * MultiplicationFU takes 3 cycles to perform the operation
	 */
	
	public void process() {
		try{
			if(processor.multiplicationFU.mulCount == 0){				
				Instruction tempIns = null;
				int countIQ = Constants.IQ_COUNT;
				int IQInsAdd = 0;
		
				for(int i=0; i < countIQ; i++){
					if(processor.iQ.readIQEntry(i).opCode != null){
							if( !processor.iQ.readIQEntry(i).inExecution
								&& !processor.iQ.readIQEntry(i).src1Stall
								&& !processor.iQ.readIQEntry(i).src2Stall)
							{
								tempIns = processor.iQ.readIQEntry(i);	
								IQInsAdd = i;
							    break;
							}else{
								Processor.noIssueCount++;
							}
						}
					else{
						break;
					}
					
				}
				
				if(tempIns == null){	
					instruction = null;
					return;
				}
						
				if(tempIns != null && tempIns.opCode.ordinal() == 2)
				{
					processor.iQ.readIQEntry(IQInsAdd).inExecution = true;
					instruction = tempIns;
					processor.iQ.removeIQEntry(IQInsAdd);		
				}
				else{
					instruction = null;
					return;
				}	
				
				if(instruction != null){				

					pc.write(instruction.insPc);
						
					
				}			
			}
			if(processor.multiplicationFU.mulCount == 2){
				result.write(instruction.src1*instruction.src2);
				instruction.destVal = instruction.src1*instruction.src2;
				processor.multiplicationFU.mulCount++;
			}
			else{
				processor.multiplicationFU.mulCount++;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	/**
	 * clearStage method clears the BranchFU stage.
	 */
	public void clearStage() {
		pc.write(0);
		result.write(0);
		instruction = null;
	}
	
	/**
	 * pcValue method returns the pc Value(instruction address) of the BranchFU stage.
	 * @return long value of the pc Value(instruction address)
	 */
	public Long pcValue(){
		return pc.read();
	}
	
	/**
	 * toString method returns the instruction currently in BranchFU as string if instruction is not null or returns the IDLE constants.
	 * @return String of the instruction or IDLE constants
	 */
	@Override
	public String toString() {
		if(instruction == null){
			return Constants.OpCode.IDLE.name();
		}
		else{
			return instruction.toString();
		}
	}

}
