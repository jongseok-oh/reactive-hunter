package com.reactive.hunter.jongking;

import com.reactive.hunter.jongking.publisher.PublisherImpl;
import com.reactive.hunter.jongking.subscriber.SubscriberImpl;
import org.junit.jupiter.api.Test;


class JongkingApplicationTests {
	@Test
	void contextLoads() {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
	}
}
