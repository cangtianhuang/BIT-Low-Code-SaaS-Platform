package org.lcsp.module6;

import org.apache.maven.model.Model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class module6_test1 {

	public static void main(String[] args) throws IOException {

		ModuleManageService service = new ModuleManageService();

		// 用户输入参数
		String parentName = "P";
		String parentVersion = "1.0";
		List<String> modules = List.of("A", "B", "C");
		Map<String, String> dependencies = Map.of("B", "A", "C", "B");

		// 调用方法创建模块
		for (String module : modules) {
			service.createModule(module);
		}

		// 配置父模块
		Model parent = service.configureParent(parentName, parentVersion);

		// 配置依赖关系
		dependencies.forEach((child, parent) -> {
			parent.addDependency(service.createDependency(child));
		});

		// 保存pom.xml
		builder.savePom(parent, parentName);
		modules.forEach(m -> builder.savePom(new Model(), m));

	}
}
