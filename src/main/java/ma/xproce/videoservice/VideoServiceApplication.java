package ma.xproce.videoservice;

import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dao.repositories.CreatorRepository;
import ma.xproce.videoservice.dao.repositories.VideoRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VideoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            CreatorRepository creatorRepository,
            VideoRepository videoRepository) {

        return args -> {

            // Creators
            List<Creator> creators = creatorRepository.saveAll(List.of(
                    Creator.builder()
                            .name("Mohamed")
                            .email("mohamed@gmail.com")
                            .build(),

                    Creator.builder()
                            .name("Yassine")
                            .email("yassine@gmail.com")
                            .build(),

                    Creator.builder()
                            .name("Sara")
                            .email("sara@gmail.com")
                            .build()
            ));

            // Videos
            videoRepository.saveAll(List.of(
                    Video.builder()
                            .name("Introduction à Spring Boot")
                            .url("https://youtube.com/video1")
                            .description("Cours d'introduction à Spring Boot")
                            .datePublication(LocalDate.of(2026, 9, 1))
                            .creator(creators.get(0))
                            .build(),

                    Video.builder()
                            .name("Spring Data JPA")
                            .url("https://youtube.com/video2")
                            .description("Introduction à Spring Data JPA")
                            .datePublication(LocalDate.of(2026, 9, 5))
                            .creator(creators.get(0))
                            .build(),

                    Video.builder()
                            .name("GraphQL avec Spring")
                            .url("https://youtube.com/video3")
                            .description("Créer une API GraphQL avec Spring Boot")
                            .datePublication(LocalDate.of(2026, 9, 10))
                            .creator(creators.get(1))
                            .build(),

                    Video.builder()
                            .name("H2 Database")
                            .url("https://youtube.com/video4")
                            .description("Utilisation de la base H2")
                            .datePublication(LocalDate.of(2026, 9, 15))
                            .creator(creators.get(2))
                            .build()
            ));

            System.out.println("=================================");
            System.out.println("Creators et Videos ajoutés !");
            System.out.println("=================================");
        };
    }
}