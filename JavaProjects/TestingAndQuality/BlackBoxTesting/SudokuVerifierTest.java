import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuVerifierTest {

//implement tests to test Sudokuverifier with boundary values.  Use templates from Task 1 to derive and document test cases.
	
// A correct Sudoku string: 417369825632158947958724316825437169791586432346912758289643571573291684164875293
// An incorrect Sudoku string: 123456789912345678891234567789123456678912345567891234456789123345678912234567891
String c = "417369825632158947958724316825437169791586432346912758289643571573291684164875293";
String i = "123456789912345678891234567789123456678912345567891234456789123345678912234567891";
SudokuVerifier v = new SudokuVerifier();

	@Test
	public void testCorrectString() {
		int a = v.verify(c);
		assertEquals("correct string", a, 0);
	}

	@Test
	public void testIncorrectString() {
		int a = v.verify(i);
		assertEquals("incorrect string", a, -2);
		
	}
	
	@Test
	public void testDigitCountTooMany() {
		String b = "41736982563215894795872431682543716979158643234691275828964357157329168416487529329";
			int a = v.verify(b);
		assertEquals("Too many digits in the string", a, -1);
	}
	
	@Test
	public void testDigitCountTooTiny() {
		String b = "4173698256321589479587243168254371697915864323469127582896435715732916841648";
		int a = v.verify(b);
		assertEquals("Not enough digits in the string", a ,-1);
	}
	
	@Test
	public void testUniCodeDigit() {
		String b = "@417369825632158947958724316825437169791586432346912758289643571573291684164875293";
			int a = v.verify(b);
		assertEquals("Unicode digit in the string", a, -1);
	}
	
}
