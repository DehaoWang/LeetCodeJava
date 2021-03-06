package com.leetcodejava.dataAnalysis;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangdehao on 17/4/5.
 */
public class CityNameCalOriMl {
    private static final double P5 = 0.05;
    String cityName;

    Set<Long> calRenewAids = new HashSet<Long>();
    Set<Long> oriRenewAids = new HashSet<Long>();
    Set<Long> mlRenewAids = new HashSet<Long>();

    public Set<Long> getCalRenewAids() {
        return calRenewAids;
    }

    public void setCalRenewAids(Set<Long> calRenewAids) {
        this.calRenewAids = calRenewAids;
    }

    public Set<Long> getOriRenewAids() {
        return oriRenewAids;
    }

    public void setOriRenewAids(Set<Long> oriRenewAids) {
        this.oriRenewAids = oriRenewAids;
    }

    public Set<Long> getMlRenewAids() {
        return mlRenewAids;
    }

    public void setMlRenewAids(Set<Long> mlRenewAids) {
        this.mlRenewAids = mlRenewAids;
    }

    public void addAccountId2Cal(Long accountId){
        calRenewAids.add(accountId);
    }
    public void addAccountId2Ori(Long accountId){
        oriRenewAids.add(accountId);
    }
    public void addAccountId2Ml(Long accountId){
        mlRenewAids.add(accountId);
    }
    public String getInfo(){
        return calRenewAids.size()+" "+oriRenewAids.size()+" "+mlRenewAids.size()
                + " "+(oriRenewAids.size()-calRenewAids.size())+" "+(mlRenewAids.size()-calRenewAids.size());
    }

    public boolean oriBelowP5() {
        if(calRenewAids.size() > 0 && (double)Math.abs(oriRenewAids.size()-calRenewAids.size())/calRenewAids.size() <= P5){
            return true;
        }
        return false;
    }
    public boolean mlBelowP5() {
        if(calRenewAids.size() > 0 && (double)Math.abs(mlRenewAids.size()-calRenewAids.size())/calRenewAids.size() <= P5){
            return true;
        }
        return false;
    }
}
