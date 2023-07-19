package com.badlogic.mygame.units;

import com.badlogic.mygame.arena.Arena;
import com.badlogic.mygame.units.abstractUnits.Equipment;
import com.badlogic.mygame.units.abstractUnits.Unit;
import com.badlogic.mygame.units.abstractUnits.UnitAttackingWithWeapons;
import com.badlogic.mygame.units.abstractUnits.UnitsTypes;

/**
 * Разбойник
 */
public class Robber extends UnitAttackingWithWeapons {
    public Robber(String name) {
        super(Equipment.knives_and_cloak.getHealth(), Equipment.knives_and_cloak.getAttack(),
                Equipment.knives_and_cloak.getDefend(), UnitsTypes.Robber, name);
    }
    public boolean theft(Unit target){
        if (getAbilityPoints() == 2) {
//            System.out.println("Ворую.");
            super.clearPointAbility();
            for (int i = 0; i < 2; i++) {
                target.decreasePointActivities();
            }

            return true;
        }

        return false;
    }
    public boolean jab(Unit target){
        if (getAbilityPoints() == 2){
//            System.out.println("jab");
            super.clearPointAbility();
            target.decreasePointActivities();
            target.decreaseHealth(10);

            return true;
        }

        return false;
    }

    @Override
    public Unit findTarget(Arena arena) {
        // ищем ближайшего чужого
        return arena.findTheNearestTeamUnit(this, true);
    }

    @Override
    public boolean applyAbility(Unit targetUnit, Arena arena) {
        boolean res = theft(targetUnit);

        return res;
    }

    @Override
    public boolean isInDiapason(Unit targetUnit) {
        return this.distanceSkill >= this.getCoordinates().calculateDistance(targetUnit.getCoordinates());
    }

    @Override
    public String getCharacterRepresentation() {
        return "Rbr";
    }
}
