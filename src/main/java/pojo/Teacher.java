package pojo;

import java.util.Arrays;
import java.util.List;

public class Teacher {
	private String teacherName;

	private List Student;

    String [] classes;

    public String[] getClasses() {
        return classes;
    }

    public void setClasses(String[] classes) {
        this.classes = classes;
    }

    public List getStudent() {
        return Student;
    }

    public void setStudent(List student) {
        Student = student;
    }

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
        return "Teacher{" +
                "教师姓名='" + teacherName + '\'' +
                ", 学生=" + Student +
                ", 班级=" + Arrays.toString(classes) +
                '}';
    }
}
