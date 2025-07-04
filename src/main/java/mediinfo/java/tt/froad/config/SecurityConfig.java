package mediinfo.java.tt.froad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 仅用于开发环境的特殊安全配置
     * 允许免认证访问 Swagger 相关端点
     */
    @Bean
    @Profile("dev") // 仅当 dev profile 激活时生效
    public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用 CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // 配置授权规则 dev环境允许所有接口通过
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()
                );

        return http.build();
    }

    /**
     * 非开发环境的正常安全配置
     */
    @Bean
    @Profile("!dev") // 非开发环境生效
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults()) // 启用表单登录
                .httpBasic(withDefaults()); // 启用基本认证

        return http.build();
    }
}