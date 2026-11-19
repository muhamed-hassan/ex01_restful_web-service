package app.infrastructure.configuration;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateFormatSettings {
	
	@Value("${date.pattern}")
    private String datePattern;
	
	@Bean
	public SimpleDateFormat simpleDateFormat() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
		return simpleDateFormat;
	}

}
