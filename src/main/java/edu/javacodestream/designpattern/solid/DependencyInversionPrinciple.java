package edu.javacodestream.designpattern.solid;


// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

// B. Abstractions should not depend on details.
// Details should depend on abstractions.

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

enum Relationship
{
    PARENT,
    CHILD,
    SIBLING
}

class Person
{
    public String name;
    // dob etc.

    public Person(String name) {
        this.name = name;
    }
}

interface RelationshipBrowser
{
    List<Person> findAllChildrenOf(String name);
}

class RelationshipsBefore
{
    // Triplet class requires javatuples
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child)
    {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }
}

class RelationshipsAfter implements RelationshipBrowser
{
    public List<Person> findAllChildrenOf(String name) {

        return relations.stream()
                .filter(x -> Objects.equals(x.getValue0().name, name)
                        && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }

    // Triplet class requires javatuples
    private List<Triplet<Person, Relationship, Person>> relations =
            new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child)
    {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }
}

class ResearchBefore
{
    public ResearchBefore(RelationshipsBefore relationships)
    {
        // high-level: find all of john's children
        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
        relations.stream()
                .filter(x -> x.getValue0().name.equals("John")
                        && x.getValue1() == Relationship.PARENT)
                .forEach(ch -> System.out.println("John has a child called " + ch.getValue2().name));
    }

}

class ResearchAfter
{
    public ResearchAfter(RelationshipBrowser browser)
    {
        List<Person> children = browser.findAllChildrenOf("John");
        for (Person child : children)
            System.out.println("John has a child called " + child.name);
    }
}

public class DependencyInversionPrinciple {
    public static void main(String[] args)
    {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        // low-level module
        RelationshipsBefore relationshipsBefore = new RelationshipsBefore();
        relationshipsBefore.addParentAndChild(parent, child1);
        relationshipsBefore.addParentAndChild(parent, child2);

        new ResearchBefore(relationshipsBefore);

        // After refactoring
        // low-level module
        RelationshipsAfter relationshipsAfter = new RelationshipsAfter();
        relationshipsAfter.addParentAndChild(parent, child1);
        relationshipsAfter.addParentAndChild(parent, child2);

        new ResearchAfter(relationshipsAfter);
    }
}
