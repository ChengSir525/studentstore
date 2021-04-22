package tedu;

import java.util.Objects;

/**
 * @Description: 物品
 */
public class Good {
    // 商品的编号
    private int id;
    // 商品的名称
    private String name;
    // 商品的价格
    private int price;

    public Good(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public Good() {
    }

    @Override
    public String toString() {
        return "Good[ " +
                "物品编号=" + id +
                ", 物品名称='" + name +
                ", 物品价格=$" + price +
                " ]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Good)) return false;
        Good good = (Good) o;
        return id == good.id &&
                price == good.price &&
                Objects.equals(name, good.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
