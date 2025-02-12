package spring.bbusinsa.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spring.bbusinsa.global.error.BbusinsaException;
import spring.bbusinsa.global.error.ErrorType;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("MALE"), FEMALE("FEMALE"), NONE("NONE");

    private final String value;

    public static Gender getGender(String value){
        for (Gender gender : Gender.values()) {
            if (gender.getValue().equals(value)) {
                return gender;
            }
        }
        throw new BbusinsaException(ErrorType.MEMBER_GENDER_IS_INVALID);
    }
}
