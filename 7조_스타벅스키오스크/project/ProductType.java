package project;

public enum ProductType {
    COFFEE("COFFEE","커피"),
    DESSERT("DESSERT","디저트"),
    ;

    private final String code;
    private final String name;

    ProductType(String code, String name) {
        this.code = code;
        this.name = name;
    }


    // 코드로부터 enum 상수를 가져오는 정적 메서드
    public static ProductType fromCode(String code) {
        for (ProductType type : ProductType.values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }

    public static ProductType fromName(String name) {
        for (ProductType type : ProductType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + name + "]");
    }

    public static String[] getComboList(){
        return new String[]{"커피", "디저트"};
    }
}

