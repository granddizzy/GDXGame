package com.badlogic.mygame.units.abstractUnits;

import com.badlogic.mygame.arena.Arena;
import com.badlogic.mygame.arena.map.Map;

public abstract class UnitProtective extends Unit implements UnitProtectiveInterface {
    private float increasingDefence;
    private int abilityPoints;

    public UnitProtective(int health, int defense, int attack, UnitsTypes type, String name) {
        super(health, defense, attack, type, name);
        this.abilityPoints = 0;
    }

    public UnitProtective(UnitsTypes type, String name) {
        this(0, 0, 0, type, name);
    }

    public boolean concentration() {
        if (abilityPoints < 3) {
            abilityPoints += 1;
        }
        this.addSuperimposedAction("Концентрация", 1, this.getAttack() / 4, 0, 0);
        return true;
    }

    public int getAbilityPoints() {
        return abilityPoints;
    }

    public void clearAbilityPoints() {
        abilityPoints = 0;
    }

    public void decreaseDamage(int increasingDefence) {
        super.decreaseAttack(increasingDefence);
    }

    public float getIncreasingDefence() {
        return increasingDefence;
    }

    @Override
    public void actionInDiapason(Arena arena, Unit targetUnit, boolean moveMade) {
        if (!targetUnit.getTeam().equals(this.getTeam()) || !this.applyAbility(targetUnit, arena)) {
            // если это чужой
            // или
            // если не смогли применить спобосность

            if (this.performAnAttack(targetUnit)) {
                // если смогли атаковать
                arena.addArenaMessage(this, targetUnit, " атака на ");

                //проверяем убили ли
                if (targetUnit.getHealth() == 0) {
                    // выносим труп
                    arena.removeTheCorpse(targetUnit);
                }

                if (!moveMade) {
                    // если шаг НЕ сделан
                    if (this.concentration()) {
                        arena.addArenaMessage(this, null, " сконцентрировался.");
                    }
                }
            } else {
                // если не смогли атаковать
                if (!moveMade) {
                    arena.addArenaMessage(this, null, " пропустил ход");
                    this.skipAMove();
                } else {
                    arena.addArenaMessage(this, targetUnit, " подошел к ");
                }
            }
        } else {
            arena.addArenaMessage(this, targetUnit, " способности на ");
        }
    }

    @Override
    public void actionNotInDiapason(Arena arena, Unit targetUnit, boolean moveMade) {
        if (moveMade) {
            // если шаг сделан
            arena.addArenaMessage(this, targetUnit, " подошел к ");

            if (this.concentration()) {
                arena.addArenaMessage(this, null, " сконцентрировался ");
            }
        } else {
            arena.addArenaMessage(this, null, " пропустил ход");
            this.skipAMove();
        }
    }

    @Override
    public void step(Arena arena, Map map) {
        // ищем своего
        Unit targetUnit1 = this.findTarget(arena);

        // ищем чужого
        Unit targetUnit2 = this.findTarget2(arena);

        Unit targetUnit = null;

        // вибираем цель
        if (targetUnit1 == null) targetUnit = targetUnit2;
        else if (targetUnit2 == null) targetUnit = targetUnit1;
        else {
            // выбираем кто ближе
            if (this.getCoordinates().calculateDistance(targetUnit1.getCoordinates()) > this.getCoordinates().calculateDistance(targetUnit2.getCoordinates())) {
                targetUnit = targetUnit1;
            } else {
                targetUnit = targetUnit2;
            }
        }

        if (targetUnit == null) {
            arena.addArenaMessage(this, null, " Цель не найдена ");
        } else {
            if (this.isInDiapason(targetUnit)) {
                this.actionInDiapason(arena, targetUnit, false);
            } else {
                this.decreasePointActivities();
                arena.doMove(this, targetUnit.getCoordinates());
                if (this.isInDiapason(targetUnit)) {
                    this.actionInDiapason(arena, targetUnit, true);
                } else {
                    this.actionNotInDiapason(arena, targetUnit, true);
                }
            }
        }
    }
}
