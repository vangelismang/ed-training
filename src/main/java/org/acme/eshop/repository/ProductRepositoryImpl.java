package org.acme.eshop.repository;

import java.util.concurrent.atomic.AtomicLong;

import lombok.Setter;
import org.acme.eshop.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProductRepositoryImpl extends AbstractRepository<Product> implements ProductRepository {

	@Value("${sequenceStart.product}")
	private Long sequenceStart;

	@Setter
	private AtomicLong SEQUENCE = new AtomicLong();

	@PostConstruct
	private void initProduct() {
		setSEQUENCE(new AtomicLong(sequenceStart));
	}

	@Override
	public AtomicLong getSequence() {
		return SEQUENCE;
	}
}
