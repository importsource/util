package com.importsource.util.comparable;

/**
 * 
 * @author Hezf
 *
 */
public class Student implements Comparable<Student> {
	private String name;
	private int age;
	private float score;

	public Student(String name, int age, float score) {
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public String toString() {
		return name + "\t\t" + age + "\t\t" + score;
	}

	public int compareTo(Student o) {
		if (this.name.charAt(0) > o.name.charAt(0)) {
			return 1;
		} else if (this.name.charAt(0) < o.name.charAt(0)) {
			return -1;
		} else {
			if (this.score > o.score) // score是private的，为什么能够直接调用,这是因为在Student类内部
				return -1;// 由高到底排序
			else if (this.score < o.score)
				return 1;
			else {
				if (this.age > o.age)
					return 1;// 由底到高排序
				else if (this.age < o.age)
					return -1;
				else
					return 0;
			}
		}
	}
}
