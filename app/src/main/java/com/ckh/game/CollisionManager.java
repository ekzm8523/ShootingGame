package com.ckh.game;

import android.graphics.Rect;

import com.ckh.gameframework.AppManager;

import java.util.ArrayList;

public class CollisionManager {
    //TODO: 추후 보
    private static CollisionManager s_Instance;
    public static CollisionManager getInstance(){
        if(s_Instance == null) s_Instance = new CollisionManager();
        return s_Instance;
    }
    public static boolean checkBoxToBox(Rect rt1, Rect rt2){
        return rt1.contains(rt2.left,rt2.top) ||
                rt1.contains(rt2.right,rt2.top) ||
                rt1.contains(rt2.left,rt2.bottom) ||
                rt1.contains(rt2.right,rt2.bottom);

    }
    public long checkCollision(
            ArrayList<Enemy> enemList,
            ArrayList<MissilePlayer> playerMissileList,
            ArrayList<MissileSpecialSkill> playerSkillList,
            ArrayList<MissileEnemy> enemyMissileList,
            ArrayList<Item> itemList,
            Player mPlayer,
            long playerHitTime
    ) {
        // PlayerMissile & enemy
        for (int i = playerMissileList.size() - 1; i >= 0 ;i--) {
            for (int j = enemList.size() -1; j>= 0; j--){
                if (CollisionManager.checkBoxToBox(enemList.get(j).mBoundBox, playerMissileList.get(i).mBoundBox)){
                    MissilePlayer missile = playerMissileList.remove(i);
                    enemList.get(j).attackedByMissile();
                    if (enemList.get(j).getHp() <= 0){
                        Enemy enem = enemList.remove((j));
                        enem.destroy(mPlayer, itemList);
                    }
                    return playerHitTime;
                }
            }
        }
        // enemy & Player
        for (int i = enemList.size()-1; i>=0; i--) {
            if (CollisionManager.checkBoxToBox(mPlayer.mBoundBox, enemList.get(i).mBoundBox)
                    && !mPlayer.mPlayerHit) {

                playerHitTime = System.currentTimeMillis();
                mPlayer.mPlayerHit = true;
                mPlayer.SetAlpha(50);
            }
        }
        // playerSkill & enemy

        for (int i = enemList.size() - 1; i >= 0 ;i--) {
            for (int j = playerSkillList.size() -1; j>= 0; j--){
                if (checkBoxToBox(playerSkillList.get(j).mBoundBox,enemList.get(i).mBoundBox)){
                    if (!(enemList.get(i) instanceof Boss)){
                        Enemy enemy = enemList.remove(j);
                        enemy.destroy(mPlayer, itemList);
                    }
                    return playerHitTime;
                }
            }
        }
        // playerSkill & enemyMssile
        for (int i = enemyMissileList.size() - 1; i >= 0 ;i--) {
            for (int j = playerSkillList.size() -1; j>= 0; j--){
                if (checkBoxToBox(playerSkillList.get(j).mBoundBox,enemyMissileList.get(i).mBoundBox)){
                    enemyMissileList.remove(j);
                }
            }
        }
        // Player & enemy
        for (int i = enemList.size()-1; i>=0; i--){
            if (checkBoxToBox(mPlayer.mBoundBox, enemList.get(i).mBoundBox)){
                enemList.remove(i);
                mPlayer.destroyPlayer();
                if(mPlayer.getLife() <= 0) {
                    AppManager.getInstance().lastScore = mPlayer.getScore();
                    AppManager.getInstance().getGameView().changeGameState(new GameEndState());
                }
            }
            if(mPlayer.mPlayerHit && (System.currentTimeMillis() - playerHitTime >= 1000))
                mPlayer.mPlayerHit = false;
        }
        // enemyMissile & Player
        for (int i=enemyMissileList.size()-1; i>=0; i--){
            if (checkBoxToBox(mPlayer.mBoundBox, enemyMissileList.get(i).mBoundBox)
                    && !mPlayer.mPlayerHit){
                playerHitTime = System.currentTimeMillis();
                mPlayer.mPlayerHit = true;
                mPlayer.SetAlpha(50);

                enemyMissileList.remove(i);
                mPlayer.destroyPlayer();

                if(mPlayer.getLife() <= 0) {
                    AppManager.getInstance().lastScore = mPlayer.getScore();
                    AppManager.getInstance().getGameView().changeGameState(new GameEndState());
                }
            }
            if(mPlayer.mPlayerHit  && (System.currentTimeMillis() - playerHitTime >= 1000)) {
                mPlayer.mPlayerHit = false;

            }
        }
        // 플레이어 - 아이템 충돌
        for (int i = itemList.size()-1; i>=0; i--){
            if (checkBoxToBox(mPlayer.mBoundBox, itemList.get(i).mBoundBox)){
                itemList.get(i).applyToPlayer(mPlayer);
                itemList.remove(i);
            }
        }
        return playerHitTime;
    }
}
