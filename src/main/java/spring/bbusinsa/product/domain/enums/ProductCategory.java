package spring.bbusinsa.product.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spring.bbusinsa.global.error.BbusinsaException;
import spring.bbusinsa.global.error.ErrorType;

@Getter
@AllArgsConstructor
public enum ProductCategory {
    OUTER("아우터"), TOP("상의"), BOTTOM("하의"), ETC("기타");

    private final String value;

    public static ProductCategory getName(String value){
        for (ProductCategory category : ProductCategory.values()) {
            if (category.getValue().equals(value)) {
                return category;
            }
        }
        throw new BbusinsaException(ErrorType.PRODUCT_CATEGORY_IS_INVALID);
    }
}
