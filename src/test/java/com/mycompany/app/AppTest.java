package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
	@Test
	 public void testAdd() {
      String str = "Junit is working fine";
      assertEquals("Junit is working fine",str);
   }
}
