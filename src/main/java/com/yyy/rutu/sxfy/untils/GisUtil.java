package com.yyy.rutu.sxfy.untils;

import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;

import java.math.BigDecimal;

public final class GisUtil {

    /**
     * 经纬度转换倍数
     */
    private static final int MULTIPLE = 1000000;

    private static double EARTH_RADIUS = 6378.137;

    public static RedisGeoCommands.GeoLocation<String> createGeoLocation(String name, int lng, int lat) {
        return new RedisGeoCommands.GeoLocation<>(name, createPoint(lng, lat));
    }

    public static int calculateDistance(int lat1, int lng1, int lat2, int lng2) {
        return calculateDistance((double)lat1 / 1000000.0D, (double)lng1 / 1000000.0D, (double)lat2 / 1000000.0D, (double)lng2 / 1000000.0D);
    }

    public static int calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        double pointA = lat1 * Math.PI / 180.0D - lat2 * Math.PI / 180.0D;
        double pointB = lng1 * Math.PI / 180.0D - lng2 * Math.PI / 180.0D;
        double distance = 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(pointA / 2.0D), 2.0D) + Math.cos(lat1 * Math.PI / 180.0D) * Math.cos(lat2 * Math.PI / 180.0D) * Math.pow(Math.sin(pointB / 2.0D), 2.0D)));
        distance = distance * EARTH_RADIUS * 1000.0D;
        return (int) distance;
    }

    public static Point createPoint(int lng, int lat) {
        return new Point(convertOriginalCoordinate(lng), convertOriginalCoordinate(lat));
    }

    /**
     * 整型经纬度转原生经纬度
     *
     * @param lngOrLat
     * @return
     */
    public static double convertOriginalCoordinate(Integer lngOrLat) {
        return new BigDecimal(lngOrLat).divide(new BigDecimal(MULTIPLE)).doubleValue();
    }
}
