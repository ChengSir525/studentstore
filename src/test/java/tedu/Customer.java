package tedu;

import java.util.Objects;

/**
 * @Description: 顾客
 */
public class Customer {
    private String username;
    private int points;
    /*
    this指代当前对象

    构造方法： 建对象的模板，需要建对象的同时传入两个值，
    第一个必须是String类型，第二必须是int类型
     */
    public Customer(String username, int points) {
        this.username = username;
        this.points = points;
    }
    /*
    构造方法： 建对象的模板，不需要传任何值
     */
    public Customer() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return points == customer.points &&
                Objects.equals(username, customer.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, points);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", points=" + points +
                '}';
    }
}
