package ru.javabegin.training.spring.aop.objects;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import ru.javabegin.training.spring.aop.annotations.ShowResult;
import ru.javabegin.training.spring.aop.annotations.ShowTime;

@Component
public class FileManager2 {

	@ShowTime
	public Set<String> getExtensionList(String folder) {

		File dir = new File(folder);

		Set<String> exList = new TreeSet<String>();

		for (String fileName : dir.list()) {

			File file = new File(dir.getAbsolutePath() + "\\" + fileName);

			int i = fileName.lastIndexOf(".");
			if (file.isFile() && i != -1) {
				exList.add(fileName.substring(i + 1, fileName.toLowerCase().length()));
			}
		}

		return exList;
	}

	@ShowResult
	public Map<String, Integer> getExtensionCount(String folder) {

		File dir = new File(folder);

		Map<String, Integer> map = new HashMap<String, Integer>();

		for (String ext : getExtensionList(folder)) {
			FilenameFilter filter = new CustomFileFilter(ext);
			map.put(ext, dir.listFiles(filter).length);
		}

		return map;
	}

}
