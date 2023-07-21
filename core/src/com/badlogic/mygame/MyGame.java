package com.badlogic.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.mygame.arena.Arena;
import com.badlogic.mygame.arena.map.Map;
import com.badlogic.mygame.arena.map.Pit;
import com.badlogic.mygame.units.Team;
import com.badlogic.mygame.units.abstractUnits.Unit;

import java.util.Random;

public class MyGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture gameover, crossbowman, druid, monk, palladine, peasant, robber, sniper, sorcerer, spearman, wizard, pit, debugField;
    Texture background;
    Arena arena;
    Music music;
    private long lastDropTime;

    public int width;
    public int heigth;

    public boolean debug;

    private int fieldWigth;
    private int fieldHeight;

    @Override
    public void create() {
        lastDropTime = TimeUtils.nanoTime();

        batch = new SpriteBatch();
        gameover = new Texture(Gdx.files.internal("gameover.png"));
        debugField = new Texture(Gdx.files.internal("debugField.png"));

        // выбираем юнитов
        switch (new Random().nextInt(2)) {
            case 0: {
                crossbowman = new Texture("units/Crossbowman1.gif");
                break;
            }
            case 1: {
                crossbowman = new Texture("units/Crossbowman2.gif");
                break;
            }
        }
        switch (new Random().nextInt(5)) {
            case 0: {
                druid = new Texture("units/Druid1.gif");
                break;
            }
            case 1: {
                druid = new Texture("units/Druid2.gif");
                break;
            }
            case 2: {
                druid = new Texture("units/Druid3.gif");
                break;
            }
            case 3: {
                druid = new Texture("units/Druid4.gif");
                break;
            }
            case 4: {
                druid = new Texture("units/Druid5.gif");
                break;
            }
        }
        switch (new Random().nextInt(2)) {
            case 0: {
                monk = new Texture("units/Monk1.gif");
                break;
            }
            case 1: {
                monk = new Texture("units/Monk2.gif");
                break;
            }
        }
        switch (new Random().nextInt(3)) {
            case 0: {
                palladine = new Texture("units/Palladine1.gif");
                break;
            }
            case 1: {
                palladine = new Texture("units/Palladine2.gif");
                break;
            }
            case 2: {
                palladine = new Texture("units/Palladine3.gif");
                break;
            }
        }
        switch (new Random().nextInt(2)) {
            case 0: {
                peasant = new Texture("units/Peasant1.gif");
                break;
            }
            case 1: {
                peasant = new Texture("units/Peasant2.gif");
                break;
            }
        }
        switch (new Random().nextInt(3)) {
            case 0: {
                robber = new Texture("units/Robber1.gif");
                break;
            }
            case 1: {
                robber = new Texture("units/Robber2.gif");
                break;
            }
            case 2: {
                robber = new Texture("units/Robber3.gif");
                break;
            }
        }
        switch (new Random().nextInt(3)) {
            case 0: {
                sniper = new Texture("units/Sniper1.gif");
                break;
            }
            case 1: {
                sniper = new Texture("units/Sniper2.gif");
                break;
            }
            case 2: {
                sniper = new Texture("units/Sniper3.gif");
                break;
            }
        }
        switch (new Random().nextInt(4)) {
            case 0: {
                sorcerer = new Texture("units/Sorcerer1.gif");
                break;
            }
            case 1: {
                sorcerer = new Texture("units/Sorcerer2.gif");
                break;
            }
            case 2: {
                sorcerer = new Texture("units/Sorcerer3.gif");
                break;
            }
            case 3: {
                sorcerer = new Texture("units/Sorcerer4.gif");
                break;
            }
        }
        switch (new Random().nextInt(2)) {
            case 0: {
                spearman = new Texture("units/Spearman1.gif");
                break;
            }
            case 1: {
                spearman = new Texture("units/Spearman2.gif");
                break;
            }
        }
        switch (new Random().nextInt(3)) {
            case 0: {
                wizard = new Texture("units/Wizard1.gif");
                break;
            }
            case 1: {
                wizard = new Texture("units/Wizard2.gif");
                break;
            }
            case 2: {
                wizard = new Texture("units/Wizard3gif");
                break;
            }
        }

        pit = new Texture("pit2.png");

        // выбираем фон
        int rand = new Random().nextInt(5);
//        rand = 1;
        switch (rand) {
            case 0: {
                music = Gdx.audio.newMusic(Gdx.files.internal("musics/call-of-victory.mp3"));
                music.setVolume(0.1f);
                break;
            }
            case 1: {
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Fon_1.mp3"));
                music.setVolume(0.5f);
                break;
            }
            case 2: {
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Fon_2.mp3"));
                music.setVolume(0.5f);
                break;
            }
            case 3: {
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Fon_3.mp3"));
                music.setVolume(0.5f);
                break;
            }
            case 4: {
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Fon_4.mp3"));
                music.setVolume(0.5f);
                break;
            }
        }

        music.setLooping(true);
        music.play();

        Map map = new Map(20, 20);

        arena = new Arena(map);

        arena.createTeam("Команда Добра", 10, "GREEN");
        arena.createTeam("Команда Зла", 10, "RED");


        debug = false;

        // выбираем фон
        rand = new Random().nextInt(7);
//        rand = 6;
        switch (rand) {
            case 0: {
                background = new Texture(Gdx.files.internal("fons/background1.jpg"));
                // размер рабочей области в зависимости от фона
                width = Gdx.graphics.getWidth();
                heigth = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.35);
                break;
            }
            case 1: {
                background = new Texture(Gdx.files.internal("fons/Fon_5.jpg"));
                // размер рабочей области в зависимости от фона
                width = Gdx.graphics.getWidth();
                heigth = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.3);
                break;
            }
            case 2: {
                background = new Texture(Gdx.files.internal("fons/Fon_6.jpg"));
                // размер рабочей области в зависимости от фона
                width = Gdx.graphics.getWidth();
                heigth = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.5);
                break;
            }
            case 3: {
                background = new Texture(Gdx.files.internal("fons/Fon_7.jpg"));
                // размер рабочей области в зависимости от фона
                width = Gdx.graphics.getWidth();
                heigth = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.45);
                break;
            }
            case 4: {
                background = new Texture(Gdx.files.internal("fons/Fon_4.jpg"));
                // размер рабочей области в зависимости от фона
                width = Gdx.graphics.getWidth();
                heigth = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.3);
                break;
            }
            case 5: {
                background = new Texture(Gdx.files.internal("fons/Fon_11.jpg"));
                // размер рабочей области в зависимости от фона
                width = Gdx.graphics.getWidth();
                heigth = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.6);
                break;
            }
            case 6: {
                background = new Texture(Gdx.files.internal("fons/Fon_9.jpg"));
                // размер рабочей области в зависимости от фона
                width = Gdx.graphics.getWidth();
                heigth = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.6);
                break;
            }
        }

        // размер поля в зависимости от размера рабочей области и матрицы
        this.fieldWigth = width / arena.getMap().sizeX;
        this.fieldHeight = heigth / arena.getMap().sizeY;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        for (int j = arena.getMap().sizeY - 1; j >= 0; j--) {
            for (int i = 0; i < arena.getMap().sizeX; i++) {
                Object field = arena.getMap().getField(i, j);

                //  вывод полей для дебага
                if (debug) {
                    batch.begin();
                    batch.draw(debugField, i * fieldWigth, j * fieldHeight, fieldWigth, fieldHeight);
                    batch.end();
                }

                batch.begin();
                if (field instanceof Unit) {
                    String type = ((Unit) field).getType();

                    int mx = 1;
                    if (((Unit) field).getTeam().color.equals("RED"))
                        mx = -1;

                    // рисуем спрайты вычисляя размер в зависимости от размера рабочей области и матрицы
                    switch (type) {
                        case "Crossbowman": {
                            showUnit(batch, crossbowman, mx, i, j);
                            break;
                        }
                        case "Druid": {
                            showUnit(batch, druid, mx, i, j);
                            break;
                        }
                        case "Monk": {
                            showUnit(batch, monk, mx, i, j);
                            break;
                        }
                        case "Palladine": {
                            showUnit(batch, palladine, mx, i, j);
                            break;
                        }
                        case "Peasant": {
                            showUnit(batch, peasant, mx, i, j);
                            break;
                        }
                        case "Robber": {
                            showUnit(batch, robber, mx, i, j);
                            break;
                        }
                        case "Sniper": {
                            showUnit(batch, sniper, mx, i, j);
                            break;
                        }
                        case "Sorcerer": {
                            showUnit(batch, sorcerer, mx, i, j);
                            break;
                        }
                        case "Spearman": {
                            showUnit(batch, spearman, mx, i, j);
                            break;
                        }
                        case "Wizard": {
                            showUnit(batch, wizard, mx, i, j);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                } else if (field instanceof Pit) {
                    batch.draw(pit, i * fieldWigth, j * fieldHeight, fieldWigth, (int) (pit.getHeight() * (double) fieldWigth / pit.getWidth()));
                }
                batch.end();
            }
        }

        if (TimeUtils.nanoTime() - lastDropTime > 300000000) {
            lastDropTime = TimeUtils.nanoTime();

            if (arena.checkTheNeedForTheNextRound()) {
                arena.step();
            } else {
                music.stop();
            }
        }

        Team winner = arena.getWinner();
        if (winner != null) {
            batch.begin();
            batch.draw(gameover, Gdx.graphics.getWidth() / 2 - 350 / 2, Gdx.graphics.getHeight() / 2 - 300 / 2, 350, 300);
            batch.end();
        }

    }

    private void showUnit(SpriteBatch batch, Texture unit, int mx, int i, int j) {
        int unitWidth = this.fieldWigth * mx * 2;
        int unitHeight = (int) (unit.getHeight() * (double) this.fieldWigth / unit.getWidth()) * 2;
        int unitX = i * this.fieldWigth + (mx == -1 ? this.fieldWigth : 0) - unitWidth / 4;
        int unitY = j * this.fieldHeight - unitHeight / 6;
        batch.draw(unit, unitX, unitY, unitWidth, unitHeight);
    }

    @Override
    public void dispose() {
        batch.dispose();
        crossbowman.dispose();
        druid.dispose();
        monk.dispose();
        palladine.dispose();
        peasant.dispose();
        robber.dispose();
        sniper.dispose();
        sorcerer.dispose();
        spearman.dispose();
        wizard.dispose();
        pit.dispose();
        debugField.dispose();

        background.dispose();
    }
}
