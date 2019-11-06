package com.yyy.rutu.sxfy;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.Test;

public class CalculateLngLat2Meters {

    @Test
    public void cal(){
        GlobalCoordinates source = new GlobalCoordinates(30.287459,120.153576);//shop.id = 8
        GlobalCoordinates target = new GlobalCoordinates(30.317276,120.165510);//location.id = 1

        double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);//Sphere坐标系
        double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);//WGS84坐标系

        //System.out.println("Sphere坐标系计算结果："+meter1 + "米");
        //System.out.println("WGS84坐标系计算结果："+meter2 + "米");

        System.out.println("距离分：" + (5000 - meter1) / 10);
    }

    //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid){
        GeodeticCurve  geodeticCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geodeticCurve.getEllipsoidalDistance();
    }


    @Test
    public void cal1(){

    }
}
