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
    Texture gameover, background, crossbowman, druid, monk, palladine, peasant, robber, sniper, sorcerer, spearman, wizard;
    Arena arena;
    Music music;
    private long lastDropTime;


    @Override
    public void create() {
        lastDropTime = TimeUtils.nanoTime();

        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("fons/backgraund1.jpg"));
        gameover = new Texture(Gdx.files.internal("gameover.png"));

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

        music = Gdx.audio.newMusic(Gdx.files.internal("musics/call-of-victory.mp3"));
        music.setLooping(true);
        music.setVolume(0.2f);
        music.play();

        Map map = new Map(30, 30);

        arena = new Arena(map);

        arena.createTeam("Команда Добра", 5, "GREEN");
        arena.createTeam("Команда Зла", 5, "RED");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        for (int i = 0; i < arena.getMap().sizeX; i++) {
            for (int j = 0; j < arena.getMap().sizeY; j++) {
                Object field = arena.getMap().getField(i, j);

                int kx = Gdx.graphics.getWidth() / arena.getMap().sizeX;
                int ky = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.4) / arena.getMap().sizeY;

                batch.begin();
                if (field instanceof Unit) {
                    String type = ((Unit) field).getType();

                    int mx = 1;
                    if (((Unit) field).getTeam().color.equals("RED"))
                        mx = -1;

                    switch (type) {
                        case "Crossbowman" ->
                                batch.draw(crossbowman, i * kx, j * ky, crossbowman.getWidth() * mx, crossbowman.getHeight());
                        case "Druid" ->
                                batch.draw(druid, i * kx, j * ky, druid.getWidth() * mx, druid.getHeight());
                        case "Monk" ->
                                batch.draw(monk, i * kx, j * ky, monk.getWidth() * mx, monk.getHeight());
                        case "Palladine" ->
                                batch.draw(palladine, i * kx, j * ky, palladine.getWidth() * mx, palladine.getHeight());
                        case "Peasant" ->
                                batch.draw(peasant, i * kx, j * ky, peasant.getWidth() * mx, peasant.getHeight());
                        case "Robber" ->
                                batch.draw(robber, i * kx, j * ky, robber.getWidth() * mx, robber.getHeight());
                        case "Sniper" ->
                                batch.draw(sniper, i * kx, j * ky, sniper.getWidth() * mx, sniper.getHeight());
                        case "Sorcerer" ->
                                batch.draw(sorcerer, i * kx, j * ky, sorcerer.getWidth() * mx, sorcerer.getHeight());
                        case "Spearman" ->
                                batch.draw(spearman, i * kx, j * ky, spearman.getWidth() * mx, spearman.getHeight());
                        case "Wizard" ->
                                batch.draw(wizard, i * kx, j * ky, wizard.getWidth() * mx, wizard.getHeight());
                        default -> {
                        }
                    }
                } else if (field instanceof Pit) {

                }
                batch.end();
            }
        }

        if (TimeUtils.nanoTime() - lastDropTime > 400000000) {
            lastDropTime = TimeUtils.nanoTime();

            if (arena.checkTheNeedForTheNextRound()) {
                arena.step();
            }
        }

        Team winner = arena.getWinner();
        if (winner != null) {
            batch.begin();
            batch.draw(gameover, Gdx.graphics.getWidth() / 2 - 150 / 2, Gdx.graphics.getHeight() / 2 - 150 / 2, 150, 150);
            batch.end();
            music.stop();
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
    }
}
