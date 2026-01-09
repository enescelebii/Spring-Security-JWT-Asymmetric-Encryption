package com.vena.app.auth.request;


import com.vena.app.validation.NonDisposableEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {

    @NotBlank(message = "VALIDATION.REGISTRATION.FIRSTNAME.NOT_BLANK")
    @Size(min = 3, max = 50, message = "VALIDATION.REGISTRATION.FIRSTNAME.SIZE")
    @Pattern(regexp = "^[\\p{L} '-]+$", message = "VALIDATION.REGISTRATION.FIRSTNAME.PATTERN")
    @Schema(example = "Enes")
    private String firstName;

    @NotBlank(message = "VALIDATION.REGISTRATION.LASTNAME.NOT_BLANK")
    @Size(min = 3, max = 50, message = "VALIDATION.REGISTRATION.LASTNAME.SIZE")
    @Pattern(regexp = "^[\\p{L} '-]+$", message = "VALIDATION.REGISTRATION.LASTNAME.PATTERN")
    @Schema(example = "Çelebi")
    private String lastName;

    @NotBlank(message = "VALIDATION.REGISTRATION.EMAIL.NOT_BLANK")
    @Email(message = "VALIDATION.REGISTRATION.EMAIL.FORMAT")
    // block disposable emails
    @NonDisposableEmail(message = "VALIDATION.REGISTRATION.EMAIL.DISPOSABLE")
    @Schema(example = "enescelebi222@gmail.com")
    private String email;

    @NotBlank(message = "VALIDATION.REGISTRATION.PHONE.NOT_BLANK")
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "VALIDATION.REGISTRATION.PHONE.FORMAT")
    @Schema(example = "+905350000000")
    private String phoneNumber;

    @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.NOT_BLANK")
    @Size(min = 8, max = 20, message = "VALIDATION.REGISTRATION.PASSWORD.SIZE")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "VALIDATION.REGISTRATION.PASSWORD.PATTERN")
    @Schema(example = "Password123!")
    private String password;

    @NotBlank(message = "VALIDATION.REGISTRATION.CONFIRM_PASSWORD.NOT_BLANK")
    @Size(min = 8, max = 20, message = "VALIDATION.REGISTRATION.CONFIRM_PASSWORD.SIZE")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "VALIDATION.REGISTRATION.CONFIRM_PASSWORD.PATTERN")
    @Schema(example = "Password123!")
    private String confirmPassword;
}
