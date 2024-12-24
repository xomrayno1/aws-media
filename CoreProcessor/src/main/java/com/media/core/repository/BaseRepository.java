package com.media.core.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> extends PersistRepository<T>, ReadOnlyRepository<T> {
 
}
