package org.lcsp.module6.mapper;

import org.apache.ibatis.annotations.*;
import org.lcsp.module6.model.Dependency;
import org.lcsp.module6.model.Module;

import java.util.List;

@Mapper
public interface ModuleMapper {

    @Select("SELECT * FROM module WHERE id = #{id}")
    @Results({
            @Result(property = "dependencies", column = "id", javaType = List.class, many = @Many(select = "findDependencies"))
    })
    Module findModuleById(@Param("id") long id);

    @Select("SELECT * FROM dependencies WHERE moduleId = #{moduleId}")
    List<Dependency> findDependencies(@Param("moduleId") long moduleId);

    @Insert("INSERT INTO module(groupId, artifactId, version, modifiedTime, modifier, description, detailedDescription, usageExample, notes) " +
            "VALUES(#{groupId}, #{artifactId}, #{version}, #{modifiedTime}, #{modifier}, #{description}, #{detailedDescription}, #{usageExample}, #{notes})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertModule(Module module);

    @Insert("INSERT INTO dependencies(moduleId, dependentId) VALUES(#{moduleId}, #{dependentId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertDependency(Dependency dependency);

    @Select("SELECT * FROM module")
//    @Results({
//            @Result(property = "dependencies", column = "id", javaType = List.class, many = @Many(select = "findDependencies"))
//    })
    List<Module> findAllModules();

    @Delete("DELETE FROM module WHERE id = #{id}")
    void deleteModuleById(@Param("id") long id);

    @Delete("DELETE FROM dependencies WHERE id = #{id}")
    void deleteDependencyById(@Param("id") long id);
}
