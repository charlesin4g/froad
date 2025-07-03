package mediinfo.java.tt.froad.models.gear;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mediinfo.java.tt.froad.models.BaseModel;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * 装备推荐系统
 **/
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "gear_recommendation")
public class GearRecommendationModel extends BaseModel {
    /** 用户ID **/
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 装备ID **/
    @Column(name = "gear_id", nullable = false)
    private Long gearId;

    /** 推荐生成时间 **/
    @Column(name = "generated_at", nullable = false)
    private OffsetDateTime generatedAt;

    /** 推荐得分(0-1) **/
    @Column(name = "score", precision = 4, scale = 3)
    private BigDecimal score;

    /** 推荐原因描述 **/
    @Column(name = "reason")
    private String reason;
} 