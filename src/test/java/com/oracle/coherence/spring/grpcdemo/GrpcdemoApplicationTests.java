package com.oracle.coherence.spring.grpcdemo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.oracle.coherence.client.GrpcSessionConfiguration;
import com.tangosol.net.NamedCache;
import com.tangosol.net.Session;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

@SpringBootTest
class GrpcdemoApplicationTests {

	@Test
	void contextLoads() throws Exception {
		Channel channel = buildChannel("localhost", 1408, false);
		Session session = buildSession(channel);
		NamedCache<String, String> cache = session.getCache("test");
		cache.put("foo", "bar");
		session.close();
	}

	protected Session buildSession(Channel channel) {

		GrpcSessionConfiguration.Builder builder = GrpcSessionConfiguration.builder(channel);
		GrpcSessionConfiguration grpcSessionConfiguration = builder.build();

		Optional<Session> optional = Session.create(grpcSessionConfiguration);
		return optional.get();
	}

	private Channel buildChannel(String host, int port, boolean useTls) {
		ManagedChannelBuilder<?> chanelBuilder = ManagedChannelBuilder.forAddress(host, port);
		if (!useTls) {
			chanelBuilder.usePlaintext();
		}
		return chanelBuilder.build();
	}

}
