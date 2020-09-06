package org.itstep.selenium.test.streamapi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.testng.annotations.Test;

public class StreamTest {

  @Test
  public void test() {
    int[] numbers = {-5, -4, -3, -2, -1, 0, 5, 2, 3, 4, 1};

    Arrays.stream(numbers)
        .filter(b -> b > 0)
        .sorted()
        .forEach(System.out::println);
  }

  @Test
  public void test2() {
    List<String> strings = Arrays.asList("Vilnus", "Minsk", "Grodno", "Brest");

    List<String> collect = strings.stream()
        .filter(x -> x.length() > 5)
        .sorted(Comparator.comparingInt(String::length))
        .collect(Collectors.toList());

    Stream<String> citiesStream = Stream
        .of("Vilnus", "Minsk", "Grodno", "Brest");

  }

  @Test
  public void test3() {
    Stream<Phone> phoneStream = Stream.of(
        new Phone("iPhone 6 S", 54000),
        new Phone("Lumia 950", 45000),
        new Phone("Samsung Galaxy S 6", 40000));

    phoneStream
        .map(Phone::getModel)
        .sorted()
        .forEach(System.out::println);
  }

  @Test
  public void test4() {
    List<Human> humans = Arrays.asList(
        new Human("Sam", Arrays.asList("Buddy", "Lucy")),
        new Human("Bob", Arrays.asList("Frankie", "Rosie")),
        new Human("Marta", Arrays.asList("Simba", "Tilly")));

    Stream<String> stringStream = humans.stream()
        .map(Human::getPets)
        .flatMap(p -> p.stream());

    Stream<List<String>> listStream = humans.stream()
        .map(human -> human.getPets());

    Stream<String> stringStream1 = listStream.flatMap(list -> list.stream());

    stringStream1.forEach(System.out::println);

    Optional<String> first = stringStream1.findFirst();
  }

  @Test
  public void test5() {
    Human human = new Human(null, null);

    Optional<Integer> integer = Optional.ofNullable(human)
        .map(x -> x.getName())
        .map(x -> x.length());

    System.out.println(integer.orElse(null));
  }

  class Phone {

    private String model;
    private Integer price;

    public Phone(String model, Integer price) {
      this.model = model;
      this.price = price;
    }

    public String getModel() {
      return model;
    }

    public Integer getPrice() {
      return price;
    }
  }

  class Human {

    private String name;
    private List<String> pets;

    public Human(String name, List<String> pets) {
      this.name = name;
      this.pets = pets;
    }

    public String getName() {
      return name;
    }

    public List<String> getPets() {
      return pets;
    }
  }
}
