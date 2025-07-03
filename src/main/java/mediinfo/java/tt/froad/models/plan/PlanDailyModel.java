package mediinfo.java.tt.froad.models.plan;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mediinfo.java.tt.froad.models.BaseModel;
import mediinfo.java.tt.froad.models.converter.GeometryConverter;
import mediinfo.java.tt.froad.models.converter.JsonConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * 行程每日详细规划表
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "plan_daily")
public class PlanDailyModel extends BaseModel {
    /** 所属行程ID **/
    @Column(name = "plan_id", nullable = false)
    private Long planId;

    /** 行程中的第几天(从1开始) **/
    @Column(name = "day_number", nullable = false)
    private Short dayNumber;

    /** 具体日期 **/
    @Column(name = "date", nullable = false)
    private LocalDate date;

    /** 目标地点 **/
    @Column(name = "target_location")
    private String targetLocation;

    /** 当日目标距离(公里) **/
    @Column(name = "target_distance_km", precision = 6, scale = 2)
    private BigDecimal targetDistanceKm;

    /** 当日海拔爬升(米) **/
    @Column(name = "elevation_gain")
    private Integer elevationGain;

    /** 预计时长(分钟) **/
    @Column(name = "expected_duration_min")
    private Integer expectedDurationMin;

    /** 每日计划备注 **/
    @Column(name = "notes")
    private String notes;

    /** 时间段规划 **/
    @Column(name = "time_slots")
    @Convert(converter = JsonConverter.class)
    private String timeSlots;

    /** 当日露营地坐标(WGS84坐标系) **/
    @Column(name = "camp_location", columnDefinition = "geometry(Point,4326)")
    @Convert(converter = GeometryConverter.class)
    private String campLocation;

} 