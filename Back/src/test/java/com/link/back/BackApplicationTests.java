// package com.link.back;
//
// import org.junit.jupiter.api.Test;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import com.link.back.dto.RefreshToken;
// import com.link.back.repository.RefreshTokenRepository;
//
// // import com.link.back.infra.rabbitmq.RabbitPublisher;
//
// @SpringBootTest
// class BackApplicationTests {
// 	@Autowired
// 	RefreshTokenRepository refreshTokenRepository;
// 	@Test
// 	void test() {
// 		RefreshToken refreshToken = refreshTokenRepository.save(
// 			new RefreshToken("1")
// 		);
// 		refreshTokenRepository.delete(refreshToken);
// 	}
// 	// @Autowired
// 	// RabbitPublisher rabbitPublisher;
// 	// @Autowired
// 	// RabbitTemplate rabbitTemplate;
// 	// @Test
// 	// void contextLoads() {
// 	// }
// 	//
// 	// @Test
// 	// void testRabbitMQ() {
// 	// 	rabbitPublisher.sendMessages(1L);
// 	//
// 	// }
// }
