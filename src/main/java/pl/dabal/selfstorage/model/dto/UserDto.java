package pl.dabal.selfstorage.model.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class UserDto {
    @Email
    @NotNull
    @NotBlank
    String username;

    @NotNull
    @NotBlank
    String password;

    @NotNull
    @NotBlank
    String password1;

}
