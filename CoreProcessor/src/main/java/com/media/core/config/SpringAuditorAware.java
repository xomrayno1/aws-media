package com.media.core.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class SpringAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("SYS");
	}

}
