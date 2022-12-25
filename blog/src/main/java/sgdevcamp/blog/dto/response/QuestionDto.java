package sgdevcamp.blog.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionDto {

    private String subject;

    private String content;


}