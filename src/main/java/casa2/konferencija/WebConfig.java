package casa2.konferencija;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import casa2.konferencija.converter.OsobaToPlacanjeConverter;
import casa2.konferencija.converter.OsobaToPrimanjeConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new OsobaToPlacanjeConverter());
        registry.addConverter(new OsobaToPrimanjeConverter());
    }
}