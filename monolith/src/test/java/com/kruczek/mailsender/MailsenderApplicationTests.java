package com.kruczek.mailsender;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
//fixme maven profile cant be picked up by application.properties during build, @Profile is temp workaround
@Disabled("maven profile issue")
@SpringBootTest
class MailsenderApplicationTests {

	@Test
	void contextLoads() {
	}

}
