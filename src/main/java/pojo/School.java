package pojo;

import java.util.List;

public class School {
	private String schoolNo;
	private String schoolName;
	private String address;
	private List<Collega> collega;//返回的数组长度不一定做好用集合接收
	
	public School() {
		super();
	}
	
	public School(String schoolNo, String schoolName, String address,List<Collega> collega) {
		super();
		this.schoolNo = schoolNo;
		this.schoolName = schoolName;
		this.address = address;
		this.collega = collega;
	}

	public String getSchoolNo() {
		return schoolNo;
	}
	public void setSchoolNo(String schoolNo) {
		this.schoolNo = schoolNo;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<Collega> getCollega() {
		return collega;
	}
	public void setCollega(List<Collega> collega) {
		this.collega = collega;
	}
	@Override
	public String toString() {
		return "School [schoolNo=" + schoolNo + ", schoolName=" + schoolName
				+ ", address=" + address + ", collega=" + collega + "]";
	}
	
}
