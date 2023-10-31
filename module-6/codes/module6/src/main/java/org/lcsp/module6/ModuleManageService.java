package org.lcsp.module6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

public class ModuleManageService {

	private final String DBurl = "xxxx";
	private final String administrator = "admin";
	private final String password = "123456";
	private final ModuleManageDBService dbService;

	public ModuleManageService() {
		this.dbService = new ModuleManageDBService(DBurl, administrator, password);
	}

	public ModuleManageService(ModuleManageDBService dbService) {
		this.dbService = dbService;
	}

	// 创建模块并生成 pom.xml 文件
	public static void createModule(String moduleName, String version, String sourceDirectory) throws IOException {
		Model model = new Model();
		model.setModelVersion("4.0.0");
		model.setGroupId("com.example");
		model.setArtifactId(moduleName);
		model.setVersion(version);

		// 设置源代码目录
		if (sourceDirectory != null) {
			model.setBuild(new Model.Build());
			model.getBuild().setSourceDirectory(sourceDirectory);
		}

		// 生成 pom.xml 文件
		try (FileWriter writer = new FileWriter(moduleName + "/pom.xml")) {
			new MavenXpp3Writer().write(writer, model);
		}

		// 存储模块信息到数据库
		// 这里可以根据实际情况将模块信息存储到数据库中
	}

	// 配置父子关系并生成父模块的 pom.xml 文件
	public static void setModuleRelationship(String parentModule, List<String> childModules) throws IOException {
		Model parentModel = new Model();
		parentModel.setModelVersion("4.0.0");
		parentModel.setGroupId("com.example");
		parentModel.setArtifactId(parentModule);
		parentModel.setVersion("1.0.0");
		parentModel.setPackaging("pom");

		// 添加子模块依赖
		for (String childModule : childModules) {
			Dependency dependency = new Dependency();
			dependency.setGroupId("com.example");
			dependency.setArtifactId(childModule);
			dependency.setVersion("1.0.0");
			parentModel.addDependency(dependency);
		}

		// 生成父模块的 pom.xml 文件
		try (FileWriter writer = new FileWriter(parentModule + "/pom.xml")) {
			new MavenXpp3Writer().write(writer, parentModel);
		}

		// 存储父子关系到数据库
		// 这里可以根据实际情况将父子关系存储到数据库中
	}

	// 配置依赖关系并生成子模块的 pom.xml 文件
	public static void setDependencies(String moduleName, List<String> dependencyModules) throws IOException {
		Model model = new Model();
		model.setModelVersion("4.0.0");
		model.setGroupId("com.example");
		model.setArtifactId(moduleName);
		model.setVersion("1.0.0");

		// 添加依赖关系
		for (String dependencyModule : dependencyModules) {
			Dependency dependency = new Dependency();
			dependency.setGroupId("com.example");
			dependency.setArtifactId(dependencyModule);
			dependency.setVersion("1.0.0");
			model.addDependency(dependency);
		}

		// 生成子模块的 pom.xml 文件
		try (FileWriter writer = new FileWriter(moduleName + "/pom.xml")) {
			new MavenXpp3Writer().write(writer, model);
		}

		// 存储依赖关系到数据库
		// 这里可以根据实际情况将依赖关系存储到数据库中
	}
}