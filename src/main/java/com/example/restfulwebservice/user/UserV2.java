package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Date;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@JsonFilter("UserInfoV2")
@NoArgsConstructor
public class UserV2 extends User{

    private String grade;


}
