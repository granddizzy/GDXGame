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

public class MyGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture gameover, background, crossbowman, druid, monk, palladine, peasant, robber, sniper, sorcerer, spearman, wizard, pit, debugField;
    Arena arena;
    Music music;
    private long lastDropTime;

    public int width;
    public int heigth;

    public boolean debug;


    @Override
    public void create() {
        lastDropTime = TimeUtils.nanoTime();

        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("fons/backgraund1.jpg"));
        gameover = new Texture(Gdx.files.internal("gameover.png"));
        debugField = new Texture(Gdx.files.internal("debugField.png"));

        crossbowman = new Texture("units/Crossbowman1.gif");
        druid = new Texture("units/Druid1.gif");
        monk = new Texture("units/Monk1.gif");
        palladine = new Texture("units/Palladine1.gif");
        peasant = new Texture("units/Peasant1.gif");
        robber = new Texture("units/Robber1.gif");
        sniper = new Texture("units/Sniper1.gif");
        sorcerer = new Texture("units/Sorcerer1.gif");
        spearman = new Texture("units/Spearman1.gif");
        wizard = new Texture("units/Spearman1.gif");

        pit = new Texture("pit2.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("musics/call-of-victory.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

        Map map = new Map(20, 20);

        arena = new Arena(map);

        arena.createTeam("Команда Добра", 10, "GREEN");
        arena.createTeam("Команда Зла", 10, "RED");

        // размер рабочей области в зависимости от фона
        width = (int) Gdx.graphics.getWidth();
        heigth = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.35);

        debug = false;
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

                // размер поля в зависимости от размера рабочей области и матрицы
                int fieldWigth = (int) (width / arena.getMap().sizeX);
                int fieldHeight = (int) (heigth / arena.getMap().sizeY);

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
                            int unitWidth = fieldWigth * mx * 2;
                            int unitHeight = (int) (crossbowman.getHeight() * (double) fieldWigth / crossbowman.getWidth()) * 2;
                            int unitX = i * fieldWigth + (mx == -1 ? fieldWigth : 0) - (int) (unitWidth / 4);
                            int unitY = j * fieldHeight - (int) (unitHeight / 6);
                            batch.draw(crossbowman, unitX, unitY, unitWidth, unitHeight);
                            break;
                        }
                        case "Druid": {
//                            batch.draw(druid, i * fieldWigth + (mx == -1 ? druid.getWidth() : 0), j * fieldHeight, fieldWigth * mx, (int) (druid.getHeight() * (double) fieldWigth / druid.getWidth()));
                            int unitWidth = fieldWigth * mx * 2;
                            int unitHeight = (int) (druid.getHeight() * (double) fieldWigth / druid.getWidth()) * 2;
                            int unitX = i * fieldWigth + (mx == -1 ? fieldWigth : 0) - (int) (unitWidth / 4);
                            int unitY = j * fieldHeight - (int) (unitHeight / 6);
                            batch.draw(druid, unitX, unitY, unitWidth, unitHeight);
                            break;
                        }
                        case "Monk": {
//                            batch.draw(monk, i * fieldWigth + (mx == -1 ? monk.getWidth() : 0), j * fieldHeight, fieldWigth * mx, (int) (monk.getHeight() * (double) fieldWigth / monk.getWidth()));
                            int unitWidth = fieldWigth * mx * 2;
                            int unitHeight = (int) (monk.getHeight() * (double) fieldWigth / monk.getWidth()) * 2;
                            int unitX = i * fieldWigth + (mx == -1 ? fieldWigth : 0) - (int) (unitWidth / 4);
                            int unitY = j * fieldHeight - (int) (unitHeight / 6);
                            batch.draw(monk, unitX, unitY, unitWidth, unitHeight);
                            break;
                        }
                        case "Palladine": {
//                            batch.draw(palladine, i * fieldWigth + (mx == -1 ? palladine.getWidth() : 0), j * fieldHeight, fieldWigth * mx, (int) (palladine.getHeight() * fieldWigth / palladine.getWidth()));
                            int unitWidth = fieldWigth * mx * 2;
                            int unitHeight = (int) (palladine.getHeight() * (double) fieldWigth / palladine.getWidth()) * 2;
                            int unitX = i * fieldWigth + (mx == -1 ? fieldWigth : 0) - (int) (unitWidth / 4);
                            int unitY = j * fieldHeight - (int) (unitHeight / 6);
                            batch.draw(palladine, unitX, unitY, unitWidth, unitHeight);
                            break;
                        }
                        case "Peasant": {
//                            batch.draw(peasant, i * fieldWigth + (mx == -1 ? peasant.getWidth() : 0), j * fieldHeight, fieldWigth * mx, (int) (peasant.getHeight() * (double) fieldWigth / peasant.getWidth()));
                            int unitWidth = fieldWigth * mx * 2;
                            int unitHeight = (int) (peasant.getHeight() * (double) fieldWigth / peasant.getWidth()) * 2;
                            int unitX = i * fieldWigth + (mx == -1 ? fieldWigth : 0) - (int) (unitWidth / 4);
                            int unitY = j * fieldHeight - (int) (unitHeight / 6);
                            batch.draw(peasant, unitX, unitY, unitWidth, unitHeight);
                            break;
                        }
                        case "Robber": {
//                            batch.draw(robber, i * fieldWigth + (mx == -1 ? robber.getWidth() : 0), j * fieldHeight, fieldWigth * mx, (int) (robber.getHeight() * (double) fieldWigth / robber.getWidth()));
                            int unitWidth = fieldWigth * mx * 2;
                            int unitHeight = (int) (robber.getHeight() * (double) fieldWigth / robber.getWidth()) * 2;
                            int unitX = i * fieldWigth + (mx == -1 ? fieldWigth : 0) - (int) (unitWidth / 4);
                            int unitY = j * fieldHeight - (int) (unitHeight / 6);
                            batch.draw(robber, unitX, unitY, unitWidth, unitHeight);
                            break;
                        }
                        case "Sniper": {
//                            batch.draw(sniper, i * fieldWigth + (mx == -1 ? sniper.getWidth() : 0), j * fieldHeight, fieldWigth * mx, (int) (sniper.getHeight() * (double) fieldWigth / sniper.getWidth()));
                            int unitWidth = fieldWigth * mx * 2;
                            int unitHeight = (int) (sniper.getHeight() * (double) fieldWigth / sniper.getWidth()) * 2;
                            int unitX = i * fieldWigth + (mx == -1 ? fieldWigth : 0) - (int) (unitWidth / 4);
                            int unitY = j * fieldHeight - (int) (unitHeight / 6);
                            batch.draw(sniper, unitX, unitY, unitWidth, unitHeight);
                            break;
                        }
                        case "Sorcerer": {
//                            batch.draw(sorcerer, i * fieldWigth + (mx == -1 ? sorcerer.getWidth() : 0), j * fieldHeight, fieldWigth * mx, (int) (sorcerer.getHeight() * fieldWigth / sorcerer.getWidth()));
                            int unitWidth = fieldWigth * mx * 2;
                            int unitHeight = (int) (sorcerer.getHeight() * (double) fieldWigth / sorcerer.getWidth()) * 2;
                            int unitX = i * fieldWigth + (mx == -1 ? fieldWigth : 0) - (int) (unitWidth / 4);
                            int unitY = j * fieldHeight - (int) (unitHeight / 6);
                            batch.draw(sorcerer, unitX, unitY, unitWidth, unitHeight);
                            break;
                        }
                        case "Spearman": {
//                            batch.draw(spearman, i * fieldWigth + (mx == -1 ? spearman.getWidth() : 0), j * fieldHeight, fieldWigth * mx, (int) (spearman.getHeight() * (double) fieldWigth / spearman.getWidth()));
                            int unitWidth = fieldWigth * mx * 2;
                            int unitHeight = (int) (spearman.getHeight() * (double) fieldWigth / spearman.getWidth()) * 2;
                            int unitX = i * fieldWigth + (mx == -1 ? fieldWigth : 0) - (int) (unitWidth / 4);
                            int unitY = j * fieldHeight - (int) (unitHeight / 6);
                            batch.draw(spearman, unitX, unitY, unitWidth, unitHeight);
                            break;
                        }
                        case "Wizard": {
//                            batch.draw(wizard, i * fieldWigth + (mx == -1 ? wizard.getWidth() : 0), j * fieldHeight, fieldWigth * mx, (int) (wizard.getHeight() * (double) fieldWigth / wizard.getWidth()));
                            int unitWidth = fieldWigth * mx * 2;
                            int unitHeight = (int) (wizard.getHeight() * (double) fieldWigth / wizard.getWidth()) * 2;
                            int unitX = i * fieldWigth + (mx == -1 ? fieldWigth : 0) - (int) (unitWidth / 4);
                            int unitY = j * fieldHeight - (int) (unitHeight / 6);
                            batch.draw(wizard, unitX, unitY, unitWidth, unitHeight);
                            break;
                        }
                        default:  {
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
            batch.draw(gameover, Gdx.graphics.getWidth() / 2 - 150 / 2, Gdx.graphics.getHeight() / 2 - 150 / 2, 150, 150);
            batch.end();
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
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
    }
}
