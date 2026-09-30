package ma.xproce.videoservice.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CreatorRequest {
    private String name;
    private String email;
}