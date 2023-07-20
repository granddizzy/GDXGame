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

    private int fieldWigth;
    private int fieldHeight;

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


        debug = false;

        // размер рабочей области в зависимости от фона
        width = (int) Gdx.graphics.getWidth();
        heigth = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.35);

        // размер поля в зависимости от размера рабочей области и матрицы
        this.fieldWigth = width / arena.getMap().sizeX;
        this.fieldHeight =heigth / arena.getMap().sizeY;
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
                            shouUnit(batch, crossbowman, mx, i, j);
                            break;
                        }
                        case "Druid": {
                            shouUnit(batch, druid, mx, i, j);
                            break;
                        }
                        case "Monk": {
                            shouUnit(batch, monk, mx, i, j);
                            break;
                        }
                        case "Palladine": {
                            shouUnit(batch, palladine, mx, i, j);
                            break;
                        }
                        case "Peasant": {
                            shouUnit(batch, peasant, mx, i, j);
                            break;
                        }
                        case "Robber": {
                            shouUnit(batch, robber, mx, i, j);
                            break;
                        }
                        case "Sniper": {
                            shouUnit(batch, sniper, mx, i, j);
                            break;
                        }
                        case "Sorcerer": {
                            shouUnit(batch, sorcerer, mx, i, j);
                            break;
                        }
                        case "Spearman": {
                            shouUnit(batch, spearman, mx, i, j);
                            break;
                        }
                        case "Wizard": {
                            shouUnit(batch, wizard, mx, i, j);
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

    private void shouUnit(SpriteBatch batch, Texture unit, int mx, int i, int j) {
        int unitWidth = this.fieldWigth * mx * 2;
        int unitHeight = (int) (unit.getHeight() * (double) this.fieldWigth / unit.getWidth()) * 2;
        int unitX = i * this.fieldWigth + (mx == -1 ? this.fieldWigth : 0) - unitWidth / 4;
        int unitY = j * this.fieldHeight - unitHeight / 6;
        batch.draw(unit, unitX, unitY, unitWidth, unitHeight);
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
