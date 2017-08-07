package car.pool.member.domain;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class MemberCommand {

	@NotEmpty
	private String mem_id;
	private int mem_auth;	/* 0탈퇴회원, 1일반회원, 2관리자 */
	private int mem_driver; /* 1일반회원, 2운전자회원 */
	@Size(min=4,max=10)
	private String mem_pw;
	@NotEmpty
	private String mem_name;
	private String mem_gender;
	private String mem_grade;
	@NotEmpty
	private String mem_phone;
	@NotEmpty
	private String mem_email;
	private Date mem_date;
	private MultipartFile upload;
	private byte[] mem_image;
	private String mem_filename;
	
	
	//비밀번호 일치 여부 체크
	public boolean isCheckedPasswd(String userPasswd){
		if(mem_auth > 0 && mem_pw.equals(userPasswd)){
			return true;
		}
		return false;
	}
		
	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
		
		setMem_image(upload.getBytes());
		setMem_filename(upload.getOriginalFilename());
	}
	
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getMem_auth() {
		return mem_auth;
	}

	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}
	public int getMem_driver() {
		return mem_driver;
	}

	public void setMem_driver(int mem_driver) {
		this.mem_driver = mem_driver;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_gender() {
		return mem_gender;
	}
	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}
	public String getMem_grade() {
		return mem_grade;
	}
	public void setMem_grade(String mem_grade) {
		this.mem_grade = mem_grade;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public Date getMem_date() {
		return mem_date;
	}
	public void setMem_date(Date mem_date) {
		this.mem_date = mem_date;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	
	public byte[] getMem_image() {
		return mem_image;
	}
	public void setMem_image(byte[] mem_image) {
		this.mem_image = mem_image;
	}
	public String getMem_filename() {
		return mem_filename;
	}

	public void setMem_filename(String mem_filename) {
		this.mem_filename = mem_filename;
	}

	@Override
	public String toString() {
		return "MemberCommand [mem_id=" + mem_id + ", mem_auth=" + mem_auth + ", mem_driver=" + mem_driver + ", mem_pw="
				+ mem_pw + ", mem_name=" + mem_name + ", mem_gender=" + mem_gender + ", mem_grade=" + mem_grade
				+ ", mem_phone=" + mem_phone + ", mem_email=" + mem_email + ", mem_date=" + mem_date + ", upload="
				+ upload + ", mem_filename=" + mem_filename + "]";
	}
	
}
