package org.acme.eshop.repository;

import java.util.concurrent.atomic.AtomicLong;

import lombok.Setter;
import org.acme.eshop.model.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@PropertySource("classpath:my.properties")
public class CategoryRepositoryImpl extends AbstractRepository<Category> implements CategoryRepository {

	@Value("${sequenceStart.category}")
    private Long sequenceStart;

    @Setter
    private AtomicLong SEQUENCE = new AtomicLong();

	@PostConstruct
    private void initCat() {
	   setSEQUENCE(new AtomicLong(sequenceStart));
    }

	@Override
	public AtomicLong getSequence() {
		return SEQUENCE;
	}
}
