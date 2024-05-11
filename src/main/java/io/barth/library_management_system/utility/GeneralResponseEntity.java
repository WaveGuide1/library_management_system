package io.barth.library_management_system.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponseEntity<T> {

    T info;
    private String message;
}
