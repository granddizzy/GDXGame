package com.badlogic.mygame.units;

import com.badlogic.mygame.arena.Arena;
import com.badlogic.mygame.arena.map.Pit;
import com.badlogic.mygame.units.abstractUnits.Equipment;
import com.badlogic.mygame.units.abstractUnits.Unit;
import com.badlogic.mygame.units.abstractUnits.UnitSupportiveBasic;
import com.badlogic.mygame.units.abstractUnits.UnitsTypes;

import java.util.Random;

//import arena.map.Map;

/**
 * Крестьянин
 */
public class Peasant extends UnitSupportiveBasic {
    public Peasant(String name) {
        super(Equipment.pitchfork_and_robe.getHealth(), Equipment.pitchfork_and_robe.getAttack(),
                Equipment.pitchfork_and_robe.getDefend(), UnitsTypes.Peasant, name);
    }

    @Override
    public Unit findTarget(Arena arena) {
        // ищем ближайшего чужого
        return arena.findTheNearestTeamUnit(this, true);
    }

    @Override
    public boolean applyAbility(Unit targetUnit, Arena arena) {
        switch (new Random().nextInt(3)) {
            case 0 -> {
                return pokeWithAPitchfork(targetUnit);
            }
            case 1 -> {
        return digAPit(arena);
            }
            case 2 -> {
                return kickInTheLegs(targetUnit);
            }
        }
        return false;
    }

    public boolean pokeWithAPitchfork(Unit targetUnit) {
        //тычек вилами
        this.performAnAttack(targetUnit);
        return true;
    }

    public boolean digAPit(Arena arena) {
        arena.getMap().addPit(new Pit(this.getCoordinates(), 10));
        arena.addArenaMessage(this, null, " вырыл ямку");
        return true;
    }

    public boolean kickInTheLegs(Unit targetUnit) {
        targetUnit.addSuperimposedAction("Удар по ногам", 3, 0, 0, -1);
        return true;
    }

    @Override
    public boolean isInDiapason(Unit targetUnit) {
        return this.distanceSkill >= this.getCoordinates().calculateDistance(targetUnit.getCoordinates());
    }

    @Override
    public String getCharacterRepresentation() {
        return "Pst";
    }
}
