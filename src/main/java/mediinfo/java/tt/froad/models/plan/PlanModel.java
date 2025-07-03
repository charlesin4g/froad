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
 * 徒步行程规划主表
 **/
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "plan")
public class PlanModel extends BaseModel {
    /** 所属用户ID **/
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 行程标题 **/
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    /** 行程开始日期 **/
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    /** 行程结束日期 **/
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    /** 计划总距离(公里) **/
    @Column(name = "total_distance_km", precision = 8, scale = 3)
    private BigDecimal totalDistanceKm;

    /** 行程难度等级 **/
    @Column(name = "difficulty", length = 20)
    private String difficulty;

    /** 完整路径GeoJSON数据 **/
    @Column(name = "route_geojson")
    @Convert(converter = JsonConverter.class)
    private String routeGeojson;

    /** 装备检查清单{gear_id: 数量} **/
    @Column(name = "gear_checklist")
    @Convert(converter = JsonConverter.class)
    private String gearChecklist;

    /** 行程起点地理坐标(WGS84坐标系) **/
    @Column(name = "start_point", columnDefinition = "geometry(Point,4326)")
    @Convert(converter = GeometryConverter.class)
    private String startPoint;

    /** 行程终点地理坐标(WGS84坐标系) **/
    @Column(name = "end_point", columnDefinition = "geometry(Point,4326)")
    @Convert(converter = GeometryConverter.class)
    private String endPoint;
} 