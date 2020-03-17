package com.gem.back.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName(value = "sucai")
@EqualsAndHashCode(callSuper = true)
public class Sucai extends Model<Sucai> {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer num;
	private String name;
	private String money;
	private String downnum;
	private String favnum;
	private String tagid;
	private String suffix;
	private String size;
	private String picurl;
	private String createtime;
	private String author;
	private String userid;
	private String save;
	private Integer status;
	private String passtime;
	private String inpasstime;
	private String description;

	@TableLogic
	private Integer delFlag;

}
