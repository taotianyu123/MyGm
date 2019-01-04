package pojo;

/**
 * @ClassName pojo.ExtGoods
 * @Author tty
 * @Date 2018\12\31 0031 23:52
 * @Version 1.0
 */
public class ExtGoods extends Goods {
    private Smallclass smallclass;
    private Seller seller;
    private Discount discount;


    public ExtGoods(Smallclass smallclass, Seller seller, Discount discount) {
        this.smallclass = smallclass;
        this.seller = seller;
        this.discount = discount;
    }

    public ExtGoods() {
    }

    public Smallclass getSmallclass() {
        return smallclass;
    }

    public void setSmallclass(Smallclass smallclass) {
        this.smallclass = smallclass;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
