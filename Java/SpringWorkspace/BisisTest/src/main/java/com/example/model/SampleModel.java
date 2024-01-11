package com.example.model;

public class SampleModel {

    private Integer id;
    private String name;

    // �⺻ ������
    public SampleModel() {
    }

    // �Ű������� �޴� ������
    public SampleModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter �� Setter �޼ҵ�
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

    // toString �޼ҵ� (����� �� �α� �뵵)
    @Override
    public String toString() {
        return "SampleModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
