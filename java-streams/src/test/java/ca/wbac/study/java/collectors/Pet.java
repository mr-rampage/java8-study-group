package ca.wbac.study.java.collectors;

class Pet {
    enum Type {
        DOG, CAT, FISH, MOOSE
    }

    private String name;
    private Type type;
    private Integer age;

    static Pet create(String name, Integer age) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setAge(age);
        return pet;
    }

    static Pet create(String name, Type type, Integer age) {
        Pet pet = Pet.create(name, age);
        pet.setType(type);
        return pet;
    }

    String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    Type getType() {
        return type;
    }

    private void setType(Type type) {
        this.type = type;
    }

    Integer getAge() {
        return age;
    }

    private void setAge(Integer age) {
        this.age = age;
    }
}
