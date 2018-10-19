package org.sww.joinfamily.cache.po.redis ;

import java.io.Serializable ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.JoinColumn ;
import javax.persistence.ManyToOne ;

import org.springframework.data.redis.core.RedisHash;
import org.sww.joinfamily.cache.po.BasePo;
import org.sww.joinfamily.cache.po.User;

import com.fasterxml.jackson.annotation.JsonBackReference ;

@Entity(name = "PictureUnSaved")
@RedisHash("picture-un-saved")
public class PictureUnSaved extends BasePo implements Serializable {
	
	private static final long	serialVersionUID	= -8004507093185207036L ;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long							id ;
	
	@Column(name = "name")
	private String						name ;
	
	@Column(name = "suffix_name")
	private String						suffixName ;
	
	@Column(name = "file_path")
	private String						filePath ;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference // 防止对象的递归访问
	private User						user ;
	
	public Long getId() {
		return id ;
	}
	
	public void setId(Long id) {
		this.id = id ;
	}
	
	public String getName() {
		return name ;
	}
	
	public void setName(String name) {
		this.name = name ;
	}
	
	public String getSuffixName() {
		return suffixName ;
	}
	
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName ;
	}
	
	public String getFilePath() {
		return filePath ;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath ;
	}
	
	public User getUser() {
		return user ;
	}
	
	public void setUser(User user) {
		this.user = user ;
	}
	
}
