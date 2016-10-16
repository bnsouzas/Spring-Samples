package br.com.bruno.SpringBootJSP.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Component
public class RequestHandledEventListener implements ApplicationListener<RequestHandledEvent> {

	@Override
	public void onApplicationEvent(RequestHandledEvent requestHandled) {
		System.out.println("RequestHandledEvent: " + requestHandled.getDescription());
	}

}
