package com.installman.zhong.myfirstapplication;

/**
 * Created by zhong on 17-2-20.
 */

public class LbsInfoDBSchema {
    public static final class LbsInfoTable{
        public static final String NAME = "lbsinfo";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String ADDRESS = "address";
            public static final String LATITUDE = "latitude";
            public static final String LONGITUDE = "longitude";
            public static final String ACCUR = "accur";
            public static final String DATETIME = "datetime";
        }
    }
}
