package api;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class DoctorTest {

	@Rule
	public TestName name = new TestName();

	@Before
	public void setUp() throws Exception {
		System.out.println("Test starts: " + name.getMethodName());

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test finished: " + name.getMethodName());
	}

	@Test
	public void testBuilder() {
		Doctor d = new Doctor.Builder("x1234", "Tom Smith", "General Practice", "Los Angeles").score(4.0).build();
		assertEquals("x1234", d.getId());
		Double s = 4.0;
		assertEquals(s, d.getScore());
	}

}
