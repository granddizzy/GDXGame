package com.badlogic.mygame.units.abstractUnits;

import com.badlogic.mygame.arena.Arena;
import com.badlogic.mygame.units.Coordinates;

public abstract class UnitSupportive extends Unit {

    private int speed;
    //private int pointActivites;

    public UnitSupportive(int health, int defense, int attack, UnitsTypes type, String name) {
        super(health, defense, attack, type, name);
        speed = super.getSpeed() * 2;
    }

    public UnitSupportive(UnitsTypes type, String name) {
        this(0, 0, 0, type, name);
    }

    public void actionInDiapason(Arena arena, Unit targetUnit, boolean moveMade) {
        if (!this.applyAbility(targetUnit, arena)) {
            // всегда применяет

        } else {
            arena.addArenaMessage(this, targetUnit, " способности на ");

            // убежать
            Coordinates directionCoordinatesToRun = new Coordinates(this.getCoordinates().x, this.getCoordinates().y);

            if (targetUnit.getCoordinates().x > directionCoordinatesToRun.x) directionCoordinatesToRun.x -= 10;
            else if (targetUnit.getCoordinates().x < directionCoordinatesToRun.x) directionCoordinatesToRun.x += 10;
            if (targetUnit.getCoordinates().y > directionCoordinatesToRun.y) directionCoordinatesToRun.y -= 10;
            else if (targetUnit.getCoordinates().y < directionCoordinatesToRun.y) directionCoordinatesToRun.y += 10;

            arena.doMove(this, directionCoordinatesToRun);
            arena.addArenaMessage(this, null, " убежал");
        }
    }

    @Override
    public void actionNotInDiapason(Arena arena, Unit targetUnit, boolean moveMade) {
        if (moveMade) {
            // если шаг сделан
            arena.addArenaMessage(this, targetUnit, " подошел к ");
        } else {
            arena.addArenaMessage(this, null, " пропустил ход");
            this.skipAMove();
        }
    }
}
