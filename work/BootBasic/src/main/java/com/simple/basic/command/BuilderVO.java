package com.simple.basic.command;

public class BuilderVO {
	
	// 클래스패턴 - 빌더패턴(객체의 불변성을 유지하는, 스프링부트에서 자주 사용되는 디자인패턴)
	// 외부 클래스: 항상 내부클래스에 접근이 가능하나, 내부 static 클래스는 외부 값에 접근할 수 없음
	
	// 1. 멤버변수생성
	private String name;
	private int age;
	
	// 3. BuilderVO 생성자도 제한
	private BuilderVO(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
	}
	
	// 4. 외부에서 객체를 요구할 때 setter를 통해서 내부클래스를 반환
	public static Builder builder() {
		return new Builder();
	}
	
	// 7. toString
	@Override
	public String toString() {
		return "BuilderVO [name=" + name + ", age=" + age + "]";
	}

	// 2. 내부클래스 생성
	public static class Builder {
		private String name;
		private int age;
		
		// 생성자 제한
		private Builder() {
			
		}	
		
		// 5. 내부객체 안에는 값을 저장하는 setter만 생성합니다
		// 그리고 나자신을 다시 반환
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder age(int age) {
			this.age = age;
			return this;
		}
		
		// 6. 마지막으로 build 메서드를 실행시켜서 3번에서 만들어둔 외부객체를 반환할 수 있도록 처리
		public BuilderVO build() {
			return new BuilderVO(this);
		}
		
		
	}
	

	
	

}
