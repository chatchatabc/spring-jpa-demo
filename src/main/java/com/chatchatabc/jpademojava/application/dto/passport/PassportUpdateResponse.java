package com.chatchatabc.jpademojava.application.dto.passport;

import com.chatchatabc.jpademojava.application.dto.ErrorContent;
import com.chatchatabc.jpademojava.domain.model.Passport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassportUpdateResponse {
    private Passport passort;
    private ErrorContent error;
}
