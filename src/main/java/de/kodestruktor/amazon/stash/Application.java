package de.kodestruktor.amazon.stash;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class Application {

  public static void main(final String[] args) {
    final SpringApplication application = new SpringApplication(Application.class);
    final Banner banner = (environment, sourceClass, out) -> {
      out.println("                                  _           _");
      out.println(" ___ _____ ___ ___ ___ ___    ___| |_ ___ ___| |_");
      out.println("| .'|     | .'|- _| . |   |  |_ -|  _| .'|_ -|   |");
      out.println("|__,|_|_|_|__,|___|___|_|_|  |___|_| |__,|___|_|_|");
      out.println();
    };

    application.setBanner(banner);
    application.run(args);
  }
}
