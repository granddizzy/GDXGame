package com.badlogic.mygame.units;

import com.badlogic.mygame.arena.Arena;
import com.badlogic.mygame.units.abstractUnits.Equipment;
import com.badlogic.mygame.units.abstractUnits.Unit;
import com.badlogic.mygame.units.abstractUnits.UnitAttackingWithWeapons;
import com.badlogic.mygame.units.abstractUnits.UnitsTypes;

/**
 * Копейщик
 */
public class Spearman extends UnitAttackingWithWeapons {
    public final int distanceSkill = 2;
    public Spearman(String name) {
        super(Equipment.spear_and_cuirass.getHealth(), Equipment.spear_and_cuirass.getAttack(),
                Equipment.spear_and_cuirass.getDefend(), UnitsTypes.Spearman,  name);
    }
    public boolean stun(Unit target){
        if (this.getAbilityPoints() == 2 && this.getPointActivites() == 1) {
//            System.out.println("Оглушаю.");
            super.clearPointAbility();
            super.performAnAttack(target);
            target.clearPointActivites();

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
        boolean res = stun(targetUnit);

        return res;
    }

    @Override
    public boolean isInDiapason(Unit targetUnit) {
        return this.distanceSkill >= this.getCoordinates().calculateDistance(targetUnit.getCoordinates());
    }

    @Override
    public String getCharacterRepresentation() {
        return "Spn";
    }
}
