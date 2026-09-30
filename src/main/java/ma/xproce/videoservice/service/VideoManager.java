package ma.xproce.videoservice.service;

import lombok.RequiredArgsConstructor;
import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dao.repositories.VideoRepository;
import ma.xproce.videoservice.dto.VideoRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoManager {

    private final VideoRepository videoRepository;
    private final CreatorManager creatorManager;
    private final ModelMapper modelMapper;

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Video findById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Video %s not found", id)));
    }

    public Video saveVideo(VideoRequest request) {
        Video video = modelMapper.map(request, Video.class);
        if (request.getCreator() != null) {
            Creator creator = creatorManager.saveCreator(request.getCreator());
            video.setCreator(creator);
        }
        return videoRepository.save(video);
    }

    public Video updateVideo(Video video) {
        return videoRepository.save(video);
    }
}