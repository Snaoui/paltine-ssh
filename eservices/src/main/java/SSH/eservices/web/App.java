package SSH.eservices.web;

import SSH.eservices.model.Role;
import SSH.eservices.model.User;
import SSH.eservices.web.services.Itf.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * App config!
 *
 */
@SpringBootApplication
@EntityScan(basePackages = "SSH.eservices.model")
@EnableJpaRepositories(basePackages = {"SSH.eservices.repository"})
@ComponentScan(basePackages = {"SSH.eservices.web"})
public class App
		implements CommandLineRunner {

    @Autowired
		private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

	@Override
	public void run(String... strings) throws Exception {
		// TODO auto-generated

		logger.info("Trying to insert users in database...");
		logger.info("Creating user1...");
		User user1 = userService.createUser("admin@admin.fr", "admin", "password", Role.ADMIN_ROLE);
		logger.info("Creating user2...");
		User user2 = userService.createUser("che@che.fr", "cherif", "password", Role.SIMPLE_USER_ROLE);

	}
}
