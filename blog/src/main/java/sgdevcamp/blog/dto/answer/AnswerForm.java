package sgdevcamp.blog.dto.answer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AnswerForm {

    @NotEmpty(message = "사용자 이름은 필수항목입니다.")
    @Size(max = 15)
    private String username;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;

}
