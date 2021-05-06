package com.unisys.aos.view.viewentity.mapper.flight;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FmsCarouselDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FmsCarousel fmsCarousel = new FmsCarousel();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fmsCarousel.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> flightId = fmsCarousel.flightId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> sequenceNumber = fmsCarousel.sequenceNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> carouselCode = fmsCarousel.carouselCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> plannedStartTime = fmsCarousel.plannedStartTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> plannedEndTime = fmsCarousel.plannedEndTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> firstBagTime = fmsCarousel.firstBagTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> lastBagTime = fmsCarousel.lastBagTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> carouselIndicator = fmsCarousel.carouselIndicator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> carouselClass = fmsCarousel.carouselClass;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = fmsCarousel.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = fmsCarousel.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FmsCarousel extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> flightId = column("flight_id", JDBCType.BIGINT);

        public final SqlColumn<Byte> sequenceNumber = column("sequence_number", JDBCType.TINYINT);

        public final SqlColumn<String> carouselCode = column("carousel_code", JDBCType.VARCHAR);

        public final SqlColumn<Date> plannedStartTime = column("planned_start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> plannedEndTime = column("planned_end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> firstBagTime = column("first_bag_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> lastBagTime = column("last_bag_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> carouselIndicator = column("carousel_indicator", JDBCType.CHAR);

        public final SqlColumn<String> carouselClass = column("carousel_class", JDBCType.CHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public FmsCarousel() {
            super("fms_carousel");
        }
    }
}