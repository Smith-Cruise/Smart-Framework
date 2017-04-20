package org;

/**
 * Created by Smith on 2017/4/20.
 */
public class Test {
    public static void main(String[] args) {
        Person p1 = new Person(13,"smith");
        Person p2 = new Person(13,"smith");
        System.out.println(p1.hashCode()+";"+p2.hashCode());
        System.out.println(p1.equals(p2));
    }
}

class Person {
    int age;
    String name;
    Person(int a, String n) {
        age = a;
        name = n;
    }

    @Override
    public boolean equals(Object obj) {
        Person p = (Person)obj;
        if (this.age==p.age && this.name.equals(p.name))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        Integer integer = new Integer(age);
        return integer.hashCode();
    }
}
