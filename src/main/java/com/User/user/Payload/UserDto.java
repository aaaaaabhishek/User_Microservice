package com.User.user.Payload;

import com.User.user.Validation.Password;
import com.User.user.Validation.ValidDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    public String user_id;
    @NotNull(message="It can't null ")
    public String user_name;
    @Column(name = "First name")
    public String first_name;
    @Column(name="Middle name")
    public String middle_name;
    @Password
    @NotNull(message="Password can't null")
    public String password;
    @NotNull(message = "Email can't be null or empty")
    @Email
    public String email;
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number format")
    @Column(name = "Contact")
    private String contact;
    public LocalDateTime date;
    public String description;
    public LocalDateTime timeout;
    @ValidDate(patterns = {"yyyy-MM-dd","yyyy/MM/dd", "MM/dd/yyyy","MM-dd-yyyy", "dd-MM-yyyy","dd/MM/yyyy"}, message = "Invalid date format")
    public LocalDateTime date_time_format;
}
