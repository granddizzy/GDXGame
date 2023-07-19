package com.badlogic.mygame.units.abstractUnits;

public enum Equipment {
    spear_and_cuirass(30, 17, 10, "spearman"),
    knives_and_cloak(20, 12, 7, "robber"),
    crossbow_and_helmet(25, 12, 8, "crossbowman"),
    bow_and_arrows(15, 15, 5, "sniper"),
    mantle_and_wand(18, 15, 7, "wizard"),
    runes_and_powders(20, 12, 8, "sorcerer"),
    shield_and_sword(40, 10, 13, "paladine"),
    kesa_and_beads(35, 10, 15, "monk"),
    pitchfork_and_robe(15, 7, 5, "peasant"),
    frogfoot_and_bearskin(17, 7, 5, "druid");

    private int health, attack, defend;
    private String parameter;

    Equipment(int health, int attack, int defend, String parameter) {
        this.health = health;
        this.attack = attack;
        this.defend = defend;
        this.parameter = parameter;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefend() {
        return defend;
    }

    public String getParameter() {
        return parameter;
    }
}
