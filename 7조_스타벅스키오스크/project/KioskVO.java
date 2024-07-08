package project;


public class KioskVO {

    private int quantity; // 수량
    private String PRODUCT;
    private String PRICE;
    private ProductType TYPE;
    private String DELETEYN;
    private String INSERTDATE;
    private String UPDATEDATE;

    public KioskVO(String PRODUCT, String PRICE, ProductType TYPE, int quantity) {
        this.PRODUCT = PRODUCT;
        this.PRICE = PRICE;
        this.TYPE = TYPE;
        this.quantity = quantity;
    }

    public KioskVO(String PRODUCT, String PRICE, ProductType TYPE, String DELETEYN, String INSERTDATE, String UPDATEDATE) {
        this.PRODUCT = PRODUCT;
        this.PRICE = PRICE;
        this.TYPE = TYPE;
        this.DELETEYN = DELETEYN;
        this.INSERTDATE = INSERTDATE;
        this.UPDATEDATE = UPDATEDATE;
    }

    public String getPRODUCT() {
        return PRODUCT;
    }

    public String getPRICE() {
        return PRICE;
    }


    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity = quantity;}
    public ProductType getTYPE(){return TYPE;}



}