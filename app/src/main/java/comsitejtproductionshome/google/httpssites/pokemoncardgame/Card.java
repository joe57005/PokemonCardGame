package comsitejtproductionshome.google.httpssites.pokemoncardgame;

import java.io.Serializable;

/**
 * Created by Justin on 2/3/2017.
 *
 * O Hai
 */

public class Card implements Serializable{
    private int damage;

    private String name;
    private String nickName;

    private boolean burned;
    private boolean paralyzed;
    private boolean confused;
    private boolean asleep;
    private boolean poisoned;

    public Card(String name){
        this.name=name;
        setNickName(name);
        damage=0;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setNickName(name);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String name) {
        this.nickName="";
        int length=name.length();
        if(name.length()>5){
            length=5;
        }

        for(int i=0; i<length; i++){
                this.nickName += name.charAt(i);

        }
    }

    //Noted!
    //Signified by a burned symbol. Coin flipped between turns; if tails=20 damage. heads=no damage, but still burned.
    public boolean isBurned() {
        return burned;
    }

    public void setBurned(boolean burned) {
        this.burned = burned;
    }

    //Noted!
    //Unable to attack or retreat for one turn. Turned clockwise (on the rigth).
    public boolean isParalyzed() {
        return paralyzed;
    }

    public void setParalyzed(boolean paralyzed) {
        this.paralyzed = paralyzed;
    }


    //Card is turned upside down. If cois is heads, attack successful. If tails, 30 damage. Also, remains confused unless retreat or other action is taken.
    public boolean isConfused() {
        return confused;
    }

    public void setConfused(boolean confused) {
        this.confused = confused;
    }


    //Noted!
    //Card turned counter-clockwise (to the left). invovles flip coin to see if pokemon is asleep or not. Heads=awaken.
    public boolean isAsleep() {
        return asleep;
    }

    public void setAsleep(boolean asleep) {
        this.asleep = asleep;
    }


    //10 Damage done between each turn.
    public boolean isPoisoned() {
        return poisoned;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }




    public int doTurn(){
        int decider;

        int dangage=0;

        if(isBurned()){
            decider=(int) (Math.random()*(2));
            if(decider==1){
                setDamage(damage+20);
                dangage+=20;
            }
            else{
               //Nothing!
            }
        }

        if(isParalyzed()){
            setParalyzed(false);
        }

        if(isAsleep()){
            decider=(int) (Math.random()*(2));
            if(decider==1){
                setAsleep(false);
            }
            else{
                //Nothing!
            }
        }

        if(isPoisoned()){
            setDamage(damage+10);
            dangage+=10;
        }

        return dangage;
    }

    public void clearConditions(){
        burned=false;
        paralyzed=false;
        confused=false;
        asleep=false;
        poisoned=false;
    }

}
