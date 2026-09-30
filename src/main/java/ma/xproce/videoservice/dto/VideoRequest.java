package ma.xproce.videoservice.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class VideoRequest {
    private String name;
    private String url;
    private String description;
    private String datePublication;
    private CreatorRequest creator;
}