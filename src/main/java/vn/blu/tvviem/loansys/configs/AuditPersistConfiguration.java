package vn.blu.tvviem.loansys.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // serve @CreatedDate and @LastModifiedDate
public class AuditPersistConfiguration {
}
