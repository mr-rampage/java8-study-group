package ca.wbac.study.java;

class Pet {
    private String name;
    private PetType type;
    private Integer age;

    static Pet create(String name, Integer age) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setAge(age);
        return pet;
    }

    static Pet create(String name, PetType type, Integer age) {
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

    PetType getType() {
        return type;
    }

    private void setType(PetType type) {
        this.type = type;
    }

    Integer getAge() {
        return age;
    }

    private void setAge(Integer age) {
        this.age = age;
    }
}
