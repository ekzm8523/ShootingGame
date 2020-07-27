package com.ckh.game.model;

import java.util.ArrayList;

public class RankingListVO {
    int allCount;
    ArrayList<Ranking> data;

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public ArrayList<Ranking> getData() {
        return data;
    }

    public void setData(ArrayList<Ranking> data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "allCount: " + getAllCount() + "datas : " + getData();
    }



}
