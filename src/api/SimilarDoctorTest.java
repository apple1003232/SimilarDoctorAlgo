package api;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class SimilarDoctorTest {
	@Rule
	public TestName name = new TestName();
	private List<Doctor> list = new ArrayList<>();
	private Doctor d, d0, d1, d2, d3, d4, d5, d6;
	private SimilarDoctor sd = new SimilarDoctor();

	@Before
	public void setUp() throws Exception {
		d = new Doctor.Builder("x1", "Tom", "General Practice", "Los Angeles").score(4.0).build();
		d0 = new Doctor.Builder("x2", "Tom", "Acupuncture", "Santa Clara").score(4.0).build();

		d1 = new Doctor.Builder("x3", "Tom", "Acupuncture", "Santa Clara").score(4.5).build();
		d2 = new Doctor.Builder("x4", "Carrie", "Acupuncture", "Los Angeles").score(3.7).build();
		d3 = new Doctor.Builder("d5", "Susan", "Acupuncture", "Santa Clara").score(4.7).build();
		d4 = new Doctor.Builder("c6", "Mary", "General Practice", "Los Angeles").score(4.9).build();
		d5 = new Doctor.Builder("b7", "Jane", "General Practice", "Santa Clara").score(4.6).build();
		d6 = new Doctor.Builder("g8", "Sherry", "General Practice", "Los Angeles").score(3.9).build();

		list.add(d);
		list.add(d0);
		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.add(d4);
		list.add(d5);
		list.add(d6);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test finished: " + name.getMethodName());
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		System.out.println("All test finished.");
	}

	/**
	 * Given Doctor d2 and the list, the function should return an empty list.
	 */
	@Test
	public void testNoSimilarDoctor() {
		List<Doctor> res = new ArrayList<>();

		assertEquals(res.isEmpty(), sd.similarDoctor(d2, list).isEmpty());
		assertEquals(res.isEmpty(), sd.similarDoctor(d5, list).isEmpty());

	}

	/**
	 * Given Doctor d and the list, the function should return d4, d6, not
	 * considering the order of the review scores
	 */
	@Test
	public void testSimilarDoctorNoOrder() {
		List<Doctor> res = new ArrayList<>(Arrays.asList(d4, d6));

		assertEquals(res.size(), sd.similarDoctor(d, list).size());
		assertEquals(d4.getId(), sd.similarDoctor(d, list).get(0).getId());
		assertEquals(d6.getId(), sd.similarDoctor(d, list).get(1).getId());

	}

	/**
	 * Given Doctor d0 and the list, the function should return d3, d1
	 * considering the order of review scores
	 */
	@Test
	public void testSimilarDoctorOrderred() {
		List<Doctor> res = new ArrayList<>(Arrays.asList(d3, d1));
		assertEquals(res.size(), sd.similarDoctor(d0, list).size());
		assertEquals(d3.getId(), sd.similarDoctor(d0, list).get(0).getId());
		assertEquals(d1.getId(), sd.similarDoctor(d0, list).get(1).getId());
	}

}
