package org.lcsp.module6;

import jep.Jep;
import jep.SharedInterpreter;

public class PythonModuleLoader {
	public PythonModuleLoader(String pythonPath) {
		try (Jep jep = new Jep()) {
			// 运行指定的Python脚本
			boolean result = jep.eval(pythonPath);
			System.out.println("Python脚本运行结果: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
