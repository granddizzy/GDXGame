package com.badlogic.mygame.units;

import com.badlogic.mygame.arena.Arena;
import com.badlogic.mygame.units.abstractUnits.Equipment;
import com.badlogic.mygame.units.abstractUnits.Unit;
import com.badlogic.mygame.units.abstractUnits.UnitAttackingWithWeapons;
import com.badlogic.mygame.units.abstractUnits.UnitsTypes;

/**
 * Снайпер
 */
public class Sniper extends UnitAttackingWithWeapons {
    public final int distanceSkill = 9;
    private boolean using_Hit_the_bulls_eye = false;
    public Sniper(String name) {
        super(Equipment.bow_and_arrows.getHealth(), Equipment.bow_and_arrows.getAttack(),
                Equipment.bow_and_arrows.getDefend(), UnitsTypes.Sniper, name);
    }
    public boolean hitTheBullsEye(Unit target){
        if (getAbilityPoints() == 2){
            if (using_Hit_the_bulls_eye == false) {
//                System.out.println("Попал в яблочко.");
                super.clearPointAbility();
                target.decreaseHealth(getHealth()/2);
                using_Hit_the_bulls_eye = true;

                return true;
            }
        }

        return false;
    }

    @Override
    public Unit findTarget(Arena arena) {
        // ищем чужого с минимальным здоровьем
        return arena.findAUnitWithMinimumHealth(this, true);
    }

    @Override
    public boolean applyAbility(Unit targetUnit, Arena arena) {
        boolean res = hitTheBullsEye(targetUnit);

        return res;
    }

    @Override
    public boolean isInDiapason(Unit targetUnit) {
        return this.distanceSkill >= this.getCoordinates().calculateDistance(targetUnit.getCoordinates());
    }

    @Override
    public String getCharacterRepresentation() {
        return "Snr";
    }
}
