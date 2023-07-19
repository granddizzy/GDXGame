package com.badlogic.mygame.units;

import com.badlogic.mygame.arena.Arena;
import com.badlogic.mygame.units.abstractUnits.Unit;
import com.badlogic.mygame.arena.map.Map;

import java.util.ArrayList;

public interface UnitInterface {

    /**
     * Получает информацию о персонаже
     * @return
     */
    String getInfo();

    /**
     * Устанавливает здоровеье персонажа в 0
     */
    void die();

    /**
     * Выполняет ход песонажа в игре
     * @param arena
     */
    void step(Arena arena, Map map);

    /**
     * Находит цель
     * переопределяется каждому персонажу отдельно
     *
     * @param arena
     * @return
     */
    Unit findTarget(Arena arena);

    /**
     * Выполняет действия песонажа если цель в диапазоне
     * может выполнятся До перемещения и После
     *
     * @param arena
     * @param targetUnit
     * @param moveMade   сделал ли персонаж перемещение
     */
    void actionInDiapason(Arena arena, Unit targetUnit, boolean moveMade);

    /**
     * Выполняет действия песонажа если цель в не в диапазоне
     * может выполнятся До перемещения и После
     *
     * @param arena
     * @param targetUnit
     * @param moveMade   сделал ли персонаж перемещение
     */
    void actionNotInDiapason(Arena arena, Unit targetUnit, boolean moveMade);

    /**
     * Выполняет применение способностей к цели
     * @param targetUnit
     * @return
     */
    boolean applyAbility(Unit targetUnit, Arena arena);

    boolean isInDiapason(Unit targetUnit);

    String getCharacterRepresentation();

//    String getSpritePath();
}
