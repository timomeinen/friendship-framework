package de.timomeinen.master.friendship.test.packageAccess;

import de.timomeinen.master.friendship.annotations.Friendship;

public class Entity {

	@Friendship(packageAccess = true)
	public int packageAccessible;
	
}
