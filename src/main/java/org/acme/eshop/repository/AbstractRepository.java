package org.acme.eshop.repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.acme.eshop.model.BaseEntity;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@PropertySource("classpath:my.properties")
public abstract class AbstractRepository<T extends BaseEntity> implements BaseRepository<T, Long> {

    @Value("${logging.level.repo}")
    private Level level;

    Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);

	private final Map<Long, T> STORAGE = new LinkedHashMap<>();

	public abstract AtomicLong getSequence();

	@PostConstruct
    public void init() {
        System.err.println(logger.getLevel());
    }

	@Override
	public T create(final T entity) {
		final Long key = getSequence().getAndIncrement();
		entity.setId(key);
		STORAGE.put(key, entity);
		return entity;
	}

	@Override
	public void update(final T entity) {
		STORAGE.put(entity.getId(), entity);
	}

	@Override
	public void delete(final T entity) {
		STORAGE.remove(entity.getId());
	}

	@Override
	public boolean exists(final T entity) {
		return Objects.nonNull(STORAGE.get(entity.getId()));
	}

	@Override
	public T get(final Long id) {
		return STORAGE.get(id);
	}

	@Override
	public List<T> findAll() {
		return new ArrayList<>(STORAGE.values());
	}
}
