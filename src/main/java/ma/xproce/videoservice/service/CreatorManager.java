package ma.xproce.videoservice.service;

import lombok.RequiredArgsConstructor;
import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.repositories.CreatorRepository;
import ma.xproce.videoservice.dto.CreatorRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatorManager {

    private final CreatorRepository creatorRepository;
    private final ModelMapper modelMapper;

    public Creator saveCreator(CreatorRequest request) {
        return creatorRepository.save(modelMapper.map(request, Creator.class));
    }

    public Creator findById(Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
    }
}