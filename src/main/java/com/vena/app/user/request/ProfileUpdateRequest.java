package com.vena.app.user.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateRequest {

    @NotBlank(message = "VALIDATION.PROFILE_UPDATE.FIRST_NAME.NOT_BLANK")
    private String firstName;

    @NotBlank(message = "VALIDATION.PROFILE_UPDATE.LAST_NAME.NOT_BLANK")
    private String lastName;

     // old time for date of birth
    @Past(message = "VALIDATION.PROFILE_UPDATE.DATE_OF_BIRTH.PAST")
    @NotNull(message = "VALIDATION.PROFILE_UPDATE.DATE_OF_BIRTH.NOT_NULL")
    private LocalDate dateOfBirth;

}
