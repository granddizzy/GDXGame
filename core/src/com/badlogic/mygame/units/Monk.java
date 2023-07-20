package com.badlogic.mygame.units;

import com.badlogic.mygame.arena.Arena;
import com.badlogic.mygame.units.abstractUnits.Equipment;
import com.badlogic.mygame.units.abstractUnits.Unit;
import com.badlogic.mygame.units.abstractUnits.UnitProtectiveWithoutShild;
import com.badlogic.mygame.units.abstractUnits.UnitsTypes;

import java.util.Random;

/**
 * Монах
 */
public class Monk extends UnitProtectiveWithoutShild {
    public final int distanceSkill = 9;

    public Monk(String name) {
        super(Equipment.kesa_and_beads.getHealth(), Equipment.kesa_and_beads.getAttack(),
                Equipment.kesa_and_beads.getDefend(), UnitsTypes.Monk, name);
    }

    /**
     * Мысли монаха
     * @param target
     * @return
     */
    public boolean mindMonk(Unit target) {
        if (getAbilityPoints() == 2) {
            super.clearAbilityPoints();
            //super.decreaseDamage(target.getDefense() * 1);   // вот здесь как-то определить тип атаки

            target.addSuperimposedAction("Мысли монаха", 1,0, target.getDefense(), 0);
            return true;
        }
        return false;
    }

    public boolean monkSHand(Unit target) {
        if (getAbilityPoints() == 2) {
            super.clearAbilityPoints();
            target.addSuperimposedAction("Рука монаха", 1,0, 100, 0);
            return true;
        }
        return false;
    }

    @Override
    public Unit findTarget(Arena arena) {
        // ищем своего с минимальным здоровьем
        return arena.findAUnitWithMinimumHealth(this, false);
    }

    @Override
    public Unit findTarget2(Arena arena) {
        // ищем чужого
        return arena.findAUnitWithMinimumHealth(this, true);
    }

    @Override
    public boolean applyAbility(Unit targetUnit, Arena arena) {
        switch (new Random().nextInt(2)) {
            case 0:  {
                return mindMonk(targetUnit);
            }
            case 1: {
                return monkSHand(targetUnit);
            }
        }
        return false;
    }

    @Override
    public boolean isInDiapason(Unit targetUnit) {
        return this.distanceSkill >= this.getCoordinates().calculateDistance(targetUnit.getCoordinates());
    }

    @Override
    public String getCharacterRepresentation() {
        return "Mnk";
    }
}
