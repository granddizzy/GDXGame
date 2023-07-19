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
    Texture gameover, background, crossbowman, druid, monk, palladine, peasant, robber, sniper, sorcerer, spearman, wizard, pit;
    Arena arena;
    Music music;
    private long lastDropTime;

    public int width;
    public int heigth;


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

        pit = new Texture("pit2.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("musics/call-of-victory.mp3"));
        music.setLooping(true);
        music.setVolume(0.2f);
        music.play();

        Map map = new Map(10, 10);

        arena = new Arena(map);

        arena.createTeam("Команда Добра", 10, "GREEN");
        arena.createTeam("Команда Зла", 10, "RED");

        // размер рабочей области
        width = (int) Gdx.graphics.getWidth();
        heigth = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.3);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        for (int i = 0; i < arena.getMap().sizeX; i++) {
            for (int j = arena.getMap().sizeY - 1; j >= 0; j--) {
                Object field = arena.getMap().getField(i, j);

                int kx = (int) ((width - width * 0.02) / arena.getMap().sizeX);
                int ky = (int) ((heigth - heigth * 0.15) / arena.getMap().sizeY);

                int fieldWigth = (int) (this.width / arena.getMap().sizeX);

                batch.begin();
                if (field instanceof Unit) {
                    String type = ((Unit) field).getType();

                    int mx = 1;
                    if (((Unit) field).getTeam().color.equals("RED"))
                        mx = -1;

                    switch (type) {
                        case "Crossbowman" -> {
                            batch.draw(crossbowman, i * kx + (mx == -1 ? crossbowman.getWidth() : 0), j * ky, fieldWigth * mx, (int) (crossbowman.getHeight() * (double) fieldWigth / crossbowman.getWidth()));
                        }
                        case "Druid" ->
                                batch.draw(druid, i * kx + (mx == -1 ? druid.getWidth() : 0), j * ky, fieldWigth * mx, (int) (druid.getHeight() * (double) fieldWigth / druid.getWidth()));
                        case "Monk" ->
                                batch.draw(monk, i * kx + (mx == -1 ? monk.getWidth() : 0), j * ky, fieldWigth * mx, (int) (monk.getHeight() * (double) fieldWigth / monk.getWidth()));
                        case "Palladine" ->
                                batch.draw(palladine, i * kx + (mx == -1 ? palladine.getWidth() : 0), j * ky, fieldWigth * mx, (int) (palladine.getHeight() * fieldWigth / palladine.getWidth()));
                        case "Peasant" ->
                                batch.draw(peasant, i * kx + (mx == -1 ? peasant.getWidth() : 0), j * ky, fieldWigth * mx, (int) (peasant.getHeight() * (double) fieldWigth / peasant.getWidth()));
                        case "Robber" ->
                                batch.draw(robber, i * kx + (mx == -1 ? robber.getWidth() : 0), j * ky, fieldWigth * mx, (int) (robber.getHeight() * (double) fieldWigth / robber.getWidth()));
                        case "Sniper" ->
                                batch.draw(sniper, i * kx + (mx == -1 ? sniper.getWidth() : 0), j * ky, fieldWigth * mx, (int) (sniper.getHeight() * (double) fieldWigth / sniper.getWidth()));
                        case "Sorcerer" ->
                                batch.draw(sorcerer, i * kx + (mx == -1 ? sorcerer.getWidth() : 0), j * ky, fieldWigth * mx, (int) (sorcerer.getHeight() * fieldWigth / sorcerer.getWidth()));
                        case "Spearman" ->
                                batch.draw(spearman, i * kx + (mx == -1 ? spearman.getWidth() : 0), j * ky, fieldWigth * mx, (int) (spearman.getHeight() * (double) fieldWigth / spearman.getWidth()));
                        case "Wizard" ->
                                batch.draw(wizard, i * kx + (mx == -1 ? wizard.getWidth() : 0), j * ky, fieldWigth * mx, (int) (wizard.getHeight() * (double) fieldWigth / wizard.getWidth()));
                        default -> {
                        }
                    }
                } else if (field instanceof Pit) {
                    batch.draw(pit, i * kx, j * ky, fieldWigth, (int) (pit.getHeight() * (double) fieldWigth / pit.getWidth()));
                }
                batch.end();
            }
        }

        if (TimeUtils.nanoTime() - lastDropTime > 400000000) {
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
    }
}
