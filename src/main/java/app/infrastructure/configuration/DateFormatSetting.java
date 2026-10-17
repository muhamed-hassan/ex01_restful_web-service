package app.infrastructure.configuration;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateFormatSetting {
	
	@Value("${date.pattern}")
    private String datePattern;
	
	@Bean
	public SimpleDateFormat dateFormatter() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
		return simpleDateFormat;
	}

}
