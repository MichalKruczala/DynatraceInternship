package it.dynatrace.internship.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@ComponentScan("it.dynatrace.internship")

public class AppConfiguration {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .directModelSubstitute(Object.class, Void.class)
                .select()
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(createApiInfo());

    }

    private ApiInfo createApiInfo() {
        return new ApiInfo("DynatraceInternship",
                "Rest api endpoints",
                "1",
                "",
                new Contact("Michał", "https://github.com/MichalJunior", "kruczalamichal@gmail.com"),
                "",
                "",
                Collections.emptyList());
    }
}
