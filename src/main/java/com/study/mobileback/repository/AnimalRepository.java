package com.study.mobileback.repository;

import com.study.mobileback.model.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByUserId(Long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Animal a set a.name = :name, a.city = :city, a.age = :age, a.breed = :breed, a.description = :description where a.id = :id")
    void update(String name, String city, Integer age, String breed, String description, Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Animal a where a.id= :id")
    void deleteById(Long id);
}
