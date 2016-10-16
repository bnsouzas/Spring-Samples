package br.com.bruno.SpringBootJSP.events;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    
	@Override
	public void onApplicationEvent(ContextClosedEvent arg0) {
		System.out.println("ContextClosedEvent");
	}

}
