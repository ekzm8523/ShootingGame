package com.ckh.game;

import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {
    private static EnemyManager s_Instance;
    Random randEnem = new Random();

    public static EnemyManager getInstance(){
        if(s_Instance == null) s_Instance = new EnemyManager();
        return s_Instance;
    }
    public void AddEnemy(ArrayList<Enemy> enemList){
        int enemType = randEnem.nextInt(4);
        Enemy enem = null;
        if (enemType == 0) enem = new Enemy_1();
        else if (enemType == 1 || enemType == 2 || enemType == 3) enem = new Enemy_2();
        enem.setPosition(1920, randEnem.nextInt(900));
        enemList.add(enem);
    }
    public void AddBoss(ArrayList<Enemy> enemList, int bossLevel){
        if(bossLevel == 1){
            Enemy enem = new Boss1();
            enem.setPosition(1200, 100);
            enemList.add( enem );
        }
        else if(bossLevel == 2){
            Enemy enem = new Boss2();
            enem.setPosition(1400, 100);
            enemList.add( enem );
        }
        else if(bossLevel == 3){
            Enemy enem = new Boss3();
            enem.setPosition(1200, 100);
            enemList.add( enem );
        }

    }




}
