package com.user.userservice.model;

import com.user.userservice.enums.BloodGroup;
import com.user.userservice.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
//@Document(collection = "users")
@Data
@ToString
public class UserDto {
    @Id
    private String userId;
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Middle name is required")
    private String middleName;
    @NotEmpty(message = "Last name is required")
    private String lastName;
    @NotEmpty(message = "Phone Number is required")
    @Size(min=10,max = 10)
    private String phoneNumber;

    private Date dateOfBirth;

    private Gender gender;
    @NotEmpty(message = "address is required")
    private String address ;
    @NotEmpty(message = "Employee Number is required")
    private String employeeNumber;

    private BloodGroup bloodGroup;
    @NotEmpty(message = "Email is required")
    private String email;
}
