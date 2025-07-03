package mediinfo.java.tt.froad.models.activity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mediinfo.java.tt.froad.models.BaseModel;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * 实际完成的行程记录表
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "activity")
public class ActivityModel extends BaseModel {
    /* 行程记录唯一标识（主键由BaseModel提供） **/

    /** 所属用户ID **/
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 关联的规划ID(可空) **/
    @Column(name = "plan_id")
    private Long planId;

    /** 行程标题 **/
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    /** 实际开始时间 **/
    @Column(name = "start_time", nullable = false)
    private OffsetDateTime startTime;

    /** 实际结束时间 **/
    @Column(name = "end_time")
    private OffsetDateTime endTime;

    /** 实际距离(公里) **/
    @Column(name = "distance_km", precision = 8, scale = 3)
    private BigDecimal distanceKm;

    /** 累计海拔爬升(米) **/
    @Column(name = "elevation_gain")
    private Integer elevationGain;

    /** 累计海拔下降(米) **/
    @Column(name = "elevation_loss")
    private Integer elevationLoss;

    /** 平均速度(公里/小时) **/
    @Column(name = "avg_speed_kmh", precision = 5, scale = 2)
    private BigDecimal avgSpeedKmh;

    /** 天气状况 **/
    @Column(name = "weather", length = 50)
    private String weather;

    /** 平均温度(℃) **/
    @Column(name = "temperature")
    private Short temperature;

    /** 消耗卡路里 **/
    @Column(name = "calories")
    private Short calories;

    /** 使用的装备ID数组 **/
    @Column(name = "gear_used")
    private List<Long> gearUsed;

    /** 行程持续时间(分钟) **/
    @Column(name = "duration_min")
    private Integer durationMin;

    /** 记录创建时间 **/
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    /** 记录创建人ID */
    @Column(name = "created_by")
    private Long createdBy;

    /** 最后修改时间 */
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    /** 最后修改人ID */
    @Column(name = "updated_by")
    private Long updatedBy;

    /** 是否已删除 */
    @Column(name = "is_deleted", nullable = false)
    private Short isDeleted;

    /** 删除时间 */
    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    /** 删除人ID */
    @Column(name = "deleted_by")
    private Long deletedBy;
} 