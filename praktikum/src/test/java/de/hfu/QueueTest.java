package de.hfu;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {
	
	Queue testQueue = new Queue(3);

	@Test
	public void testEnqueue() {
		// Test 1
		testQueue.enqueue(1);
		testQueue.enqueue(2);
		testQueue.enqueue(3);
		int[] soll1 = {1,2,3};
		for(int i = 0; i < 3; i++)
			assertEquals(soll1[i], testQueue.queue[i]);
		
		// Test 2
		testQueue.enqueue(4);
		testQueue.enqueue(5);
		testQueue.enqueue(6);
		int[] soll2 = {1,2,6};
		for(int i = 0; i < 3; i++)
			assertEquals(soll2[i], testQueue.queue[i]);
		
		// Test 3
		testQueue.enqueue(4);
		testQueue.enqueue(5);
		testQueue.enqueue(6);
		int[] soll3 = {1,2,6};
		for(int i = 0; i < 3; i++)
			assertEquals(soll3[i], testQueue.queue[i]);
				
				
		
	}
	
	@Test
	public void testDequeue() {
		
		testQueue.enqueue(1);
		testQueue.enqueue(2);
		testQueue.enqueue(3);
		
		assertEquals(1, testQueue.dequeue());
		assertEquals(2, testQueue.dequeue());
		assertEquals(3, testQueue.dequeue());
	}

}
