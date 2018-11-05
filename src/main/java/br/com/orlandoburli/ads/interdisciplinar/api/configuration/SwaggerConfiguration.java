package br.com.orlandoburli.ads.interdisciplinar.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket buildApiDocumentation() {
		// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.orlandoburli"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(this.buildMetaDataDocumentation());
		// @formatter:on
	}

	private ApiInfo buildMetaDataDocumentation() {
		// @formatter:off
		return new ApiInfoBuilder()
					.title("ADS - Sistema de Gestão Escolar - API")
					.description("Módulo de API's")
					.version("1.0.0")
					.contact(new Contact("Orlando Burli", "orlandoburli.com.br", "orlando.burli@gmail.com"))
				.license("Apache License Version 2.0")
				.build();
		// @formatter:on
	}
}
