package dataobjects.items;

public class Weapon extends IItem {
    public enum Type { MELEE, RANGED, MAGIC }

    private int attackPower = 0;
    private int range = 0;
    private int manaCost = 0;
    private Type weaponType;
    private float accuracy = 0;

    public Weapon(String itemName, String itemDescription) {
        super(itemName, itemDescription);
    }

    public Weapon(String weaponName, String weaponDescription, int attackPower, Type weaponType) {
        super(weaponName, weaponDescription);
        this.attackPower = attackPower;
        this.weaponType = weaponType;
    }

    public int getAttackPower() {
        return attackPower;
    }
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        if (weaponType == Type.MELEE) return;
        this.range = range;
    }

    public int getManaCost() {
        return manaCost;
    }
    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public Type getWeaponType() {
        return weaponType;
    }
    public void setWeaponType(Type weaponType) {
        this.weaponType = weaponType;
    }

    public float getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }
}
