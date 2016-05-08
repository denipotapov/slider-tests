package org.yota.automation.offer;

/**
 * Created by Denis on 5/7/2016.
 */
public class Offer {

    private String speed;
    private int cost, time;

    public Offer (int time, String speed, int cost) {
        this.time = time;
        this.speed = speed;
        this.cost = cost;
    }

    public String getSpeed() {
        return this.speed;
    }

    public int getCost() {
        return this.cost;
    }

    public int getTime(){
        return this.time;
    }

    @Override
    public boolean equals(Object offer)
    {
        Offer of = (Offer)offer;
        boolean eq;
        if (this.cost == of.cost && this.speed.equals(of.speed))
            eq = true;
        else eq = false;

        return eq;
    }
}
