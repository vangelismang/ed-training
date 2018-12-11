package org.acme.eshop.repository;

import java.util.concurrent.atomic.AtomicLong;

import lombok.Setter;
import org.acme.eshop.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OrderRepositoryImpl extends AbstractRepository<Order> implements OrderRepository {

	@Value("${sequenceStart.order}")
	private Long sequenceStart;

	@Setter
	private AtomicLong SEQUENCE = new AtomicLong();

	@PostConstruct
	private void initOrder() {
		setSEQUENCE(new AtomicLong(sequenceStart));
	}


	@Override
	public AtomicLong getSequence() {
		return SEQUENCE;
	}
}
