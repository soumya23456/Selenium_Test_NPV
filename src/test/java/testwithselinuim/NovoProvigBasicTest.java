package testwithselinuim;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NovoProvigBasicTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("*****before all********");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("*******After all*******");
	}

	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("*****before each*******");
	}

	@AfterEach
	public void tearDown() throws Exception {
		System.out.println("*******after each********");
	}

	@Test
	public void test() {
		System.out.println("*******test*******");
	}

}
