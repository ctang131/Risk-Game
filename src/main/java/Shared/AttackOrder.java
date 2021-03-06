package Shared;

import java.util.Random;

public class AttackOrder extends OrderBasic {

    public AttackOrder(Player p, String orderType, Territory fromT, Territory toT, int units) {
        super(p, orderType, fromT, toT, units);
    }

    private int rollDice(int from, int to) {
        Random rand = new Random();
        return rand.nextInt((to - from) + 1) + from;
    }

    @Override
    public void execute() {
        int att_units = units, def_units = toT.getUnits();
        Player attacker = this.getPlayer(), defender = toT.getOwner();
        while(att_units > 0 && def_units > 0) {
            int attNum = rollDice(1, 20);
            int defNum = rollDice(1, 20);
            if (attNum > defNum) def_units--;
            else att_units--;

            /* For accurate testing, we don't use random number
            if (att_units > def_units) {
                att_units -= def_units;
                def_units = 0;
            }
            if (def_units > att_units) {
                def_units -= att_units;
                att_units = 0;
            }
            if (def_units == att_units) {
                def_units = 1;
                att_units = 0;
            }
            */

        }
        if(att_units > 0) {  // attacker wins
            toT.setUnits(att_units);
            toT.setOwner(attacker);
        }
        else {  //defender wins
            toT.setUnits(def_units);
        }
    }
}
