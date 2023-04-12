package com.chatchatabc.jpademojava.application.dto.post;

import com.chatchatabc.jpademojava.application.dto.ErrorContent;
import com.chatchatabc.jpademojava.domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Post post;
    private ErrorContent error;
}
