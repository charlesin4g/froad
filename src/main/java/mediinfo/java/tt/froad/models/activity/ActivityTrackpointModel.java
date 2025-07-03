package mediinfo.java.tt.froad.models.activity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mediinfo.java.tt.froad.models.BaseModel;
import mediinfo.java.tt.froad.models.converter.GeometryConverter;

import java.time.OffsetDateTime;

/**
 * 行程轨迹点详细数据(GPS点)
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "activity_trackpoint")
public class ActivityTrackpointModel extends BaseModel {
    /* 轨迹点唯一标识（主键由BaseModel提供） */

    /** 所属行程ID **/
    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    /** 轨迹点序列号(排序用) **/
    @Column(name = "sequence_number", nullable = false)
    private Long sequenceNumber;

    /** 记录时间点 **/
    @Column(name = "recorded_at", nullable = false)
    private OffsetDateTime recordedAt;

    /** 三维地理点(经度,纬度,海拔),WGS84坐标系 **/
    @Column(name = "location", columnDefinition = "geography(PointZ,4326)")
    @Convert(converter = GeometryConverter.class)
    private String location;

    /** 瞬时速度(公里/小时) **/
    @Column(name = "speed_kmh", precision = 5, scale = 2)
    private java.math.BigDecimal speedKmh;

    /** 心率(次/分钟) **/
    @Column(name = "heart_rate")
    private Short heartRate;

    /** 当时温度(℃) **/
    @Column(name = "temperature", precision = 4, scale = 1)
    private java.math.BigDecimal temperature;

    /** 设备电池电量百分比 **/
    @Column(name = "battery_level")
    private Short batteryLevel;
} 