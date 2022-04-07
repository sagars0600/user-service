package com.user.userservice.model;

import com.user.userservice.constfile.ConstFiles;
import com.user.userservice.enums.BloodGroup;
import com.user.userservice.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@Data
@ToString
public class User {
    @Id
    private String userId;

    @NotNull(message = ConstFiles.firstName)
    private String firstName;
    @NotNull(message = ConstFiles.middleName)
    private String middleName;

    @NotEmpty(message = ConstFiles.lastName)
    private String lastName;


    @NotEmpty(message = ConstFiles.phoneNumber)

    @Size(min=10,max = 10)
    private String phoneNumber;

    private Gender gender;

    @NotEmpty(message = ConstFiles.address)
    private String address;

    private Date dateOfBirth;

    @NotEmpty(message=ConstFiles.employeeNumber)
    private String employeeNumber;

    private BloodGroup bloodGroup;

    @NotEmpty(message = ConstFiles.email)
    private String email;

    @NotNull(message = ConstFiles.password)
    private String password;
}
