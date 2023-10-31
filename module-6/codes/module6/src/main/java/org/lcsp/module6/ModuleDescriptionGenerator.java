package org.lcsp.module6;

import java.util.List;

public class ModuleDescriptionGenerator {

	private ModuleManageDBService dbService; // 从数据库查询模块信息的服务

	public ModuleDescriptionGenerator(ModuleManageDBService dbService) {
		this.dbService = dbService;
	}

	public String getModuleName(String moduleId) {
		return dbService.getModuleName(moduleId);
	}

	public String getModuleVersion(String moduleId) {
		return dbService.getModuleVersion(moduleId);
	}

	public String getModuleAuthor(String moduleId) {
		return dbService.getModuleAuthor(moduleId);
	}

	public List<Pair> getModuleDependencies(String moduleId) {
		return dbService.getModuleDependencies(moduleId);
	}

	public String getModuleFunction(String moduleId) {
		return dbService.getModuleFunction(moduleId);
	}

	public List<Module> getModuleInterfaces(String moduleId) {
		return dbService.getModuleInterfaces(moduleId);
	}

}
