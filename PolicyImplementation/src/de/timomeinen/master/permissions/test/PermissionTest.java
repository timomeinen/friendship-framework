package de.timomeinen.master.permissions.test;

import de.timomeinen.master.permissions.FriendshipClass;

public class PermissionTest {

	public static void main(String[] args) {
		FriendshipClass friendshipClass = new FriendshipClass();
		Protect protect = new Protect();
		Friend  friend  = new Friend();
		
		friend.execute(friendshipClass);
		protect.execute(friendshipClass);
	}

}
