package com.example.locationtech;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.operation.union.UnaryUnionOp;

public class MergePolygon{
    public static void main(String arg[]) {
        List<Geometry> geometries = new ArrayList<>();
        GeometryFactory f = new GeometryFactory();

        Polygon ligidTipas = f.createPolygon(new Coordinate[] {
                new Coordinate(121.0416097,14.5922119,0),
                new Coordinate(121.0415186,14.592503,0),
                new Coordinate(121.0413932,14.5928752,0),
                new Coordinate(121.0413307,14.5930633,0),
                new Coordinate(121.0425538,14.5934786,0),
                new Coordinate(121.0434765,14.5936447,0),
                new Coordinate(121.0432719,14.5939139,0),
                new Coordinate(121.0432808,14.5940056,0),
                new Coordinate(121.0430367,14.5939384,0),
                new Coordinate(121.0428865,14.5938761,0),
                new Coordinate(121.0426934,14.5938035,0),
                new Coordinate(121.0425217,14.5938969,0),
                new Coordinate(121.0422642,14.5944991,0),
                new Coordinate(121.0423393,14.5950805,0),
                new Coordinate(121.0421784,14.5951947,0),
                new Coordinate(121.0420925,14.5953401,0),
                new Coordinate(121.0420818,14.5955997,0),
                new Coordinate(121.0417063,14.5960773,0),
                new Coordinate(121.0417063,14.5963264,0),
                new Coordinate(121.0415132,14.596451,0),
                new Coordinate(121.0411913,14.5962642,0),
                new Coordinate(121.0410089,14.596098,0),
                new Coordinate(121.0405476,14.5959527,0),
                new Coordinate(121.0402257,14.596098,0),
                new Coordinate(121.0400541,14.5964303,0),
                new Coordinate(121.0398395,14.5965341,0),
                new Coordinate(121.0394318,14.5968663,0),
                new Coordinate(121.0392172,14.5973232,0),
                new Coordinate(121.0392923,14.5975204,0),
                new Coordinate(121.0381121,14.5973232,0),
                new Coordinate(121.0378547,14.5971674,0),
                new Coordinate(121.0380585,14.5962019,0),
                new Coordinate(121.0379405,14.5957139,0),
                new Coordinate(121.037447,14.5945303,0),
                new Coordinate(121.0371573,14.594089,0),
                new Coordinate(121.0369105,14.5939281,0),
                new Coordinate(121.0364814,14.5937308,0),
                new Coordinate(121.0357572,14.5933882,0),
                new Coordinate(121.0352368,14.5935491,0),
                new Coordinate(121.0344965,14.5935647,0),
                new Coordinate(121.0335309,14.5936503,0),
                new Coordinate(121.0322488,14.5933025,0),
                new Coordinate(121.0320933,14.5932921,0),
                new Coordinate(121.0320983,14.5932159,0),
                new Coordinate(121.0319266,14.5930706,0),
                new Coordinate(121.0317764,14.5927383,0),
                new Coordinate(121.0319159,14.5924476,0),
                new Coordinate(121.0323987,14.5926553,0),
                new Coordinate(121.0325059,14.5926345,0),
                new Coordinate(121.0333857,14.5916793,0),
                new Coordinate(121.0337819,14.5917406,0),
                new Coordinate(121.0340931,14.5916057,0),
                new Coordinate(121.034136,14.5914084,0),
                new Coordinate(121.0346188,14.59091,0),
                new Coordinate(121.034829,14.5907375,0),
                new Coordinate(121.0373396,14.5919835,0),
                new Coordinate(121.0377687,14.5921288,0),
                new Coordinate(121.0388631,14.5922119,0),
                new Coordinate(121.0416097,14.5922119,0) }
            );

        Polygon palingon = f.createPolygon(new Coordinate[] {

            new Coordinate(121.0298768,14.5946092,0),
            new Coordinate(121.0300487,14.5943949,0),
            new Coordinate(121.0301881,14.5943015,0),
            new Coordinate(121.0303008,14.5943015,0),
            new Coordinate(121.0305503,14.5942626,0),
            new Coordinate(121.0307863,14.5940238,0),
            new Coordinate(121.0312769,14.594007,0),
            new Coordinate(121.0313466,14.5939758,0),
            new Coordinate(121.0315159,14.5939303,0),
            new Coordinate(121.0316017,14.5937538,0),
            new Coordinate(121.0318163,14.5935358,0),
            new Coordinate(121.0320845,14.5934424,0),
            new Coordinate(121.032093,14.5932927,0),
            new Coordinate(121.0322485,14.5933031,0),
            new Coordinate(121.0335306,14.5936509,0),
            new Coordinate(121.0341029,14.5935984,0),
            new Coordinate(121.0344048,14.5935734,0),
            new Coordinate(121.0344965,14.5935647,0),
            new Coordinate(121.0348457,14.5935578,0),
            new Coordinate(121.0350853,14.593553,0),
            new Coordinate(121.0352368,14.5935491,0),
            new Coordinate(121.0354992,14.5934677,0),
            new Coordinate(121.0357572,14.5933882,0),
            new Coordinate(121.036872,14.5939187,0),
            new Coordinate(121.0371543,14.594087,0),
            new Coordinate(121.037444,14.5945283,0),
            new Coordinate(121.0379375,14.5957119,0),
            new Coordinate(121.0380555,14.5961999,0),
            new Coordinate(121.0378547,14.5971674,0),
            new Coordinate(121.0360458,14.5962081,0),
            new Coordinate(121.0356971,14.5959849,0),
            new Coordinate(121.0348549,14.5959537,0),
            new Coordinate(121.0344687,14.5958758,0),
            new Coordinate(121.0342488,14.5958395,0),
            new Coordinate(121.0337767,14.5956318,0),
            new Coordinate(121.033487,14.5954553,0),
            new Coordinate(121.0332295,14.5952373,0),
            new Coordinate(121.0318079,14.5954501,0),
            new Coordinate(121.0310516,14.5956007,0),
            new Coordinate(121.0307619,14.5959018,0),
            new Coordinate(121.0297265,14.5954605,0),
            new Coordinate(121.0297426,14.5953619,0),
            new Coordinate(121.030145,14.595066,0),
            new Coordinate(121.0298768,14.5946092,0)
            }
        );

        Polygon sanRoque = f.createPolygon(new Coordinate[] {
            new Coordinate(121.0572654,14.7052704,0),
            new Coordinate(121.0568093,14.7056126,0),
            new Coordinate(121.0565519,14.7058202,0),
            new Coordinate(121.0561549,14.7063287,0),
            new Coordinate(121.0558759,14.7067438,0),
            new Coordinate(121.0556721,14.7070966,0),
            new Coordinate(121.0555648,14.7072938,0),
            new Coordinate(121.0554039,14.7073768,0),
            new Coordinate(121.0554039,14.7075636,0),
            new Coordinate(121.0553931,14.7077815,0),
            new Coordinate(121.0551356,14.7081966,0),
            new Coordinate(121.054728,14.7085909,0),
            new Coordinate(121.0540413,14.7085805,0),
            new Coordinate(121.0535156,14.7085598,0),
            new Coordinate(121.0532688,14.7087362,0),
            new Coordinate(121.0529577,14.7088711,0),
            new Coordinate(121.0523569,14.7090268,0),
            new Coordinate(121.0521208,14.7095767,0),
            new Coordinate(121.0517346,14.7093692,0),
            new Coordinate(121.0515522,14.7094522,0),
            new Coordinate(121.0512089,14.7098258,0),
            new Coordinate(121.0507583,14.7104692,0),
            new Coordinate(121.0501789,14.7103654,0),
            new Coordinate(121.0496639,14.7103239,0),
            new Coordinate(121.049106,14.7102824,0),
            new Coordinate(121.0484409,14.7097428,0),
            new Coordinate(121.0481404,14.7095975,0),
            new Coordinate(121.047604,14.7099711,0),
            new Coordinate(121.0465955,14.7099503,0),
            new Coordinate(121.0460591,14.7100126,0),
            new Coordinate(121.0464775,14.7096391,0),
            new Coordinate(121.046558,14.709089,0),
            new Coordinate(121.0468959,14.7087881,0),
            new Coordinate(121.047089,14.705239,0),
            new Coordinate(121.0451444,14.7049134,0),
            new Coordinate(121.0456004,14.7044412,0),
            new Coordinate(121.0464426,14.7031129,0),
            new Coordinate(121.0477462,14.7023916,0),
            new Coordinate(121.0487708,14.7020751,0),
            new Coordinate(121.048862,14.7027393,0),
            new Coordinate(121.0490068,14.7029053,0),
            new Coordinate(121.049436,14.7029468,0),
            new Coordinate(121.0506675,14.7032704,0),
            new Coordinate(121.050925,14.7035817,0),
            new Coordinate(121.0506246,14.7039968,0),
            new Coordinate(121.0507426,14.7041317,0),
            new Coordinate(121.0507962,14.704277,0),
            new Coordinate(121.0507426,14.7045157,0),
            new Coordinate(121.0511932,14.7040383,0),
            new Coordinate(121.0523304,14.7040383,0),
            new Coordinate(121.0530815,14.7045365,0),
            new Coordinate(121.0536608,14.7046402,0),
            new Coordinate(121.0572654,14.7047723,0),
            new Coordinate(121.0572654,14.7052704,0)

             }
        );

        geometries.add(ligidTipas);
        geometries.add(palingon);
        geometries.add(sanRoque);

        Geometry geometry = UnaryUnionOp.union(geometries);
        Coordinate[] coordinates = geometry.getCoordinates();
        for(int i = 0; i<coordinates.length; i++) {
            System.out.println(String.format("%s, %s, %s", coordinates[i].getX(), coordinates[i].getY(), coordinates[i].getZ()));
        }
        System.out.println(coordinates.length);

    }
}
