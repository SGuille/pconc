import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class ThreadPoolTestCase {

	private ThreadPool t;
	private Worker mock1;
	private Worker mock2;
	private Worker mock3;
	@Before
	
	public void setUp(){
		this.t= new ThreadPool(3,10);
		
	}
	@Test
	public void testCantWorkers() {
		assertEquals(this.t.getWorkers().size() , 3);
	}
	@Test 
	public void testTamBuff(){
		assertEquals(this.t.getBuffer().getCapacidad() , 10);
		
	}
	@Test
	public void testPartesIguales(){
		
		assertTrue(this.t.getPartesIguales() == 3);
	}
	@Test
	public void testAsignarTrabajo(){
		//
	}
}
