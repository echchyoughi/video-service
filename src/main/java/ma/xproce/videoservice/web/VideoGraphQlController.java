package ma.xproce.videoservice.web;

import lombok.RequiredArgsConstructor;
import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dto.CreatorRequest;
import ma.xproce.videoservice.dto.VideoRequest;
import ma.xproce.videoservice.service.CreatorManager;
import ma.xproce.videoservice.service.VideoManager;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class VideoGraphQlController {

    private final CreatorManager creatorManager;
    private final VideoManager videoManager;

    @QueryMapping
    public List<Video> videoList() {
        return videoManager.getAllVideos();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorManager.findById(id);
    }

    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest creator) {
        return creatorManager.saveCreator(creator);
    }

    @MutationMapping
    public Video saveVideo(@Argument VideoRequest video) {
        return videoManager.saveVideo(video);
    }

    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    CreatorRequest creatorRequest = CreatorRequest.builder()
                            .name("x" + new Random().nextInt())
                            .email("x@gmail.com")
                            .build();
                    Creator creator = creatorManager.saveCreator(creatorRequest);
                    Video video = videoManager.findById(1L);
                    video.setCreator(creator);
                    videoManager.updateVideo(video);
                    return video;
                }));
    }
}