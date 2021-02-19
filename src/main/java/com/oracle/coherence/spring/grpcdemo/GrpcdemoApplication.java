package com.oracle.coherence.spring.grpcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tangosol.net.Coherence;

@SpringBootApplication
public class GrpcdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcdemoApplication.class, args);
	}

	@Bean(destroyMethod = "close")
	public Coherence coherenceServer() {
		final Coherence coherence = Coherence.clusterMember();
		coherence.start().join();
		return coherence;
	}

}
