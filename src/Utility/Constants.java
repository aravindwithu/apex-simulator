package Utility;

public class Constants {
	
	/**
	 * OpCode enum contains operation codes of different instructions.
	 */
	public enum OpCode{
		ADD, SUB, MUL, MOVC, MOV, AND, OR, EXOR, LOAD, STORE, BZ, BNZ, JUMP, BAL, HALT, IDLE
	}
	
	/**
	 * Stage enum contains Stage constants of different instructions.
	 */
	public enum Stage{
		FETCH, DECODE, DISPATCH, ALU1,  ALU2, BRANCHFU, MULTIPLICATIONFU, LSFU1, LSFU2, WRITEBACK, ROBCOMMIT, EMPTY
	}
	
	public static final String REG_PREFIX = "R";
	public static final String LITERAL_PREFIX = "#";
	public static final int MEM_SIZE = 10000;
	public static final int CACHE_SIZE = 10;
	public static int REG_COUNT = 33;	//32 Reg + 1 Reg X
	public static final int RAT_COUNT = 16;	
	public static final int IQ_COUNT = 12;	
	public static final int ROB_COUNT = 40;	
	public static final long START_ADDRESS = 4000;	
	public static final String INITIALIZE = "Initialize";
	public static final String SIMULATE = "Simulate";
	public static final String DISPLAY = "Display";
	public static final String SEPARATOR1 = " ";
	public static final String SEPARATOR2 = ", ";
}


