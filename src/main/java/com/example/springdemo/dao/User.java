package com.example.springdemo.dao;

import com.example.springdemo.enums.UserStatusEnums;
import com.example.springdemo.utils.serializer.UserStatusCode2StatusNameSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    /**
     * 在输出的Json数据中隐藏密码，只能输入不输出 .
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 身份证信息（外键关联） .
     * 级联方式
     * 实体加载方式
     * FetchType.LAZY：懒加载，加载一个实体时，定义懒加载的属性不会马上从数据库中加载
     *
     * FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载
     * referencedColumnName 指定外联表的取值字段，name外键的名称
     * @JoinColumn 默认会使用user表中的主键作为外键
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_card_num", referencedColumnName = "cardNum")
    private IDCard idCard;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "user")
    private List<Book> books;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "conuntry_user", joinColumns = {
            @JoinColumn(name = "user_id")
    }, inverseJoinColumns = { @JoinColumn(name = "country_id")})
    private List<Country> countries;

    /**
     * 响应数据格式转换(为了表面redis序列化由于值类型发生变化导致序列化异常，所以讲状态改为字符串)
     * TODO 或者有其他方式优化
     */
    @JsonSerialize(using = UserStatusCode2StatusNameSerializer.class)
    private String status = UserStatusEnums.NORMAL.getStatusCode();

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreatedDate
    private Date createTime;

    /**
     * 上次更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    @LastModifiedDate
    private Date lastUpdateTime;
}
