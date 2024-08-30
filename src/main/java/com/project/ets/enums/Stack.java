package com.project.ets.enums;

import java.util.List;

public enum Stack {
	JAVA_FULL_STACK(List.of(Subject.HIBERNATE,Subject.CSS,Subject.HTML,Subject.JAVA,
			Subject.JDBC,Subject.REACT_JS,Subject.SQL,Subject.SPRING,Subject.SERVLETS,Subject.JAVA_SCRIPT)),
	PYTHON_FULL_STACK(List.of(Subject.CSS,Subject.HTML,Subject.JAVA_SCRIPT,Subject.MONGO_DB,Subject.REACT_JS,
			Subject.NODE_JS,Subject.SQL)),
	MERN_STACK(List.of(Subject.CSS,Subject.HTML,Subject.JAVA_SCRIPT,Subject.MONGO_DB,Subject.REACT_JS,
			Subject.NODE_JS,Subject.SQL)),;
	
	private List<Subject> subjects;

	private Stack(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public List<Subject> getSubjects(){
		return subjects;
	}
	
}
