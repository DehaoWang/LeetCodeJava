package com.leetcodejava.dataAnalysis;

/**
 * Created by wangdehao on 18/1/9.
 */
public class VisitModel {
    int sumVisitNear = 0;
    int cntVisitNear = 0;
    int sumVisitFar = 0;
    int cntVisitFar = 0;

    public void increaseSumNear(int visitCnt){
        sumVisitNear += visitCnt;
    }
    public void increaseSumFar(int visitCnt){
        sumVisitFar += visitCnt;
    }

    public void increaseCntNear(){
        cntVisitNear ++;
    }
    public void increaseCntFar(){
        cntVisitFar ++;
    }

    int far2NearDecrementVisitSum = 0;
    int far2NearDecrementVisitAvg = 0;

    @Override
    public String toString() {
        return "VisitModel{" +
                "sumVisitNear=" + sumVisitNear +
                ", cntVisitNear=" + cntVisitNear +
                ", sumVisitFar=" + sumVisitFar +
                ", cntVisitFar=" + cntVisitFar +
                ", far2NearDecrementVisitSum=" + far2NearDecrementVisitSum +
                ", far2NearDecrementVisitAvg=" + far2NearDecrementVisitAvg +
                '}';
    }

    public int getSumVisitNear() {
        return sumVisitNear;
    }

    public void setSumVisitNear(int sumVisitNear) {
        this.sumVisitNear = sumVisitNear;
    }

    public int getCntVisitNear() {
        return cntVisitNear;
    }

    public void setCntVisitNear(int cntVisitNear) {
        this.cntVisitNear = cntVisitNear;
    }

    public int getSumVisitFar() {
        return sumVisitFar;
    }

    public void setSumVisitFar(int sumVisitFar) {
        this.sumVisitFar = sumVisitFar;
    }

    public int getCntVisitFar() {
        return cntVisitFar;
    }

    public void setCntVisitFar(int cntVisitFar) {
        this.cntVisitFar = cntVisitFar;
    }

    public int getFar2NearDecrementVisitSum() {
        return far2NearDecrementVisitSum;
    }

    public void setFar2NearDecrementVisitSum(int far2NearDecrementVisitSum) {
        this.far2NearDecrementVisitSum = far2NearDecrementVisitSum;
    }

    public int getFar2NearDecrementVisitAvg() {
        return far2NearDecrementVisitAvg;
    }

    public void setFar2NearDecrementVisitAvg(int far2NearDecrementVisitAvg) {
        this.far2NearDecrementVisitAvg = far2NearDecrementVisitAvg;
    }

    public void calculateModel(){
        if(sumVisitNear < sumVisitFar){
            far2NearDecrementVisitSum = 1;
        }
        if(cntVisitFar > 0 && cntVisitNear > 0 && sumVisitNear/cntVisitNear < sumVisitFar/cntVisitFar){
            far2NearDecrementVisitAvg = 1;
        }
    }
}
