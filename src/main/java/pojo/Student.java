package pojo;

public class Student {
	private String studentNo;
	private String studentName;
	
	
	public Student() {
		super();
	}


	public Student(String studentNo, String studentName) {
		super();
		this.studentNo = studentNo;
		this.studentName = studentName;
	}


	public String getStudentNo() {
		return studentNo;
	}


	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	@Override
	public String toString() {
		return "Student [学生编号=" + studentNo + ", 学生姓名="	+ studentName + "]";
	}
	
}
