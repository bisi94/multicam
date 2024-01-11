package com.example.model;

public class SampleModel {

    private Integer id;
    private String name;

    // 기본 생성자
    public SampleModel() {
    }

    // 매개변수를 받는 생성자
    public SampleModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter 및 Setter 메소드
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString 메소드 (디버깅 및 로깅 용도)
    @Override
    public String toString() {
        return "SampleModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
