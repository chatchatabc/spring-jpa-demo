package com.chatchatabc.jpademojava.application.dto.country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryAssignRequest {
    private String userId;
    private String countryId;
}
