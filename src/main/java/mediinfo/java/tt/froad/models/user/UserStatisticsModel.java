package mediinfo.java.tt.froad.models.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mediinfo.java.tt.froad.models.BaseModel;
import mediinfo.java.tt.froad.models.converter.JsonConverter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * 用户徒步数据统计汇总表
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "user_statistics")
public class UserStatisticsModel extends BaseModel {
    /** 用户ID **/
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 累计徒步距离(公里) **/
    @Column(name = "total_distance_km", precision = 10, scale = 3)
    private BigDecimal totalDistanceKm;

    /** 累计海拔爬升(米) **/
    @Column(name = "total_elevation_gain")
    private Integer totalElevationGain;

    /** 日均徒步距离(公里) **/
    @Column(name = "avg_daily_distance", precision = 6, scale = 2)
    private BigDecimal avgDailyDistance;

    /** 已完成行程规划数 **/
    @Column(name = "completed_plans")
    private Integer completedPlans;

    /** 装备效率统计{category: {total_weight: x, avg_usage: y}} **/
    @Column(name = "gear_efficiency")
    @Convert(converter = JsonConverter.class)
    private String gearEfficiency;

} 