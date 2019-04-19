import org.junit.jupiter.api.Test;

import com.saptris.erp.DataType;

public class TestDataType {
	@Test
	public void runTest() {
		System.out.println(DataType.validateEmail("abc@def.co.in"));
		try {
			System.out.println(DataType.validateEmail("a@b."));			
		}catch(IllegalArgumentException ex) {System.out.println("Excpetion was thrown: "+ex.getMessage());}
		try {
			System.out.println(DataType.validateEmail("@b.c"));			
		}catch(IllegalArgumentException ex) {System.out.println("Excpetion was thrown: "+ex.getMessage());}
		try {
			System.out.println(DataType.validateEmail("a@.c"));			
		}catch(IllegalArgumentException ex) {System.out.println("Excpetion was thrown: "+ex.getMessage());}
		try {
			System.out.println(DataType.validateEmail("ab@c@def.co.in"));			
		}catch(IllegalArgumentException ex) {System.out.println("Excpetion was thrown: "+ex.getMessage());}
		try {
			System.out.println(DataType.validateEmail("abc@def.co.in."));			
		}catch(IllegalArgumentException ex) {System.out.println("Excpetion was thrown: "+ex.getMessage());}
		System.out.println(DataType.validatePhone("9876543210"));
		System.out.println(DataType.validateGender("male"));
		System.out.println(DataType.validateGender("female"));
		try {
			System.out.println(DataType.validateGender("fema"));			
		}catch(IllegalArgumentException ex) {System.out.println("Excpetion was thrown: "+ex.getMessage());}
		System.out.println(DataType.validateIfsc("SBIN0001234"));
		System.out.println(DataType.validateIfsc("barb0dilsha"));
	}
}
