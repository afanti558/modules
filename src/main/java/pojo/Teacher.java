package pojo;

public class Teacher {
	private String teacherName;

	
	public Teacher() {
		super();
	}

	public Teacher(String teacherName) {
		super();
		this.teacherName = teacherName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Override
	public String toString() {
		return "Teacher [teacherName=" + teacherName + "]";
	}
	
}
