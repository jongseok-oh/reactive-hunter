package com.reactive.hunter.jongking.publisher;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class SubscriptionImpl implements Subscription {

    private static final int MAX_ITEMS = 10;
    private Subscriber<? super String> subscriber;
    private boolean isCanceled = false;
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long requested) {
        if (isCanceled) {
            return;
        }
        log.info("subscriber has requested: {}", requested);
        for (int i = 0; i < requested && count < MAX_ITEMS; i++) {
            ++count;
            subscriber.onNext("email-" + i);
        }
        if (count == MAX_ITEMS) {
            log.info("subscriber has completed");
            subscriber.onComplete();
            cancel();
        }
    }

    @Override
    public void cancel() {
        log.info("subscriber has canceled");
        this.isCanceled = true;
    }
}
