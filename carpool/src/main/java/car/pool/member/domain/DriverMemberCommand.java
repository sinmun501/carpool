package car.pool.member.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class DriverMemberCommand {

	
	private int driver_seq;
	@NotEmpty
	private String driver_license_num;
	private String driver_bank;
	@NotEmpty
	private String driver_account;
	private String driver_can_animal;
	private String driver_can_smoking;
	private String driver_can_charge;
	private int driver_money;
	private Date driver_date;
	private String mem_id;
	private int car_seq;
	private MultipartFile car_upload;
	private byte[] car_image;
	private String car_filename;
	@NotEmpty
	private String car_registration_num;
	@NotEmpty
	private String car_model;
	
	
	public void setCar_upload(MultipartFile car_upload)throws IOException{
		this.car_upload = car_upload;
		
		setCar_image(car_upload.getBytes());
		setCar_filename(car_upload.getOriginalFilename());
	}
	
	
	public int getDriver_seq() {
		return driver_seq;
	}
	public void setDriver_seq(int driver_seq) {
		this.driver_seq = driver_seq;
	}
	public String getDriver_license_num() {
		return driver_license_num;
	}
	public void setDriver_license_num(String driver_license_num) {
		this.driver_license_num = driver_license_num;
	}
	public String getDriver_bank() {
		return driver_bank;
	}
	public void setDriver_bank(String driver_bank) {
		this.driver_bank = driver_bank;
	}
	public String getDriver_account() {
		return driver_account;
	}
	public void setDriver_account(String driver_account) {
		this.driver_account = driver_account;
	}
	public String getDriver_can_animal() {
		return driver_can_animal;
	}
	public void setDriver_can_animal(String driver_can_animal) {
		this.driver_can_animal = driver_can_animal;
	}
	public String getDriver_can_smoking() {
		return driver_can_smoking;
	}
	public void setDriver_can_smoking(String driver_can_smoking) {
		this.driver_can_smoking = driver_can_smoking;
	}
	public String getDriver_can_charge() {
		return driver_can_charge;
	}
	public void setDriver_can_charge(String driver_can_charge) {
		this.driver_can_charge = driver_can_charge;
	}
	public int getDriver_money() {
		return driver_money;
	}
	public void setDriver_money(int driver_money) {
		this.driver_money = driver_money;
	}
	public Date getDriver_date() {
		return driver_date;
	}
	public void setDriver_date(Date driver_date) {
		this.driver_date = driver_date;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getCar_seq() {
		return car_seq;
	}
	public void setCar_seq(int car_seq) {
		this.car_seq = car_seq;
	}
	public String getCar_registration_num() {
		return car_registration_num;
	}
	public void setCar_registration_num(String car_registration_num) {
		this.car_registration_num = car_registration_num;
	}
	public String getCar_model() {
		return car_model;
	}
	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}
	public MultipartFile getCar_upload() {
		return car_upload;
	}
	public byte[] getCar_image() {
		return car_image;
	}
	public void setCar_image(byte[] car_image) {
		this.car_image = car_image;
	}
	public String getCar_filename() {
		return car_filename;
	}
	public void setCar_filename(String car_filename) {
		this.car_filename = car_filename;
	}
	
	@Override
	public String toString() {
		return "DriverMemberCommand [driver_seq=" + driver_seq + ", driver_license_num=" + driver_license_num
				+ ", driver_bank=" + driver_bank + ", driver_account=" + driver_account + ", driver_can_animal="
				+ driver_can_animal + ", driver_can_smoking=" + driver_can_smoking + ", driver_can_charge="
				+ driver_can_charge + ", dirver_money=" + driver_money + ", driver_date=" + driver_date + ", mem_id="
				+ mem_id + ", car_seq=" + car_seq + ", car_registration_num=" + car_registration_num + ", car_model="
				+ car_model + ", car_upload=" + car_upload + ", car_filename=" + car_filename + "]";
	}
	
}
