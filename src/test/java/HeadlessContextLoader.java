import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootContextLoader;

public class HeadlessContextLoader extends SpringBootContextLoader {

    @Override
    protected SpringApplication getSpringApplication() {
        final var application = super.getSpringApplication();
        application.setHeadless(false);
        return application;
    }

}
