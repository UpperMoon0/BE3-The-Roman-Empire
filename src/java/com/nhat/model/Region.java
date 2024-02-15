package model;

public class Region {
    private int id;
    private String name;
    private long marketValue;
    private short growth;
    private short revoltRate;
    private int foodSupply;

    // Default constructor
    public Region() {
    }

    public Region(String name, long marketValue, short growth, short revoltRate, int foodSupply) {
        this.name = name;
        this.marketValue = marketValue;
        this.growth = growth;
        this.revoltRate = revoltRate;
        this.foodSupply = foodSupply;
    }

    public Region(int id, String name, long marketValue, short growth, short revoltRate, int foodSupply) {
        this.id = id;
        this.name = name;
        this.marketValue = marketValue;
        this.growth = growth;
        this.revoltRate = revoltRate;
        this.foodSupply = foodSupply;
    }

    // Getter and Setter methods for each field

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(long marketValue) {
        this.marketValue = marketValue;
    }

    public short getGrowth() {
        return growth;
    }

    public void setGrowth(short growth) {
        this.growth = growth;
    }

    public short getRevoltRate() {
        return revoltRate;
    }

    public void setRevoltRate(short revoltRate) {
        this.revoltRate = revoltRate;
    }

    public int getFoodSupply() {
        return foodSupply;
    }

    public void setFoodSupply(int foodSupply) {
        this.foodSupply = foodSupply;
    }

    // Override toString for better representation

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marketValue=" + marketValue +
                ", growth=" + growth +
                ", revoltRate=" + revoltRate +
                ", foodSupply=" + foodSupply +
                '}';
    }
}
