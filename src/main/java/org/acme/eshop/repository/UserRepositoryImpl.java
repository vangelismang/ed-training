package org.acme.eshop.repository;

import java.util.concurrent.atomic.AtomicLong;

import lombok.Setter;
import org.acme.eshop.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {

	@Value("${sequenceStart.user}")
	private Long sequenceStart;

	@Setter
	private AtomicLong SEQUENCE = new AtomicLong();

	@PostConstruct
	private void initUser() {
		setSEQUENCE(new AtomicLong(sequenceStart));
	}

	@Override
	public AtomicLong getSequence() {
		return SEQUENCE;
	}
}
