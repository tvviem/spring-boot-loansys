package vn.blu.tvviem.loansys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
// @EnableJpaAuditing // dùng cho tao @CreatedDate and @LastModifiedDate
public class LoansysApplication {

	@Autowired
	private ThymeleafProperties properties;

	@Value("${spring.thymeleaf.templates_root:}")
	private String templatesRoot;

    public static void main(String[] args) {
		SpringApplication.run(LoansysApplication.class, args);
	}

	@Bean
	public ITemplateResolver defaultTemplateResolver() {
		FileTemplateResolver resolver = new FileTemplateResolver();
		resolver.setSuffix(properties.getSuffix());
		resolver.setPrefix(templatesRoot);
		resolver.setTemplateMode(properties.getMode());
		resolver.setCacheable(properties.isCache());
		return resolver;
	}
}

