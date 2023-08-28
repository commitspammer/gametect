package com.gametect.api.model;

import java.util.List;
import java.time.LocalDateTime;

public class ProjectDTO {

	public Integer id;
	public String title;
	public String description;
	public String banner;
	//public LocalDateTime lastActivity;
	public Boolean archived;
	public List<Collaborator> collaborators;
	//public Framework framework;
	//public String sections;

//	private class Framework {
//		public Integer id;
//		public String name;
//		public String description;
//		public String icon;
//		public String guide;
//	}

	private record Collaborator(
		Integer id,
		String name
	){}

	public ProjectDTO(Project p) {
		id = p.getId();
		title = p.getTitle();
		description = p.getDescription();
		banner = p.getBanner();
		archived = p.getArchived();
//		collaborators = p.get.stream()
//			.map(a -> new Collaborator(a.user.id, a.user.name))
//			.toList();
	}

}
