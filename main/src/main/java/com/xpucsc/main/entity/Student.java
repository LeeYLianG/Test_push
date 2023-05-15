package com.xpucsc.main.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.xpucsc.main.enums.PoliticsEnum;
import com.xpucsc.main.enums.SexEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
@Data
@ApiModel("学生信息")
public class Student implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学号，用户唯一索引
     */
    private String stuId;

    /**
     * 密码
     */
    private String password;

    /**
     * 年级（2020）
     */
    private String grade;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级全称
     */
    private String clazzes;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（1：男  | 0：女）
     */
    private Integer sex;

    /**
     * 出生日期（2002年6月10日）
     */
    private String birthdate;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 本人联系电话
     */
    private String phoneSelf;

    /**
     * 父母联系电话
     */
    private String phoneParent;

    /**
     * qq账号
     */
    private String qq;

    /**
     * 是否持有护照情况（1：是 | 0：否）
     */
    private Integer isPassport;

    /**
     * 政治面貌
     */
    private PoliticsEnum politics;

    /**
     * 民族
     */
    private String nation;

    /**
     * 宗教信仰
     */
    private String religiousBelief;

    /**
     * 是否入伍服兵役经历（1：是 | 0：否）
     */
    private Integer isSoldier;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 高考生源地
     */
    private String originPlace;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 宿舍号
     */
    private String dormitory;

    /**
     * 校区
     */
    private String campus;

    /**
     * 银行卡号
     */
    private String bankcard;

    /**
     * 银行卡所属银行
     */
    private String bank;

    /**
     * 银行卡号问题备注
     */
    private String bankRemarks;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Student other = (Student) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStuId() == null ? other.getStuId() == null : this.getStuId().equals(other.getStuId()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getMajor() == null ? other.getMajor() == null : this.getMajor().equals(other.getMajor()))
            && (this.getClazzes() == null ? other.getClazzes() == null : this.getClazzes().equals(other.getClazzes()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getBirthdate() == null ? other.getBirthdate() == null : this.getBirthdate().equals(other.getBirthdate()))
            && (this.getIdentity() == null ? other.getIdentity() == null : this.getIdentity().equals(other.getIdentity()))
            && (this.getPhoneSelf() == null ? other.getPhoneSelf() == null : this.getPhoneSelf().equals(other.getPhoneSelf()))
            && (this.getPhoneParent() == null ? other.getPhoneParent() == null : this.getPhoneParent().equals(other.getPhoneParent()))
            && (this.getQq() == null ? other.getQq() == null : this.getQq().equals(other.getQq()))
            && (this.getIsPassport() == null ? other.getIsPassport() == null : this.getIsPassport().equals(other.getIsPassport()))
            && (this.getPolitics() == null ? other.getPolitics() == null : this.getPolitics().equals(other.getPolitics()))
            && (this.getNation() == null ? other.getNation() == null : this.getNation().equals(other.getNation()))
            && (this.getReligiousBelief() == null ? other.getReligiousBelief() == null : this.getReligiousBelief().equals(other.getReligiousBelief()))
            && (this.getIsSoldier() == null ? other.getIsSoldier() == null : this.getIsSoldier().equals(other.getIsSoldier()))
            && (this.getNativePlace() == null ? other.getNativePlace() == null : this.getNativePlace().equals(other.getNativePlace()))
            && (this.getOriginPlace() == null ? other.getOriginPlace() == null : this.getOriginPlace().equals(other.getOriginPlace()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getDormitory() == null ? other.getDormitory() == null : this.getDormitory().equals(other.getDormitory()))
            && (this.getCampus() == null ? other.getCampus() == null : this.getCampus().equals(other.getCampus()))
            && (this.getBankcard() == null ? other.getBankcard() == null : this.getBankcard().equals(other.getBankcard()))
            && (this.getBank() == null ? other.getBank() == null : this.getBank().equals(other.getBank()))
            && (this.getBankRemarks() == null ? other.getBankRemarks() == null : this.getBankRemarks().equals(other.getBankRemarks()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStuId() == null) ? 0 : getStuId().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getMajor() == null) ? 0 : getMajor().hashCode());
        result = prime * result + ((getClazzes() == null) ? 0 : getClazzes().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getBirthdate() == null) ? 0 : getBirthdate().hashCode());
        result = prime * result + ((getIdentity() == null) ? 0 : getIdentity().hashCode());
        result = prime * result + ((getPhoneSelf() == null) ? 0 : getPhoneSelf().hashCode());
        result = prime * result + ((getPhoneParent() == null) ? 0 : getPhoneParent().hashCode());
        result = prime * result + ((getQq() == null) ? 0 : getQq().hashCode());
        result = prime * result + ((getIsPassport() == null) ? 0 : getIsPassport().hashCode());
        result = prime * result + ((getPolitics() == null) ? 0 : getPolitics().hashCode());
        result = prime * result + ((getNation() == null) ? 0 : getNation().hashCode());
        result = prime * result + ((getReligiousBelief() == null) ? 0 : getReligiousBelief().hashCode());
        result = prime * result + ((getIsSoldier() == null) ? 0 : getIsSoldier().hashCode());
        result = prime * result + ((getNativePlace() == null) ? 0 : getNativePlace().hashCode());
        result = prime * result + ((getOriginPlace() == null) ? 0 : getOriginPlace().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getDormitory() == null) ? 0 : getDormitory().hashCode());
        result = prime * result + ((getCampus() == null) ? 0 : getCampus().hashCode());
        result = prime * result + ((getBankcard() == null) ? 0 : getBankcard().hashCode());
        result = prime * result + ((getBank() == null) ? 0 : getBank().hashCode());
        result = prime * result + ((getBankRemarks() == null) ? 0 : getBankRemarks().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stuId=").append(stuId);
        sb.append(", password=").append(password);
        sb.append(", grade=").append(grade);
        sb.append(", major=").append(major);
        sb.append(", clazzes=").append(clazzes);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", birthdate=").append(birthdate);
        sb.append(", identity=").append(identity);
        sb.append(", phoneSelf=").append(phoneSelf);
        sb.append(", phoneParent=").append(phoneParent);
        sb.append(", qq=").append(qq);
        sb.append(", isPassport=").append(isPassport);
        sb.append(", politics=").append(politics);
        sb.append(", nation=").append(nation);
        sb.append(", religiousBelief=").append(religiousBelief);
        sb.append(", isSoldier=").append(isSoldier);
        sb.append(", nativePlace=").append(nativePlace);
        sb.append(", originPlace=").append(originPlace);
        sb.append(", address=").append(address);
        sb.append(", dormitory=").append(dormitory);
        sb.append(", campus=").append(campus);
        sb.append(", bankcard=").append(bankcard);
        sb.append(", bank=").append(bank);
        sb.append(", bankRemarks=").append(bankRemarks);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}