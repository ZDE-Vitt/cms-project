package com.gem.back.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@TableName(value = "film")
@EqualsAndHashCode(callSuper = true)
public class Film extends Model<Film> {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String filmName;
	private String filmPic;
	private String summary;
	private String actor;
	private String director;
	private String nation;
	private String type;
	private String language;
	private String releaseTime;

	@TableLogic
	private Integer delFlag;
}
