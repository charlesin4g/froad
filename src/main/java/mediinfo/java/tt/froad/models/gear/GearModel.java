package mediinfo.java.tt.froad.models.gear;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mediinfo.java.tt.froad.models.BaseModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * 徒步装备管理系统
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "gear")
public class GearModel extends BaseModel {
    /** 所属用户ID **/
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 装备名称 **/
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /** 装备主分类 **/
    @Column(name = "category", nullable = false, length = 20)
    private String category;

    /** 装备子分类 **/
    @Column(name = "subcategory", length = 50)
    private String subcategory;

    /** 购买日期 **/
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    /** 购买价格(元) **/
    @Column(name = "price", precision = 8, scale = 2)
    private BigDecimal price;

    /** 重量(千克) **/
    @Column(name = "weight_kg", precision = 5, scale = 3)
    private BigDecimal weightKg;

    /** 存放位置 **/
    @Column(name = "storage_location", length = 100)
    private String storageLocation;

    /** 是否防水 **/
    @Column(name = "is_waterproof")
    private Boolean isWaterproof;

    /** 防水指数(mmH₂O) **/
    @Column(name = "waterproof_rating")
    private Integer waterproofRating;

    /** 是否具有保暖性 **/
    @Column(name = "is_insulation")
    private Boolean isInsulation;

    /** 最低适用温度(℃) **/
    @Column(name = "insulation_temp_min", precision = 3, scale = 1)
    private BigDecimal insulationTempMin;

    /** 最高适用温度(℃) **/
    @Column(name = "insulation_temp_max", precision = 3, scale = 1)
    private BigDecimal insulationTempMax;

    /** 耐用性等级 **/
    @Column(name = "durability_level", length = 20)
    private String durabilityLevel;

    /** 最后使用日期 **/
    @Column(name = "last_used")
    private LocalDate lastUsed;

    /** 备注信息 **/
    @Column(name = "notes")
    private String notes;

} 