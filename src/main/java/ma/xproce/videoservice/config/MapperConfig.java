package ma.xproce.videoservice.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DateTimeFormatter fr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        mapper.addConverter(ctx -> {
            String s = ctx.getSource();
            if (s == null) return null;
            try {
                return LocalDate.parse(s, fr);
            } catch (DateTimeParseException e) {
                return LocalDate.parse(s);
            }
        }, String.class, LocalDate.class);

        return mapper;
    }
}