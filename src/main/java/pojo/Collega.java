package pojo;

import java.util.List;

public class Collega {
	private String professionalNo;
	private String professionalName;
	private List<Student> student;
	private Teacher teacher;
	
	public Collega() {
		super();
	}
	


	public Teacher getTeacher() {
		return teacher;
	}



	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}



	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public String getProfessionalNo() {
		return professionalNo;
	}
	public void setProfessionalNo(String professionalNo) {
		this.professionalNo = professionalNo;
	}
	public String getProfessionalName() {
		return professionalName;
	}
	public void setProfessionalName(String professionalName) {
		this.professionalName = professionalName;
	}



	@Override
	public String toString() {
		return "Collega [professionalNo=" + professionalNo
				+ ", professionalName=" + professionalName + ", student="
				+ student + ", teacher=" + teacher + "]";
	}





}
