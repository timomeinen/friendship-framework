package de.timomeinen.master.permissions.test;

import de.timomeinen.master.permissions.FriendshipClass;

public class Protect {

	public void execute(FriendshipClass friend) {
		friend.protectedMethod(this.getClass().getName());
	}
}
