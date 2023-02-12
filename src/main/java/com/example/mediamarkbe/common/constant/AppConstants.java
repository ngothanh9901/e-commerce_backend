package com.example.mediamarkbe.common.constant;

public class AppConstants {
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "100";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    public interface ROLE {
        String  ADMIN = "ADMIN";
        String HR = "HR";
        String MANAGER = "MANAGER";
    }
//    public interface Language{
//        Long ENGLISH=4L;
//        Long KOREA=5L;
//    }
    public interface Source{
        Long WP_WEB=1L;
        Long WEB = 2L;
//        Long HUONG = 2L;
//        Long NGAN = 3L;
    }
}
