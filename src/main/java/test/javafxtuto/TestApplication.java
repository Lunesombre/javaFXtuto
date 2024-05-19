package test.javafxtuto;

import javafx.application.Application;
import lombok.Getter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import test.javafxtuto.helloworld.HelloApp;

@SpringBootApplication
public class TestApplication {

  @Getter
  private static ConfigurableApplicationContext context;

  public static void main(String[] args) {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(TestApplication.class);
    builder.headless(false);
    context = builder.run(args);

    Application.launch(HelloApp.class);

  }


}

