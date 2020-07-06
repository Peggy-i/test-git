import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;

public class zTest extends JPanel{
	private static z ztext = new z();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testJframe() {
		assertEquals(1,ztext.Jframe());
		
	}

	@Test
	public void testels() {
		assertEquals(1,ztext.Time());
	}

}
