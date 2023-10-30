package com.xpanxion.solution;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Department;
import com.xpanxion.java.assignments.model.Product;
import com.xpanxion.java.assignments.model.Person;
import com.xpanxion.java.assignments.model.Cat;
import com.xpanxion.java.assignments.model.PersonCat;

public class Worker {
    public void ex1 () {
        System.out.println(DataAccess.getProducts().stream()
                .map(f -> new Product(f.getId(), f.getDepartmentId(), DataAccess.getDepartments().get(f.getDepartmentId()-1).getName(), f.getName(), f.getPrice(), f.getSku()))
                .collect(Collectors.toList()));
    }

    public void ex2 () {
        List<Product>prodList = DataAccess.getProducts();
        System.out.println(prodList.stream().map(f -> new Product(f.getId(), f.getDepartmentId(), "N/A", f.getName(), f.getPrice(), f.getSku()))
                .collect(Collectors.toList()));
    }

    public void ex3 () {
        List<Product>prodList = DataAccess.getProducts();
        System.out.println(prodList.stream().filter(p->p.getDepartmentId() == 1 && p.getPrice()>=10)
                .collect(Collectors.toList()));
    }

    public void ex4 () {
        List<Product>prodList = DataAccess.getProducts().stream()
                .map(f -> new Product(f.getId(), f.getDepartmentId(), DataAccess.getDepartments().get(f.getDepartmentId()-1).getName(), f.getName(), f.getPrice(), f.getSku()))
                .collect(Collectors.toList());
        Double sum = prodList.stream().filter(Product->Product.getDepartmentName().equals("Food")).mapToDouble(Product::getPrice).sum();
        System.out.println(sum);
    }

    public void ex5 () {
        List<Person>personList = DataAccess.getPeople();
        System.out.println(personList.stream().filter(p->p.getId()<=3).map(p->new Person(p.getId(), p.getFirstName(), p.getLastName(), p.getAge(), p.getSsn().substring(p.getSsn().length()-4))).collect(Collectors.toList()));
    }

    public void ex6 () {
        List<Cat> catList = DataAccess.getCats();
        System.out.println(catList.stream().sorted(Comparator.comparing(Cat::getName)).collect(Collectors.toList()));
    }

    public void ex7 () {
        String words []= DataAccess.getWords().split(" ");
        TreeMap<String, Integer> map = new TreeMap<>();
        for(int i=0; i<words.length; ++i){
            String word = words[i];
            Integer freq = map.get(word);
            if(freq != null){
                map.put(word, freq + 1);
            }
            else{
                map.put(word, 1);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet())
            System.out.println(entry.getKey() + " = "
                    + entry.getValue());
    }

    public void ex8 () {
        List<Person>personList = DataAccess.getPeople();
        System.out.println(personList.stream().map(p->new Person(p.getId(), p.getFirstName(), null, 0, null)).collect(Collectors.toList()));
    }

    public void ex9 () {
        List<Product>productList = DataAccess.getProducts().stream()
                .map(f -> new Product(f.getId(), f.getDepartmentId(), DataAccess.getDepartments().get(f.getDepartmentId()-1).getName(), f.getName(), f.getPrice(), f.getSku()))
                .collect(Collectors.toList());
        productList =  productList.stream().filter(f->f.getDepartmentName().equals("Electronics")).map(f-> new Product(f.getId(), f.getDepartmentId(), f.getDepartmentName(), f.getName(), f.getPrice()+2, f.getSku())).collect(Collectors.toList());
        Double sum = productList.stream().filter(Product->Product.getDepartmentName().equals("Electronics")).mapToDouble(Product::getPrice).sum();
        System.out.println("$" +sum);
    }

    public void ex10 () {
        var cats = DataAccess.getCats();
        DataAccess.getPeople()
                .stream()
                .map(p -> new PersonCat(p.getId(), p.getFirstName(),
                        cats.stream()
                                .filter(c -> p.getId() == c.getId())
                                .collect(Collectors.toList())))
                .forEach(System.out::print);
    }
}
