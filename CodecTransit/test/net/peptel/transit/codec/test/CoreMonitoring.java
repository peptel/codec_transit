package net.peptel.transit.codec.test;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import junit.framework.TestCase;

public class CoreMonitoring extends TestCase {

	public void testCurrentCore() {
		int avpro = Runtime.getRuntime().availableProcessors(); 
		
		System.out.println(avpro);
		
		multithread(avpro);
		
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(1, 1);
	}
	
	public void multithread(int avpro){ 
        List<A> arr = new ArrayList<A>();

		arr.add(new A());
		arr.add(new A());
		arr.add(new A());
        
        ExecutorService exec = Executors.newFixedThreadPool(avpro);
        
        for (A s: arr) { 
        	exec.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(s);
					try {
						s.exceptionGen();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
        } 
    } 
	
	class A {
		public void exceptionGen() throws IOException {
			System.out.println();
			throw new IOException("OK");
		}
	}
}
