
package com.ckh.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.GraphicObject;
import com.ckh.gameframework.IState;
import com.ckh.gameframework.R;

import java.util.ArrayList;
import java.util.Random;


public class GameState implements IState {
    //EnemyManager enemyManager;
    Random randEnem = new Random();
    long playerHitTime = 0;
    private Player mPlayer;
    private GraphicObject mKeyPad;
    private BackGround mBackGround;
    private int stage_number;
    Boolean bossState = false;
    long lastRegenEnemy = System.currentTimeMillis();

    ArrayList<Enemy> enemList = new ArrayList<>();
    ArrayList<MissilePlayer> playerMissileList = new ArrayList<>();
    ArrayList<MissileSpecialSkill> playerSkillList = new ArrayList<>();
    ArrayList<MissileEnemy> enemyMissileList = new ArrayList<>();
    ArrayList<Item> itemList = new ArrayList<>();


    public Player GetPlayer() { return mPlayer;}

    public GameState(){
        AppManager.getInstance().mGameState = this;
        initStage(1);
    }
    public void initStage(int stage){
        stage_number = stage;
        mBackGround = new BackGround(stage_number);
    }
    public void makeEnemy(){
        if (System.currentTimeMillis() - lastRegenEnemy >= 1000){
            if(!mBackGround.backgroundEnd) { // background가 끝까지 안갔을떄
                lastRegenEnemy = System.currentTimeMillis();
                EnemyManager.getInstance().AddEnemy(enemList);
            }
            else if (!bossState){           // background가 끝이면 보스 한번만 생성
                bossState = true;
                EnemyManager.getInstance().AddBoss(enemList, stage_number);
            }                               // boss가 죽으면 bossState를 false로 변경후 다음레벨로 가줄것
        }
    }

    public void checkCollision() {
        if (mPlayer.mPlayerHit) mPlayer.invincibility(playerHitTime); // 플레이어가 투명상태라면 1초가 지났는지 계속 체크
        playerHitTime = CollisionManager.getInstance().checkCollision(  //모든 Collision확인
                                                        enemList, playerMissileList,
                                                        playerSkillList, enemyMissileList,
                                                        itemList, mPlayer, playerHitTime);
    }


    @Override
    public void Init() {
        mPlayer = new Player(AppManager.getInstance().getBitmap(R.drawable.player_));
        initStage(stage_number);
    }
    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        long gameTime = System.currentTimeMillis();
        mBackGround.Update();
        mPlayer.Update(gameTime);
        for (int i=playerMissileList.size()-1 ; i>=0; i--){
            Missile pms = playerMissileList.get( i );
            pms.Update();
            if ( pms.state == Missile.STATE_OUT ) playerMissileList.remove(i);
        }
        for(int i=playerSkillList.size()-1;i>=0;i--){
            Missile pmk = playerSkillList.get(i);
            pmk.Update();
            if(pmk.state == Missile.STATE_OUT) playerSkillList.remove(i);
        }
        for (int i=enemyMissileList.size()-1 ; i>=0; i--){
            Missile enemms = enemyMissileList.get( i );
            enemms.Update();
            if ( enemms.state == Missile.STATE_OUT ) enemyMissileList.remove(i);
        }
        for (int i=enemList.size()-1; i>=0; i--){
            Enemy enem = enemList.get(i);
            enem.Update(gameTime);
            if ( enem.state == Enemy.STATE_OUT ) enemList.remove(i);
        }
        for (Enemy enem : enemList) enem.Update(gameTime);
        for (int i=itemList.size()-1; i>=0; i--){
            Item item = itemList.get(i);
            item.Update(gameTime);
            if (item.bOut == true) itemList.remove(i);
        }
        this.makeEnemy();
        this.checkCollision();
    }

    @Override
    public void Render(Canvas canvas) {
        mBackGround.Draw(canvas);
        for (Missile pms: playerMissileList) pms.Draw(canvas);
        for (Missile enemms: enemyMissileList) enemms.Draw(canvas);
        for (Enemy enem : enemList) enem.Draw(canvas);
        for (Item item : itemList) item.Draw(canvas);
        for (Missile pmk: playerSkillList) pmk.Draw(canvas);
        mPlayer.Draw(canvas);
        //mKeyPad.Draw(canvas);

        // TODO: REFACTORING
        Paint p = new Paint();
        p.setTextSize(40);
        p.setColor(Color.BLACK);
        canvas.drawText("남은 목숨 :"+ mPlayer.getLife(),20,40,p);

        Paint s = new Paint();
        s.setTextSize(40);
        s.setColor(Color.BLUE);
        canvas.drawText("필살기 :"+(mPlayer.getSkill()),20,100, s);

        Paint sc = new Paint();
        sc.setTextSize(40);
        sc.setColor(Color.WHITE);
        canvas.drawText("스코어 :"+(mPlayer.getScore()),20,160, sc);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int x = mPlayer.getX();
        int y = mPlayer.getY();

        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT && x >= 10) mPlayer.setPosition(x-20, y);
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && x <= 1900) mPlayer.setPosition(x+20, y);
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP && y >= 10) mPlayer.setPosition(x, y-20);
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN && y <= 800) mPlayer.setPosition(x, y+20);
        if (keyCode == KeyEvent.KEYCODE_SPACE)
           playerMissileList.add(new MissilePlayer(x + 271-30, y + 120, mPlayer));

        if (keyCode == KeyEvent.KEYCODE_Z) {
            if(mPlayer.getSkill() >0) {
                playerSkillList.add(new MissileSpecialSkill(-1000, 300));
                mPlayer.useSkill();
            }
        }

        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            Log.i("motion event", "ACTION_DOWN");
        else if (event.getAction() == MotionEvent.ACTION_MOVE)
            Log.i("motion event", "ACTION_MOVE");
        else Log.i("motion event", "" + event.getAction());
        return true;
    }
}