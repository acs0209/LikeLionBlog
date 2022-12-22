package sgdevcamp.blog.dto.question;

import lombok.Getter;
import lombok.Setter;
import sgdevcamp.blog.data.entity.SiteUser;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class QuestionForm {

    @NotEmpty(message = "사용자 이름은 필수 항목입니다.")
    @Size(max=15)
    private String username;

    @NotEmpty(message = "제목은 필수항목입니다")
    @Size(max=200)
    private String subject;

    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;

}
