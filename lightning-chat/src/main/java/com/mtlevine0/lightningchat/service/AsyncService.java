package com.mtlevine0.lightningchat.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncService {
	
	@Async("asyncMethod")
	public Future<Long> asyncMethod(String message) {
		
		long total = 0;
		
		for(int i = 0; i < 5; i++) {
			
			System.out.println(i+1);
			total++;

			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return new AsyncResult<Long>(total);
	}

}
