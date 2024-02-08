package com.link.back;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.link.back.infra.rabbitmq.RabbitLightHouse;

@SpringBootTest
public class LighthouseTest {
	@Autowired
	RabbitLightHouse rabbitLightHouse;
	@Test
	public void test() {
		rabbitLightHouse.sendMessages(1L);
	}
}
